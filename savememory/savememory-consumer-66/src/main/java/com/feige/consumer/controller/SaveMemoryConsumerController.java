package com.feige.consumer.controller;

import com.feige.savememory.baseresponse.Result;
import com.feige.savememory.entityservice.EntityClientService;
import com.feige.savememory.vo.Identify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SaveMemoryConsumerController {

    @Autowired
    private EntityClientService entityClientService;



    //private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";


    @PutMapping ("/user/identify")
    public Result<?> UserIdentify(@RequestHeader("token") String token, @RequestBody Identify info) {
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
