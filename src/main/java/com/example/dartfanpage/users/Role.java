package com.example.dartfanpage.users;

import com.example.dartfanpage.BaseEntity;

import javax.persistence.Entity;


@Entity
public class Role extends BaseEntity {


    public static final String USER = "ROLE_USER";
    public static final String ADMIN = "ROLE_ADMIN";

    private String roleName;

    public Role() {
    }

    public String getRoleName() {
        return roleName;
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

}
