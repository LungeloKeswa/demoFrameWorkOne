package utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class EmailUtils {

    public static void sendTestReport(String reportPath) {

        final String senderEmail = "christian.keswa92@gmail.com";
        final String senderPassword = "gxjcscmtyvoxqebh";
        final String recipientEmail = "christian.keswa92@gmail.com";

        // SMTP Properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create Session
        Session session = Session.getInstance(props,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail,senderPassword);
            }
        });

        session.setDebug(true);

        try {

            // Create Message
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(senderEmail));

            message.setRecipients( Message.RecipientType.TO,InternetAddress.parse(recipientEmail));

            message.setSubject("Test Email From QA Automation Lungelo");

            // mime Body party
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(
                    "Hello,\n\n"+
                            "This is a test email from Java.\n\n" +
                            "Regards,\n" +
                            "QA Team"
            );

            // Attachment Part
            MimeBodyPart attachmentPart = new MimeBodyPart();
            //String filePath = System.getProperty("user.dir")+"/reports/ExtentReport.html";
            System.out.println("Attachment path is - "+reportPath);
            attachmentPart.attachFile(new File(reportPath));

            // Combine body and attachment path
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);

            // Send Email
            Transport.send(message);
            System.out.println("Email Sent Successfully!");

        } catch (MessagingException e) {

            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
