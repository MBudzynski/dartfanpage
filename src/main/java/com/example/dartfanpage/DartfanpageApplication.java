package com.example.dartfanpage;

import com.example.dartfanpage.config.CompanyInfo;
import com.example.dartfanpage.config.SmtpProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {CompanyInfo.class, SmtpProperties.class})
public class DartfanpageApplication {

    public static void main(String[] args) {
        SpringApplication.run(DartfanpageApplication.class, args);
    }

}
