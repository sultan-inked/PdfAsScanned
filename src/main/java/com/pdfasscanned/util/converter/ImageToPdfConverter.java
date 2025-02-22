package com.pdfasscanned.util.converter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class ImageToPdfConverter {
    public File convert(List<BufferedImage> images, String outputPath) throws IOException {
        PDDocument document = new PDDocument();

        images.forEach(image -> {
            PDPage page = new PDPage(new PDRectangle(image.getWidth(), image.getHeight()));
            document.addPage(page);

            try {
                PDImageXObject pdImage = PDImageXObject.createFromFileByContent(convertBufferedImageToFile(image), document);
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    contentStream.drawImage(pdImage, 0, 0, image.getWidth(), image.getHeight());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        File outputFile = new File(outputPath);
        document.save(outputFile);
        document.close();

        return outputFile;
    }

    private File convertBufferedImageToFile(BufferedImage image) throws IOException {
        File tempFile = File.createTempFile("scanned_effect", ".jpg");
        ImageIO.write(image, "jpg", tempFile);
        return tempFile;
    }
}
