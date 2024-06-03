package com.example.dartfanpage.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class MailConfiguration {

    private final SmtpProperties smtpProperties;
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(smtpProperties.getHost());
        mailSender.setPort(smtpProperties.getPort());

        mailSender.setUsername(smtpProperties.getUser());
        mailSender.setPassword(smtpProperties.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", smtpProperties.getAuth().toString());
        props.put("mail.smtp.starttls.enable", smtpProperties.getStarttls().toString());
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.trust", smtpProperties.getHost());

        return mailSender;
    }
}
