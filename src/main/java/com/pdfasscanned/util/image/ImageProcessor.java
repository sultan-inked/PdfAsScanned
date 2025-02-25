package com.pdfasscanned.util.image;

import com.pdfasscanned.dto.pdfprocessors.PdfProcessorsListDto;
import com.pdfasscanned.util.image.applier.ImageProcessorApplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ImageProcessor {
    private final List<ImageProcessorApplier> appliers;

    public BufferedImage applyScanEffect(BufferedImage primalImage, PdfProcessorsListDto processorsList) {
        return appliers.stream()
                .filter(applier -> applier.isApplicable(processorsList))
                .reduce(
                        new BufferedImage(primalImage.getWidth(), primalImage.getHeight(), BufferedImage.TYPE_INT_BGR),
                        (processedImage, applier) -> applier.apply(processorsList, primalImage, processedImage),
                        (img1, img2) -> img2
                );
    }
}
