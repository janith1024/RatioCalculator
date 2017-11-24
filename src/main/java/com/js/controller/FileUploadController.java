package com.js.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

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
                                   RedirectAttributes redirectAttributes) throws IOException, InvalidFormatException {

        ArrayList<String> list = new ArrayList<>();
        ExcelManager.ExcelSheet excelBalanceSheet = null;
        ExcelManager.ExcelSheet excelIncomeStatement = null;
        if (balanceSheet != null) {
//        storageService.store(balanceSheet);
            excelBalanceSheet = ExcelManager.getReadSheet(balanceSheet.getInputStream(), 0);
        }
        if (balanceSheet != null) {
//        storageService.store(incomeStatement);
            excelIncomeStatement = ExcelManager.getReadSheet(incomeStatement.getInputStream(), 0);
        }
        if (excelBalanceSheet != null) {
            list.add(new RCdata("Current Ratio", calculateService.currentRatio(excelBalanceSheet.getDouble(24, 1), excelBalanceSheet.getDouble(50, 1))).toString());
            list.add(new RCdata("Quick Ratio", calculateService.quickRatio(excelBalanceSheet.getDouble(24, 1), excelBalanceSheet.getDouble(21, 1), excelBalanceSheet.getDouble(50, 1))).toString());
            list.add(new RCdata("Absolute Liquid Ratio", calculateService.absoluteLiquidRatio(excelBalanceSheet.getDouble(19, 1), excelBalanceSheet.getDouble(50, 1))).toString());
        }
        if (excelIncomeStatement != null) {
            list.add(new RCdata("Gross Profit Ratio", calculateService.grossProfitRatio(excelIncomeStatement.getDouble(8, 2), excelIncomeStatement.getDouble(3, 2))).toString());
            list.add(new RCdata("Net Profit Ratio", calculateService.netProfitRatio(excelIncomeStatement.getDouble(19, 2), excelIncomeStatement.getDouble(3, 2))).toString());
            list.add(new RCdata("Operating Profit Ratio", calculateService.operatingProfitRatio(excelIncomeStatement.getDouble(17, 2), excelIncomeStatement.getDouble(3, 2))).toString());
            list.add(new RCdata("Earning Per Share Ratio", calculateService.earningPerShareRatio(excelIncomeStatement.getDouble(19, 2), excelIncomeStatement.getDouble(23, 2))).toString());
            list.add(new RCdata("Dividend Payout Ratio", calculateService.dividendPayoutRatio(excelIncomeStatement.getDouble(22, 2), excelIncomeStatement.getDouble(19, 2))).toString());
        }
        if (excelBalanceSheet != null && excelIncomeStatement != null) {
            list.add(new RCdata("Return On Equity Ratio", calculateService.returnOnEquityRatio(excelIncomeStatement.getDouble(19, 2), excelBalanceSheet.getDouble(35, 1))).toString());
        }
        redirectAttributes.addFlashAttribute("results", list);

        return "redirect:/";
    }

    @ExceptionHandler(RCException.class)
    public ResponseEntity<?> handleStorageFileNotFound(RCException exc) {
        return ResponseEntity.notFound().build();
    }

}

