package com.example.press_lab.service.emailService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Slf4j
@Component
public class MailService {

    @Value("${spring.mail.username}")
    String username;

    @Value("${spring.mail.password}")
    String password;

    public void sendEmail(String toEmail, Integer otpCode) throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(toEmail)
        );
        message.setSubject("Registration");
        message.setText(String.valueOf(otpCode));
        Transport.send(message);
        log.info("Mail sending to {} successfully", toEmail);
    }
}

