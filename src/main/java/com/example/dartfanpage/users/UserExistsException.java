package com.example.dartfanpage.users;

public class UserExistsException extends Exception {
    public UserExistsException(String msg) {
        super(msg);
    }
}
