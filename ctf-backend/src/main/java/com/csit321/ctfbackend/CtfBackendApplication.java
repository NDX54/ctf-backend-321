package com.csit321.ctfbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.Modulith;

@SpringBootApplication
@Modulith
public class CtfBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CtfBackendApplication.class, args);
    }

}
