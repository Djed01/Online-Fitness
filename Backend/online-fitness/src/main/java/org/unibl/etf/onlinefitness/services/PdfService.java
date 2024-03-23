package org.unibl.etf.onlinefitness.services;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.models.dto.PdfDTO;
import org.unibl.etf.onlinefitness.models.entities.ActivityEntity;
import org.unibl.etf.onlinefitness.models.entities.BodyWeightEntity;
import org.unibl.etf.onlinefitness.models.enumeration.LogType;
import org.unibl.etf.onlinefitness.repositories.ActivityRepository;
import org.unibl.etf.onlinefitness.repositories.BodyWeightRepository;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfService {

    private final ActivityRepository activityRepository;
    private final BodyWeightRepository bodyWeightRepository;
    private final LogService logService;

    public PdfDTO generatePDFByUserId(Integer userId) {
        try (PDDocument document = new PDDocument()) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            // Create first page
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            drawHeader(contentStream);
            drawDataRows(contentStream, userId);
            contentStream.close();

            // Create second page for body weight history
            PDPage secondPage = new PDPage();
            document.addPage(secondPage);

            PDPageContentStream secondContentStream = new PDPageContentStream(document, secondPage);
            drawBodyWeightHeader(secondContentStream);
            drawWeightDataRows(secondContentStream, userId);
            secondContentStream.close();

            document.save(byteArrayOutputStream);

            return new PdfDTO("Activity_" + userId, byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            logService.log(LogType.ERROR,"Error occurred while creating PDF!");
            e.printStackTrace(); // Consider handling the exception more gracefully
        }
        return null;
    }

    private void drawHeader(PDPageContentStream contentStream) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
        contentStream.setNonStrokingColor(Color.BLUE);
        contentStream.beginText();
        contentStream.newLineAtOffset(100, 750);
        contentStream.showText("Activity History");
        contentStream.endText();

        float margin = 50;
        float yStart = 700;
        float rowHeight = 20;

        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.setNonStrokingColor(Color.BLACK);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yStart);
        contentStream.showText("Activity ID");
        contentStream.newLineAtOffset(100, 0);
        contentStream.showText("Name");
        contentStream.newLineAtOffset(100, 0);
        contentStream.showText("Sets");
        contentStream.newLineAtOffset(50, 0);
        contentStream.showText("Reps");
        contentStream.newLineAtOffset(50, 0);
        contentStream.showText("Weight");
        contentStream.newLineAtOffset(100, 0);
        contentStream.showText("Date");
        contentStream.endText();
    }

    private void drawDataRows(PDPageContentStream contentStream, Integer userId) throws IOException {
        float margin = 50;
        float yStart = 700;
        float rowHeight = 20;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


        List<ActivityEntity> activities = activityRepository.findAllByUserIdAndStatus(userId, true);
        float yPosition = yStart - rowHeight;

        for (ActivityEntity activity : activities) {
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            contentStream.setNonStrokingColor(Color.BLACK);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText(activity.getId().toString());
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText(activity.getName());
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText(activity.getSets().toString());
            contentStream.newLineAtOffset(50, 0);
            contentStream.showText(activity.getReps().toString());
            contentStream.newLineAtOffset(50, 0);
            contentStream.showText(activity.getWeight().toString());
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText(dateFormat.format(activity.getDate()));
            contentStream.endText();

            yPosition -= rowHeight;
        }
    }

    private void drawBodyWeightHeader(PDPageContentStream contentStream) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
        contentStream.setNonStrokingColor(Color.BLUE);
        contentStream.beginText();
        contentStream.newLineAtOffset(100, 750);
        contentStream.showText("Body Weight History");
        contentStream.endText();

        float margin = 50;
        float yStart = 700;
        float rowHeight = 20;

        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.setNonStrokingColor(Color.BLACK);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yStart);
        contentStream.showText("ID");
        contentStream.newLineAtOffset(100, 0);
        contentStream.showText("Weight");
        contentStream.newLineAtOffset(100, 0);
        contentStream.showText("Date");
        contentStream.endText();
    }

    private void drawWeightDataRows(PDPageContentStream contentStream, Integer userId) throws IOException {
        float margin = 50;
        float yStart = 700;
        float rowHeight = 20;

        List<BodyWeightEntity> weights = bodyWeightRepository.findAllByUserId(userId);
        float yPosition = yStart - rowHeight;
        for (BodyWeightEntity weight : weights) {
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            contentStream.setNonStrokingColor(Color.BLACK);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText(weight.getId().toString());
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText(weight.getWeight().toString());
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText(weight.getDate().toString());
            contentStream.endText();

            yPosition -= rowHeight;
        }
    }
}
