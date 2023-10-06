package com.feige.savememory;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//Ribbon 和 Eureka 集合以后，客户端可以直接调用，不用加ip地址
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.feige.savememory"})
public class SaveMemoryConsumer_66 {
    public static void main(String[] args) {
        SpringApplication.run(SaveMemoryConsumer_66.class,args);
    }
}