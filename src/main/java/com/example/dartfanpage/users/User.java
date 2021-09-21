package com.example.dartfanpage.users;

import com.example.dartfanpage.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class User extends BaseEntity {


    private String firstName;
    private String lastName;
    private String eMail;
    private String passwordHash;
    private String city;
    private String zipCode;
    private String street;
    private String birthDate;
    private String phone;

    @ManyToMany
    @JoinTable(name = "user_role")
    private List<Role> roles = new ArrayList<>();

    public User() {
    }



    public User(String firstName, String lastName, String eMail, String passwordHash, String city, String zipCode, String street, String birthDate, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.passwordHash = passwordHash;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public static User fromDto(RegistrationUserDto dto, String passwordHash) {
        return new User(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEMail(),
                passwordHash,
                dto.getCity(),
                dto.getZipCode(),
                dto.getStreet(),
                dto.getBirthDate(),
                dto.getPhone());
    }

    public String geteMail() {
        return eMail;
    }

    public void addRole(Role role) {
        roles.add(role);
    }


}
