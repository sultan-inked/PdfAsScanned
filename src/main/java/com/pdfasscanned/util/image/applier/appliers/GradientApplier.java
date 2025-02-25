package com.pdfasscanned.util.image.applier.appliers;

import com.pdfasscanned.dto.pdfprocessors.GradientDto;
import com.pdfasscanned.dto.pdfprocessors.PdfProcessorsListDto;
import com.pdfasscanned.mapper.ColorMapper;
import com.pdfasscanned.util.image.applier.ImageProcessorApplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;

@RequiredArgsConstructor
@Component
public class GradientApplier implements ImageProcessorApplier {
    private final ColorMapper colorMapper;

    @Override
    public boolean isApplicable(PdfProcessorsListDto processorsList) {
        return processorsList.getGradient() != null;
    }

    @Override
    public BufferedImage apply(PdfProcessorsListDto processorsList, BufferedImage primalImage, BufferedImage processedImage) {
        int primalImageWidth = primalImage.getWidth();
        int primalImageHeight = primalImage.getHeight();

        GradientDto gradient = processorsList.getGradient();
        Color color1 = colorMapper.toColor(gradient.getColor1());
        Color color2 = colorMapper.toColor(gradient.getColor2());

        Graphics2D g2d = processedImage.createGraphics();

        GradientPaint graphics2D = new GradientPaint(0, 0, color1, primalImageWidth, primalImageHeight, color2);

        g2d.drawImage(primalImage, 0, 0, null);
        g2d.setPaint(graphics2D);
        g2d.fillRect(0, 0, primalImageWidth, primalImageHeight);

        g2d.dispose();

        return processedImage;
    }
}
