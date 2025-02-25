package com.pdfasscanned.util.image.applier.appliers;

import com.pdfasscanned.dto.pdfprocessors.PdfProcessorsListDto;
import com.pdfasscanned.util.image.applier.ImageProcessorApplier;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;

@Component
public class GrayscaleApplier implements ImageProcessorApplier {
    @Override
    public boolean isApplicable(PdfProcessorsListDto processorsList) {
        return processorsList.getGrayscale() != null && processorsList.getGrayscale().isApplicable();
    }

    @Override
    public BufferedImage apply(PdfProcessorsListDto processorsList, BufferedImage primalImage, BufferedImage processedImage) {
        BufferedImage grayscaleImage =
                new BufferedImage(processedImage.getWidth(), processedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D g2d = grayscaleImage.createGraphics();
        g2d.drawImage(processedImage, 0, 0, null);

        g2d.dispose();

        return grayscaleImage;
    }
}
