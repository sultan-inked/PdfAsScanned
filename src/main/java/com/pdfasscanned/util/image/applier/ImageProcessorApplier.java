package com.pdfasscanned.util.image.applier;

import com.pdfasscanned.dto.pdfprocessors.PdfProcessorsListDto;

import java.awt.image.BufferedImage;

public interface ImageProcessorApplier {
    boolean isApplicable(PdfProcessorsListDto processorsList);

    BufferedImage apply(PdfProcessorsListDto processorsList, BufferedImage primalImage, BufferedImage processedImage);
}
