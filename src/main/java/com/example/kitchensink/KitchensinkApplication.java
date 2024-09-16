package com.example.kitchensink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@Validated
public class KitchensinkApplication {


    public static void main(String[] args) {
        SpringApplication.run(KitchensinkApplication.class, args);
    }
}