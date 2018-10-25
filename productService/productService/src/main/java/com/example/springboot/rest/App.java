package com.example.springboot.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * REST API Product Service App
 *
 * https://www.tutorialspoint.com/spring_boot/spring_boot_exception_handling.htm
 */

@SpringBootApplication
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }
}
