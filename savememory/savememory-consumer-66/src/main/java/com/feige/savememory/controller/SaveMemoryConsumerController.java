package com.feige.savememory.controller;

import com.feige.savememory.baseresponse.Result;
import com.feige.savememory.entity.Image;
import com.feige.savememory.service.EntityClientService;
import com.feige.savememory.vo.Identify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SaveMemoryConsumerController {

    @Autowired
    private EntityClientService entityClientService;



    //private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";


    @RequestMapping("/user/identify")
    public Result<?> UserIdentify(@RequestHeader String token, @RequestBody Identify info) {
        return entityClientService.UserIdentify(token,info);
    }

/*
    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        // getForObject(服务提供方地址(接口),返回类型.class)
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
    }

    *//**
     * 消费方查询部门信息列表
     * @return
     *//*
    @RequestMapping("/consumer/dept/list")
    public List<Dept> list() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }*/
}
