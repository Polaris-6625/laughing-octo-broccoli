package com.example.musicuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = {"com.example.musicuser.Filter"})
public class MusicUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicUserApplication.class, args);
    }

}
