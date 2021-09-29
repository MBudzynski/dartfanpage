package com.example.dartfanpage.users.resetPassword;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class TokenValidator {


    private final PasswordResetRepository passwordResetRepository;

     public String isValid(String token){
         PasswordResetToken passwordToken = passwordResetRepository.findByToken(token);

         if (isTokenFound(passwordToken)) {
             return "BadToken";
         } else if(isTokenExpired(passwordToken)){
             return "expired";
         } else{
             return null;
         }

     }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken == null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        return passToken.getExpiryDateTime().isAfter(LocalDateTime.now().plusMinutes(15));
    }

}
