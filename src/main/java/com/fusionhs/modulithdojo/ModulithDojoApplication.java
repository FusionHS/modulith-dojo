package com.fusionhs.modulithdojo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ModulithDojoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModulithDojoApplication.class, args);
    }

}
