package com.feige.savememory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer//EnableEurekaServer 服务器启动类，可以接受别人注册进来
public class EurekaServer_5001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer_5001.class,args);
    }
}
