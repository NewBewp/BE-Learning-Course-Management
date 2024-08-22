package com.company.projects.course.coursemanagementsystem.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EmailService {
    JavaMailSender javaMailSender;

    public void sendCredentials(String to, String username, String password, String name) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject("Account Information");
        helper.setFrom("trantuanduy.20011105@gmail.com");

        // Nội dung HTML
        String htmlContent = "<h1>Welcome to Course Management System</h1>" +
                "<p>Dear " + name + ", </p>" +
                "<p>We are thrilled to welcome you to the course management</p>" +
                "<p>Below are your account details:</p>" +
                "<p><strong>Username:</strong> " + username + "<br>" +
                "<strong>Password:</strong> " + password + "</p>" +
                "<p>If you have any questions or need assistance, please do not hesitate to reach out to us at this email or visit our support page.</p>" +
                "<p>We hope you have a great experience with Course Management System.</p>" +
                "<p>Best regards,</p>";
        helper.setText(htmlContent, true); // true để chỉ định rằng nội dung là HTML

        javaMailSender.send(message);
    }
}

