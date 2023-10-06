package com.feige.savememory.service;

import com.feige.savememory.baseresponse.Result;
import com.feige.savememory.vo.Identify;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "SAVEMEMORY-PROVIDERS")
public interface EntityClientService {
    @GetMapping("/user/identify")
    Result<?> UserIdentify (@RequestHeader String token, @RequestBody Identify info);
}
