package com.example.dartfanpage.contact;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactService {

    private EmailSender emailSender;

    public void sendEmail(Email email){
        emailSender.sendEmail(email);
    }

}
