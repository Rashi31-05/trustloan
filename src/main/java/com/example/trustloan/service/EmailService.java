package com.example.trustloan.service;

import com.example.trustloan.entity.Loan;
import com.example.trustloan.util.EncryptionUtil;
import com.example.trustloan.util.PdfGeneratorUtil;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class EmailService {

    @Autowired
    private SendGrid sendGrid;

    @Value("${sendgrid.from-email}")
    private String fromEmail;

    public void sendLoanStatusEmail(Loan loan) {

        try {

            String status = loan.getStatus();

            // Generate password-protected PDF (DOB as password)
            byte[] pdfBytes = PdfGeneratorUtil.generateEncryptedLoanPdf(
                    loan.getFullName(),
                    String.valueOf(loan.getLoanAmountRequested()),
                    status,
                    loan.getDob().toString(),
                    loan.getApplicationDate().toString()
            );

            Email from = new Email(fromEmail);
            Email to = new Email(loan.getEmail());
            String subject = "Your TrustLoan Application Summary";

            String htmlContent =
                    "<h2>TrustLoan Application Summary</h2>"
                            + "<p>Dear " + loan.getFullName() + ",</p>"
                            + "<p>Please find attached your password-protected loan summary.</p>"
                            + "<p><b>Password:</b> Your Date of Birth (YYYY-MM-DD)</p>"
                            + "<br><p>Regards,<br>TrustLoan Team</p>";

            Content content = new Content("text/html", htmlContent);
            Mail mail = new Mail(from, subject, to, content);

            Attachments attachments = new Attachments();
            attachments.setContent(Base64.getEncoder().encodeToString(pdfBytes));
            attachments.setType("application/pdf");
            attachments.setFilename("LoanSummary.pdf");
            attachments.setDisposition("attachment");

            mail.addAttachments(attachments);

            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);

            System.out.println("SendGrid Status Code: " + response.getStatusCode());
            System.out.println("SendGrid Body: " + response.getBody());

            sendGrid.api(request);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}