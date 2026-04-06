package com.example.trustloan.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PdfGeneratorUtil {

    public static byte[] generateEncryptedLoanPdf(
            String applicantName,
            String loanAmount,
            String status,
            String dobPassword,
            String applicationDate
    ) throws Exception {

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter writer = PdfWriter.getInstance(document, baos);

        // 🔐 Password protect PDF
        writer.setEncryption(
                dobPassword.getBytes(),
                dobPassword.getBytes(),
                PdfWriter.ALLOW_PRINTING,
                PdfWriter.ENCRYPTION_AES_128
        );

        document.open();

        // 🔷 Fonts
        Font headerFont = new Font(Font.HELVETICA, 20, Font.BOLD);
        Font subHeaderFont = new Font(Font.HELVETICA, 12, Font.NORMAL);
        Font labelFont = new Font(Font.HELVETICA, 12, Font.BOLD);
        Font valueFont = new Font(Font.HELVETICA, 12, Font.NORMAL);

        // 🔷 Bank Header
        Paragraph bankName = new Paragraph("TRUSTLOAN FINANCIAL SERVICES", headerFont);
        bankName.setAlignment(Element.ALIGN_CENTER);
        document.add(bankName);

        document.add(new Paragraph(" "));
        document.add(new LineSeparator());
        document.add(new Paragraph(" "));

        // 🔷 Date
        String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Paragraph datePara = new Paragraph("Date: " + todayDate, subHeaderFont);
        datePara.setAlignment(Element.ALIGN_RIGHT);
        document.add(datePara);

        document.add(new Paragraph(" "));

        // 🔷 Subject
        Paragraph subject = new Paragraph(
                "Subject: Loan Application Status Notification",
                labelFont
        );
        document.add(subject);

        document.add(new Paragraph(" "));

        // 🔷 Greeting
        document.add(new Paragraph("Dear " + applicantName + ",", valueFont));
        document.add(new Paragraph(" "));

        // 🔷 Body
        document.add(new Paragraph(
                "We are pleased to inform you that your loan application has been processed. Below are the details of your application:",
                valueFont
        ));

        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        // 🔷 Details Table (Professional Look)
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        table.addCell(new PdfPCell(new Phrase("Applicant Name", labelFont)));
        table.addCell(new PdfPCell(new Phrase(applicantName, valueFont)));

        table.addCell(new PdfPCell(new Phrase("Loan Amount", labelFont)));
        table.addCell(new PdfPCell(new Phrase("₹ " + loanAmount, valueFont)));

        table.addCell(new PdfPCell(new Phrase("Application Date", labelFont)));
        table.addCell(new PdfPCell(new Phrase(applicationDate, valueFont)));

        table.addCell(new PdfPCell(new Phrase("Loan Status", labelFont)));
        table.addCell(new PdfPCell(new Phrase(status, valueFont)));

        document.add(table);

        document.add(new Paragraph(" "));

        // 🔷 Closing
        document.add(new Paragraph(
                "If you have any queries regarding your application, please contact our support team.",
                valueFont
        ));

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Sincerely,", valueFont));
        document.add(new Paragraph("TrustLoan Team", labelFont));

        document.close();

        return baos.toByteArray();
    }
}