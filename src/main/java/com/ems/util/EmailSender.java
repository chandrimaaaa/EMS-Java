package com.ems.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    private static final String SMTP_HOST = "smtp.gmail.com"; 
    private static final String SMTP_PORT = "587";
    private static final String SENDER_EMAIL = ConfigUtil.get("EMAIL_USER");
    private static final String SENDER_PASSWORD = ConfigUtil.get("EMAIL_PASS"); 

    public static void sendCredentialsEmail(String recipientEmail, String employeeName, String assignedId, String assignedPassword) {
        // Run in an independent background execution thread to keep UI responses instant
        new Thread(() -> {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", SMTP_HOST);
            props.put("mail.smtp.port", SMTP_PORT);
            props.put("mail.smtp.ssl.trust", SMTP_HOST);

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(SENDER_EMAIL));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
                message.setSubject("Welcome to the Team! Your EMS Profile Account Credentials");

                String htmlContent = "<h3>Welcome to the Organization, " + employeeName + "!</h3>"
                        + "<p>An administrative profile has been compiled for you within our Employee Management System.</p>"
                        + "<p><strong>Your System Login Account Credentials:</strong></p>"
                        + "<ul>"
                        + "<li><strong>User ID / Employee ID:</strong> " + assignedId + "</li>"
                        + "<li><strong>Temporary Password:</strong> " + assignedPassword + "</li>"
                        + "</ul>"
                        + "<br><p><em>Please secure these credentials safely.</em></p>";

                message.setContent(htmlContent, "text/html");
                Transport.send(message);
                System.out.println("Credential dispatch successful to: " + recipientEmail);

            } catch (MessagingException e) {
                System.err.println("Critical error triggered during JavaMail transaction execution runtime:");
                e.printStackTrace();
            }
        }).start();
    }
}