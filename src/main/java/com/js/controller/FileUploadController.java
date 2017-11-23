package com.js.controller;


import java.io.IOException;
import java.util.stream.Collectors;

import com.js.data.RCdata;
import com.js.service.*;
import com.js.util.*;
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
    public String handleFileUpload(@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2,
                                   RedirectAttributes redirectAttributes) throws IOException {
//        storageService.store(file1);
//        storageService.store(file2);
        ExcelManager.ExcelSheet excelSheet1 = ExcelManager.getReadSheet(file1.getInputStream(), 0);
        if(excelSheet1 != null){
            RCdata rCdata = new RCdata();
            rCdata.setName("Add");
            //excelSheet1.get(1,2);read from excel file
            rCdata.setValue(String.valueOf(calculateService.add(5,8)));
            redirectAttributes.addFlashAttribute("message", rCdata);
        }
//        ExcelManeager.ExcelSheet excelSheet2 = ExcelManeager.getReadSheet(file2.getInputStream(),0);

        return "redirect:/";
    }

    @ExceptionHandler(RCException.class)
    public ResponseEntity<?> handleStorageFileNotFound(RCException exc) {
        return ResponseEntity.notFound().build();
    }

}

