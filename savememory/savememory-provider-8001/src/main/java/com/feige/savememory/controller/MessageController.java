package com.feige.savememory.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.feige.savememory.baseresponse.Result;
import com.feige.savememory.service.IMessageService;
import com.feige.savememory.util.JwtUtil;
import com.feige.savememory.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author feige
 * @since 2023-09-29
 */
@RestController
@CrossOrigin
public class MessageController {

    @Autowired
    private IMessageService messageService;

    @PostMapping("/sendmsg")
    public Result<?> sendMessage(@RequestHeader("token") String token, @RequestBody MessageVo messageVo){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                Map<String, Object> data = messageService.addMessage(keyWord.getClaim("uid").asLong(),messageVo);
                if(data!=null) {
                    return Result.success(data, "success");
                }return Result.fail(403,"消息发送失败");
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");
    }

    @GetMapping("/getmsglist/{to_user_id}")
    public Result<?> getMsgList(@RequestHeader("token") String token,@PathVariable("to_user_id") Long toUserId ){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                ArrayList<Map<String, Object>> data = messageService.messageList(keyWord.getClaim("uid").asLong(),toUserId);
                if(data!=null) {
                    return Result.success(data, "success");
                }return Result.fail(403,"消息列表获取失败");
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");
    }

}
