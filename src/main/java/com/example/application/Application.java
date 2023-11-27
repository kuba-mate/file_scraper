package com.example.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static String argument0;
    public static int argument1;

    public static void main(String[] args) {

        argument0 = args[0];
        argument1 = Integer.parseInt(args[1]);
        SpringApplication.run(Application.class, args);

    }

}
