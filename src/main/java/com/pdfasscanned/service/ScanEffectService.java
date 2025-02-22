package com.pdfasscanned.service;

import com.pdfasscanned.util.ImageProcessor;
import com.pdfasscanned.util.converter.ImageToPdfConverter;
import com.pdfasscanned.util.converter.PdfToImageConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ScanEffectService {
    private final PdfToImageConverter pdfToImageConverter;
    private final ImageProcessor imageProcessor;
    private final ImageToPdfConverter imageToPdfConverter;

    public File processPdf(File inputPdf) throws IOException {
        List<BufferedImage> images = pdfToImageConverter.convert(inputPdf);
        List<BufferedImage> processedImages = images.stream()
                .map(imageProcessor::applyScanEffect)
                .toList();
        return imageToPdfConverter.convert(processedImages, "processed_scanned.pdf");
    }
}
