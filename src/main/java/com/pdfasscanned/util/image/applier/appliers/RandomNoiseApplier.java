package com.pdfasscanned.util.image.applier.appliers;

import com.pdfasscanned.dto.pdfprocessors.PdfProcessorsListDto;
import com.pdfasscanned.util.image.applier.ImageProcessorApplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Component
public class RandomNoiseApplier implements ImageProcessorApplier {
    private final Random random;

    @Override
    public boolean isApplicable(PdfProcessorsListDto processorsList) {
        return processorsList.getNoise() != null;
    }

    @Override
    public BufferedImage apply(PdfProcessorsListDto processorsList, BufferedImage primalImage, BufferedImage processedImage) {
        int primalImageWidth = primalImage.getWidth();
        int primalImageHeight = primalImage.getHeight();
        int noiseRange = processorsList.getNoise().getNoiseRange();

        IntStream.range(0, primalImageHeight).forEach(y ->
                IntStream.range(0, primalImageWidth).forEach(x -> {
                    int rgb = processedImage.getRGB(x, y);
                    Color color = new Color(rgb);
                    int noise = random.nextInt(noiseRange) - (noiseRange / 2);
                    int newRed = Math.max(0, Math.min(255, color.getRed() + noise));
                    int newGreen = Math.max(0, Math.min(255, color.getGreen() + noise));
                    int newBlue = Math.max(0, Math.min(255, color.getBlue() + noise));

                    processedImage.setRGB(x, y, new Color(newRed, newGreen, newBlue).getRGB());
                })
        );

        return processedImage;
    }
}
