package com.example.dartfanpage.contact;


import com.example.dartfanpage.config.SmtpProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailSender {

    private final SmtpProperties smtpProperties;
    private final JavaMailSender mailSender;

    public void sendEmail(Email email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(email.getEMailAddress());
        simpleMailMessage.setTo(smtpProperties.getUser());
        simpleMailMessage.setSubject("Wiadomość wysłana ze strony DartPolska: " + email.getTitle());
        simpleMailMessage.setText(email.getText());

        mailSender.send(simpleMailMessage);
    }

    public void sendPasswordResetTokenEmail(String email, String url) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(smtpProperties.getUser());
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Dart Polska: prośba o zresetowanie hasła.");
        simpleMailMessage.setText(constructResetTokenMessage(url));

        mailSender.send(simpleMailMessage);
    }

    private String constructResetTokenMessage(String url) {
        return "Otrzymaliśmy prośbę o zresetowanie hasła do " +
                "twojego konta. W celu zresetowania hasła kliknij w poniższy link. Link jest ważny przez 15 minut. \n\n\n " + url +
                "\n\n\n Jeśli to nie ty wysałeś żądanie zresetowanie hasła zignoruj tę wiadomośc!!";
    }
}
