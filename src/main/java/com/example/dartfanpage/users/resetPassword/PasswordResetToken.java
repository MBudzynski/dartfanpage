package com.example.dartfanpage.users.resetPassword;

import com.example.dartfanpage.BaseEntity;
import com.example.dartfanpage.users.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PasswordResetToken extends BaseEntity {

    private String token;

    private LocalDateTime expiryDateTime;


    public PasswordResetToken(String token, LocalDateTime expiryDateTime) {
        this.token = token;
        this.expiryDateTime = expiryDateTime;
    }

    public PasswordResetToken() {
    }

    public LocalDateTime getExpiryDateTime() {
        return expiryDateTime;
    }
}
