package com.feige.savememory.entityservice;

import com.feige.savememory.baseresponse.Result;
import com.feige.savememory.vo.Identify;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class EntityClientServiceFallbackFactory implements FallbackFactory {

    @Override
    public EntityClientService create(Throwable throwable) {
        return new EntityClientService() {
            @Override
            public Result<?> UserIdentify(String token, Identify info) {
                return Result.success("sdadaw");
            }
        };
    }
}
