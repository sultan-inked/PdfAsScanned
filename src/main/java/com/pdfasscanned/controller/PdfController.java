package com.pdfasscanned.controller;

import com.pdfasscanned.service.ScanEffectService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/api-v-1/pdf")
@RestController
public class PdfController {
    private final ScanEffectService scanEffectService;

    @PostMapping("/convert/as-scan")
    public ResponseEntity<?> convertPdfAsScan(@RequestParam("file") MultipartFile file) throws IOException {
        File inputPdf = File.createTempFile("uploaded_", ".pdf");
        file.transferTo(inputPdf);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"scanned_" +
                        file.getOriginalFilename() + "\"")
                .body(new InputStreamResource(new FileInputStream(scanEffectService.processPdf(inputPdf))));
    }
}
