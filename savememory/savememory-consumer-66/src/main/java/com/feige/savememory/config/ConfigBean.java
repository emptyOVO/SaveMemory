package com.feige.savememory.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {
    @LoadBalanced //Ribbon
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
