package com.feige.savememory.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.feige.savememory.baseresponse.Result;
import com.feige.savememory.entity.Todo;
import com.feige.savememory.service.ITodoService;
import com.feige.savememory.handler.util.JwtUtil;
import com.feige.savememory.vo.AddTodo;
import com.feige.savememory.vo.UpdateTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author feige
 * @since 2023-09-29
 */
@CrossOrigin
@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private ITodoService todoService;

    @PostMapping("/add")
    public Result<?> add(@RequestHeader String token, @RequestBody AddTodo todo){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                Todo res = todoService.addTodo(keyWord.getClaim("uid").asLong(),todo);
                return Result.success(res);
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");
    }

    @GetMapping("/list")
    public Result<?> list(@RequestHeader String token){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                List<Todo> list = todoService.getListById(keyWord.getClaim("uid").asLong());
                return Result.success(list);
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");
    }

    @DeleteMapping("/delete")
    public Result<?> delete(@RequestHeader String token, @RequestBody String tid){
        JSONObject jsonObject = JSON.parseObject(tid);
        Long tid1 = Long.valueOf(jsonObject.getString("tid"));
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                Todo res = todoService.deleteTodo(keyWord.getClaim("uid").asLong(),tid1);
                return Result.success(res);
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");
    }

    @PatchMapping("/update")
    public Result<?> update(@RequestHeader String token, @RequestBody UpdateTodo updateTodo){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                Todo res = todoService.updateTodo(keyWord.getClaim("uid").asLong(),updateTodo);
                return Result.success(res);
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");
    }


}
