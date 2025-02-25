package com.pdfasscanned.service;

import com.pdfasscanned.dto.pdfprocessors.PdfProcessorsListDto;
import com.pdfasscanned.util.image.ImageProcessor;
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
    private final ImageToPdfConverter imageToPdfConverter;
    private final ImageProcessor imageProcessor;

    public File processPdf(File inputPdf, PdfProcessorsListDto processorsList) throws IOException {
        List<BufferedImage> images = pdfToImageConverter.convert(inputPdf);

        List<BufferedImage> processedImages = images.stream()
                .map(image -> imageProcessor.applyScanEffect(image, processorsList))
                .toList();

        return imageToPdfConverter.convert(processedImages, "processed_scanned.pdf");
    }
}
