package com.utp.factory.spring_fecoma_api_rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringFecomaApiRestApplication{

    public static void main(String[] args) {
        SpringApplication.run(SpringFecomaApiRestApplication.class, args);
    }
    
}
