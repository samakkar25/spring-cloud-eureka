package com.baeldung.spring.cloud.spring_cloud_eureka_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@EnableEurekaClient
@RestController
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
 
    @RequestMapping("/greeting")
    public String greeting() {
        return "Hello from EurekaClient!";
    }
}