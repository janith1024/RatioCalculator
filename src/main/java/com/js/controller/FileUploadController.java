package com.js.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.js.data.FormData;
import com.js.data.RCdata;
import com.js.service.*;
import com.js.util.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

    private final StorageService storageService;
    private final RatioCalculateService calculateService;

    @Autowired
    public FileUploadController(StorageService storageService, RatioCalculateService calculateService) {
        this.storageService = storageService;
        this.calculateService = calculateService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

//        model.addAttribute("files", storageService.loadAll().map(
//                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//                        "serveFile", path.getFileName().toString()).build().toString())
//                .collect(Collectors.toList()));
        model.addAttribute("formData", new FormData());

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("balanceSheet") MultipartFile balanceSheet, @RequestParam("incomeStatement") MultipartFile incomeStatement,
                                   @ModelAttribute FormData formData, RedirectAttributes redirectAttributes) throws IOException, InvalidFormatException {

        ArrayList<String> list = new ArrayList<>();
        ExcelManager.ExcelSheet excelBalanceSheet = null;
        ExcelManager.ExcelSheet excelIncomeStatement = null;
        if (!balanceSheet.isEmpty()) {
//        storageService.store(balanceSheet);
            excelBalanceSheet = ExcelManager.getReadSheet(balanceSheet.getInputStream(), 0);
        }
        if (!incomeStatement.isEmpty()) {
//        storageService.store(incomeStatement);
            excelIncomeStatement = ExcelManager.getReadSheet(incomeStatement.getInputStream(), 0);
        }
        list.addAll(getLiquidityRatio(excelBalanceSheet, formData));
        list.addAll(getProfitabilityRatio(excelIncomeStatement, excelBalanceSheet, formData));
        redirectAttributes.addFlashAttribute("results", list);

        return "redirect:/";
    }

    @ExceptionHandler(RCException.class)
    public ResponseEntity<?> handleStorageFileNotFound(RCException exc) {
        return ResponseEntity.notFound().build();
    }

    private List<String> getLiquidityRatio(ExcelManager.ExcelSheet excelBalanceSheet, FormData formData) {
        ArrayList<String> list = new ArrayList<>();
        if (excelBalanceSheet != null && formData != null) {
            if (formData.getRatio().equalsIgnoreCase("Liquidity Ratio") || formData.getRatio().equalsIgnoreCase("Select All")) {
                switch ((formData.getSubRatio())) {
                    case "Current Ratio":
                        list.add(new RCdata("Current Ratio", calculateService.currentRatio(excelBalanceSheet.getDouble(24, 1), excelBalanceSheet.getDouble(50, 1))).toString());
                        break;
                    case "Quick Ratio":
                        list.add(new RCdata("Quick Ratio", calculateService.quickRatio(excelBalanceSheet.getDouble(24, 1), excelBalanceSheet.getDouble(21, 1), excelBalanceSheet.getDouble(50, 1))).toString());
                        break;
                    case "Absolute Liquid Ratio":
                        list.add(new RCdata("Absolute Liquid Ratio", calculateService.absoluteLiquidRatio(excelBalanceSheet.getDouble(19, 1), excelBalanceSheet.getDouble(50, 1))).toString());
                        break;
                    default:
                        list.add(new RCdata("Current Ratio", calculateService.currentRatio(excelBalanceSheet.getDouble(24, 1), excelBalanceSheet.getDouble(50, 1))).toString());
                        list.add(new RCdata("Quick Ratio", calculateService.quickRatio(excelBalanceSheet.getDouble(24, 1), excelBalanceSheet.getDouble(21, 1), excelBalanceSheet.getDouble(50, 1))).toString());
                        list.add(new RCdata("Absolute Liquid Ratio", calculateService.absoluteLiquidRatio(excelBalanceSheet.getDouble(19, 1), excelBalanceSheet.getDouble(50, 1))).toString());
                }
            }
        } else {
            list.add("something went wrong");
        }
        return list;
    }

    private List<String> getProfitabilityRatio(ExcelManager.ExcelSheet excelIncomeStatement, ExcelManager.ExcelSheet excelBalanceSheet, FormData formData) {
        ArrayList<String> list = new ArrayList<>();
        if (excelIncomeStatement != null && formData != null) {
            if (formData.getRatio().equalsIgnoreCase("Profitability Ratio") || formData.getRatio().equalsIgnoreCase("Select All")) {
                switch ((formData.getSubRatio())) {
                    case "GRoss Profit Ratio":
                        list.add(new RCdata("Gross Profit Ratio", calculateService.grossProfitRatio(excelIncomeStatement.getDouble(8, 2), excelIncomeStatement.getDouble(3, 2))).toString());
                        break;
                    case "Net Profit Ratio":
                        list.add(new RCdata("Net Profit Ratio", calculateService.netProfitRatio(excelIncomeStatement.getDouble(19, 2), excelIncomeStatement.getDouble(3, 2))).toString());
                        break;
                    case "Operating Profit Ratio":
                        list.add(new RCdata("Operating Profit Ratio", calculateService.operatingProfitRatio(excelIncomeStatement.getDouble(17, 2), excelIncomeStatement.getDouble(3, 2))).toString());
                        break;
                    case "Earnings Per Share Ratio":
                        list.add(new RCdata("Earning Per Share Ratio", calculateService.earningPerShareRatio(excelIncomeStatement.getDouble(19, 2), excelIncomeStatement.getDouble(23, 2))).toString());
                        break;
                    case "Return On Equity Ratio":
                        break;
                    case "Dividend Payout Ratio":
                        list.add(new RCdata("Dividend Payout Ratio", calculateService.dividendPayoutRatio(excelIncomeStatement.getDouble(22, 2), excelIncomeStatement.getDouble(19, 2))).toString());
                        break;
                    default:
                        list.add(new RCdata("Gross Profit Ratio", calculateService.grossProfitRatio(excelIncomeStatement.getDouble(8, 2), excelIncomeStatement.getDouble(3, 2))).toString());
                        list.add(new RCdata("Net Profit Ratio", calculateService.netProfitRatio(excelIncomeStatement.getDouble(19, 2), excelIncomeStatement.getDouble(3, 2))).toString());
                        list.add(new RCdata("Operating Profit Ratio", calculateService.operatingProfitRatio(excelIncomeStatement.getDouble(17, 2), excelIncomeStatement.getDouble(3, 2))).toString());
                        list.add(new RCdata("Earning Per Share Ratio", calculateService.earningPerShareRatio(excelIncomeStatement.getDouble(19, 2), excelIncomeStatement.getDouble(23, 2))).toString());
                        list.add(new RCdata("Dividend Payout Ratio", calculateService.dividendPayoutRatio(excelIncomeStatement.getDouble(22, 2), excelIncomeStatement.getDouble(19, 2))).toString());

                }
                if (excelBalanceSheet != null && (formData.getSubRatio().equalsIgnoreCase("Return On Equity Ratio") || formData.getSubRatio().equalsIgnoreCase("Select All"))) {
                    list.add(new RCdata("Return On Equity Ratio", calculateService.returnOnEquityRatio(excelIncomeStatement.getDouble(19, 2), excelBalanceSheet.getDouble(35, 1))).toString());

                }
            }
        } else {
            list.add("something went wrong");
        }
        return list;
    }

}

