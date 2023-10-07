package com.feige.savememory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard //开启监控页面
public class EntityConsumerDashboard_9001 {
    public static void main(String[] args) {
        SpringApplication.run(EntityConsumerDashboard_9001.class,args);
    }

}
