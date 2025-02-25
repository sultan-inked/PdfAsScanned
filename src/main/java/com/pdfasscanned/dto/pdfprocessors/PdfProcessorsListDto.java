package com.pdfasscanned.dto.pdfprocessors;

import lombok.Data;

@Data
public class PdfProcessorsListDto {
    private GradientDto gradient;
    private NoiseRangeDto noise;
    private GrayscaleDto grayscale;
}
