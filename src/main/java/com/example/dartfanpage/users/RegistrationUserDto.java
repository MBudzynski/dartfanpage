package com.example.dartfanpage.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationUserDto {

    private String userName;
    private String firstName;
    private String lastName;
    private String eMail;
    private String password;
    private String city;
    private String zipCode;
    private String street;
    private String birthDate;
    private String phone;


}
