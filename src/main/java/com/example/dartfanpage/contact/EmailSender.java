package com.example.dartfanpage.contact;


import com.example.dartfanpage.config.SmtpProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class EmailSender {

    private final SmtpProperties smtpProperties;

    public void sendEmail(Email email){
        Message message = prepareMessage(createSession(), email);

        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Message prepareMessage(Session session, Email email) {
        try {
            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(smtpProperties.getUser()));
            message.setSubject("Wiadomość wysłana ze strony DartPolska: " + email.getTitle());
            message.setText("Email do korespondencji: " + email.getEMailAddress() + "\n" +email.getText());
            return message;
        } catch (Exception ex) {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Session createSession() {
        return Session.getInstance(prepareProperties(),
                new Authenticator() {
                    @Override
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(smtpProperties.getUser(), smtpProperties.getPassword());
                    }
                });
    }

    private Properties prepareProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", smtpProperties.getAuth().toString());
        properties.put("mail.smtp.starttls.enable", smtpProperties.getStarttls().toString());
        properties.put("mail.smtp.host", smtpProperties.getHost());
        properties.put("mail.smtp.port", smtpProperties.getPort().toString());
        return properties;
    }

    public void sendPasswordResetTokenEmail(String email, String url) {
        Message message = constructResetTokenEmail(createSession(), email, url);

        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Message constructResetTokenEmail(Session session, String email, String url) {
        try {
            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Dart Polska: prośba o zresetowanie hasła.");
            message.setText("Otrzymaliśmy prośbę o zresetowanie hasła do " +
                    "twojego konta. W celu zresetowania hasła kliknij w poniższy link. Link jest ważny przez 15 minut. \n\n\n " + url +
            "\n\n\n Jeśli to nie ty wysałeś żądanie zresetowanie hasła zignoruj tę wiadomośc!!");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


}
