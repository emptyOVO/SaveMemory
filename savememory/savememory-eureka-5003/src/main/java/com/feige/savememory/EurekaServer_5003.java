package com.feige.savememory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer_5003 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer_5003.class,args);
    }

}
