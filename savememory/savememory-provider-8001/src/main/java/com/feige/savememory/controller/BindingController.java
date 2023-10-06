package com.feige.savememory.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.feige.savememory.baseresponse.Result;
import com.feige.savememory.entity.Binding;
import com.feige.savememory.entity.User;
import com.feige.savememory.service.IBindingService;
import com.feige.savememory.service.IUserService;
import com.feige.savememory.util.JwtUtil;
import com.feige.savememory.vo.BindUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class BindingController {

    @Autowired
    private IBindingService bindingService;

    @Autowired
    private IUserService userService;

    @PostMapping("/bind")
    public Result<?> addBinding(@RequestHeader String token, @RequestBody BindUser bindUser){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("1")) {
                User user = userService.selectByUsername(bindUser.getUsername());
                boolean res = bindingService.addBinding(user.getUid(),keyWord.getClaim("uid").asLong());
                HashMap<String, Object> data = new HashMap<>();
                data.put("uid",user.getUid());
                data.put("username",user.getUsername());
                data.put("phone",user.getPhone());
                data.put("realname",user.getRealname());
                data.put("identity",user.getIdentity());
                data.put("bindphone",user.getBindphone());
                if(res) {
                    return Result.success(data);
                }return Result.fail(403,"绑定失败");
            }return Result.fail(402,"账号被封锁或用户身份有误");
        }return Result.fail(401,"未接收到token");
    }

    @DeleteMapping("/bind")
    public Result<?> deleteBinding(@RequestHeader String token,@RequestBody String uid) {
        JSONObject jsonObject = JSON.parseObject(uid);
        Long resUid = jsonObject.getLong("uid");
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("1")) {
                User user = userService.selectById(keyWord.getClaim("uid").asLong());
                HashMap<String, Object> data = new HashMap<>();
                data.put("uid",user.getUid());
                data.put("username",user.getUsername());
                boolean res = bindingService.deleteBinding(keyWord.getClaim("uid").asLong(),resUid);
                if(res) {
                    return Result.success(data);
                }return Result.fail(403,"删除失败");
            }return Result.fail(402,"账号被封锁或用户身份有误");
        }return Result.fail(401,"未接收到token");
    }
}
