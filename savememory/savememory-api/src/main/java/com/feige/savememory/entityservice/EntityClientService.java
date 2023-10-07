package com.feige.savememory.entityservice;

import com.feige.savememory.baseresponse.Result;
import com.feige.savememory.vo.Identify;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "savememory-providers"/*,fallbackFactory = EntityClientServiceFallbackFactory.class*/)
public interface EntityClientService {
    @PutMapping("/user/identify")
    Result<?> UserIdentify (@RequestHeader("token") String token, @RequestBody Identify info);
}
