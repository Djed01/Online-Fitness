package org.unibl.etf.onlinefitness.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.models.enumeration.LogType;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final LogService logService;
    private final String url = "http://localhost:4200/?token=";

    public void sendActivationEmail(String email, String token) {
        String activationLink = url + token; // Activation link
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        try {
            helper.setTo(email);
            helper.setSubject("Activate Your Account");
            String htmlContent = "<p>Click <a href=\"" + activationLink + "\">here</a> to activate your account.</p>";
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            logService.log(LogType.ERROR,"Error occurred while sending activation email account!");
            // Handle exception
            e.printStackTrace();
        }
    }

    public void sendNewsletterEmail(String content, String email) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        try {
            helper.setTo(email);
            helper.setSubject("Online Fitness Newsletter");
            helper.setText(content, false);
            mailSender.send(message);
        } catch (MessagingException e) {
            logService.log(LogType.ERROR,"Error occurred while sending newsletter!");
            e.printStackTrace();
        }
    }
}
