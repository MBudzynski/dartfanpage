package com.example.dartfanpage.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;



@Getter
@Setter
@ConfigurationProperties(prefix="info")
public class CompanyInfo {

    private String name;
    private Address address;
    private ContactDetails contactDetails;


        @Getter
        @Setter
        static class Address {
            private String city;
            private String street;
            private String number;
        }

    @Getter
    @Setter
    static class ContactDetails {
        private String telephone;
        private String email;
    }

}