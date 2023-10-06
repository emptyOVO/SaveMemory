package com.feige.savememory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableEurekaClient//自动在服务启动后自动注册到eureka
@EnableDiscoveryClient//服务发现
@EnableScheduling
@MapperScan("com.feige.*.mapper")
public class SaveMemoryApplication_8002 {
    public static void main(String[] args) {
        SpringApplication.run(SaveMemoryApplication_8002.class, args);
    }
    @Bean
    public PasswordEncoder passwordEncoder02(){
        return new BCryptPasswordEncoder();
    }

}
