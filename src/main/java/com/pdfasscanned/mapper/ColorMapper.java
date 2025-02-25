package com.pdfasscanned.mapper;

import com.pdfasscanned.dto.pdfprocessors.ColorDto;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class ColorMapper {
    public Color toColor(ColorDto colorDto) {
        return new Color(colorDto.getRed(), colorDto.getGreen(), colorDto.getBlue(), colorDto.getAlpha());
    }
}
