package com.example.dartfanpage.users;

import com.example.dartfanpage.BaseEntity;
import com.example.dartfanpage.users.resetPassword.PasswordResetToken;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends BaseEntity {

    private String userName;
    private String firstName;
    private String lastName;
    private String eMail;
    private String passwordHash;
    private String city;
    private String zipCode;
    private String street;
    private String birthDate;
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role")
    private List<Role> roles = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="passwordResetToken_id", referencedColumnName = "id")
    private PasswordResetToken passwordResetToken;

    public User() {
    }



    public User(String userName, String firstName, String lastName, String eMail, String passwordHash, String city, String zipCode, String street, String birthDate, String phone) {
        this.userName = userName;
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
                dto.getUserName(),
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

    public List<Role> getRoles() {
        return roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public PasswordResetToken getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(PasswordResetToken passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }
}
