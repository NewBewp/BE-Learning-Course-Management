package com.company.projects.course.coursemanagementsystem.service;

import com.company.projects.course.coursemanagementsystem.dto.CompanyDto;
import com.company.projects.course.coursemanagementsystem.dto.CourseDto;
import com.company.projects.course.coursemanagementsystem.dto.EnrollmentDto;
import com.company.projects.course.coursemanagementsystem.dto.StudentDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EmailService {
    JavaMailSender javaMailSender;
    SpringTemplateEngine templateEngine;

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

    public void sendThanksEnrollment(String to, StudentDto student, CourseDto course, CompanyDto company) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject("Thank you letter");
        helper.setFrom("trantuanduy.20011105@gmail.com");

        // Nội dung HTML
        String htmlContent = "<h1>Welcome to Course Management System</h1>" +
                "<p>Dear " + student.getName() + ",</p>" +
                "<p>We are pleased to inform you that you have successfully registered for the course <strong>" + course.getName() + "</strong>!</p>" +
                "<p>Course Details:</p>" +
                "<ul>" +
                "<li><strong>Course Name:</strong> " + course.getName() + "</li>" +
                "<li><strong>Start Date:</strong> " + course.getStartDate() + "</li>" +
                "<li><strong>Start Date:</strong> " + course.getEndDate() + "</li>" +
                "</ul>" +
                "<p>Please make sure you are prepared and ready to attend the course. If you have any questions or need further assistance, feel free to contact us at " + company.getEmail() + " or " + company.getPhone() + ".</p>" +
                "<p>We will contact you shortly to confirm your course registration.</p>" +
                "<p>We look forward to seeing you in the course and hope you have a great learning experience.</p>" +
                "<p>Best regards,</p>" +
                "<p>" + company.getName() + "</p>" +
                "<p>" + company.getEmail() + "</p>" +
                "<p>" + company.getPhone() + "</p>" +
                "<p>" + company.getAddress() + "</p>";
        helper.setText(htmlContent, true); // true để chỉ định rằng nội dung là HTML

        javaMailSender.send(message);
    }

    public void sendPasswordResetEmail(String to, String resetLink) {
        Context context = new Context();
        context.setVariable("resetLink", resetLink);
        String body = templateEngine.process("password-reset-email", context);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(to);
            helper.setSubject("Password Reset Request");
            helper.setText(body, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);
    }

    public void sendNewCredentialsEmail(String to, String username, String password) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject("Your New Account Credentials");
        helper.setFrom("trantuanduy.20011105@gmail.com");


        String htmlContent = "<h1>Your Account Credentials</h1>" +
                "<p>Dear User,</p>" +
                "<p>Your password has been successfully reset. Below are your new account details:</p>" +
                "<p><strong>Username:</strong> " + username + "<br>" +
                "<strong>Password:</strong> " + password + "</p>" +
                "<p>If you have any questions or need assistance, please do not hesitate to reach out to us.</p>" +
                "<p>Best regards,</p>" +
                "<p>Course Management System Team</p>";
        helper.setText(htmlContent, true);

        javaMailSender.send(message);
    }
}

