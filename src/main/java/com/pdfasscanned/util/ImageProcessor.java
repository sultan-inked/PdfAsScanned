package com.pdfasscanned.util;

import org.springframework.stereotype.Component;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.image.BufferedImage;
import java.util.Random;

@Component
public class ImageProcessor {
    private final Random random = new Random();

    public BufferedImage applyScanEffect(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage processedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

        Graphics2D g2d = processedImage.createGraphics();

        // 1. Градиент для неравномерной яркости (как при плохом сканировании)
        GradientPaint gp = new GradientPaint(0, 0, new Color(255, 255, 255, 50),
                width, height, new Color(240, 240, 240, 50));

        g2d.drawImage(image, 0, 0, null);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);

        // 2. Перевод в градации серого

        // 3. Добавление случайного шума
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = processedImage.getRGB(x, y);
                Color color = new Color(rgb);
                int noise = random.nextInt(10) - 5;
                int newRed = Math.max(0, Math.min(255, color.getRed() + noise));
                int newGreen = Math.max(0, Math.min(255, color.getGreen() + noise));
                int newBlue = Math.max(0, Math.min(255, color.getBlue() + noise));

                processedImage.setRGB(x, y, new Color(newRed, newGreen, newBlue).getRGB());
            }
        }

        g2d.dispose();

        return processedImage;
    }

    public BufferedImage applyGrayscale(BufferedImage image) {
        BufferedImage grayscaleImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = grayscaleImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);

        g2d.dispose();

        return grayscaleImage;
    }
}
