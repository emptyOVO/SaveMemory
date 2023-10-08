package com.feige.savememory.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.feige.savememory.baseresponse.Result;
import com.feige.savememory.entity.AdrTimeLine;
import com.feige.savememory.entity.User;
import com.feige.savememory.service.IAdrTimeLineService;
import com.feige.savememory.util.JwtUtil;
import com.feige.savememory.vo.AdrTimeLineUpdVo;
import com.feige.savememory.vo.AdrTimeLineVo;
import com.feige.savememory.vo.BindAdrTimeLineVo;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/addresslist")
@CrossOrigin
public class AdrTimeLineController {

    @Autowired
    private IAdrTimeLineService adrTimeLineService;

    @PostMapping("/addtimeline")
    public Result<?> addTimeLine(@RequestHeader("token") String token, @RequestBody AdrTimeLineVo adrTimeLineVo){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                Map<String,Object> data = adrTimeLineService.addAdrTimeLine(keyWord.getClaim("uid").asLong(),adrTimeLineVo);
                return Result.success(data);
            }return Result.fail(402,"账号被封锁或用户身份有误");
        }return Result.fail(401,"未接收到token");
    }

    @DeleteMapping("/deletetimeline")
    public Result<?> addTimeLineDelete(@RequestHeader("token") String token, @RequestBody String adrtlid){
        JSONObject jsonObject = JSON.parseObject(adrtlid);
        Long adrtlid1 = jsonObject.getLong("adrtlid");
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                Map<String,Object> data = adrTimeLineService.addAdrTimeLineDelete(adrtlid1);
                return Result.success(data);
            }return Result.fail(402,"账号被封锁或用户身份有误");
        }return Result.fail(401,"未接收到token");
    }

    @PostMapping("/bindtimeline")
    public Result<?> bindTimeLine(@RequestHeader("token") String token, @RequestBody BindAdrTimeLineVo bindAdrTimeLineVo){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                Map<String,Object> data = adrTimeLineService.bindAdrTimeLine(keyWord.getClaim("uid").asLong(),bindAdrTimeLineVo);
                return Result.success(data);
            }return Result.fail(402,"账号被封锁或用户身份有误");
        }return Result.fail(401,"未接收到token");
    }

    @PutMapping("/updtimeline")
    public Result<?> addTimeLineUpd(@RequestHeader("token") String token, @RequestBody AdrTimeLineUpdVo adrTimeLineUpdVo){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                Map<String,Object> data = adrTimeLineService.addAdrTimeLineUpd(adrTimeLineUpdVo);
                return Result.success(data);
            }return Result.fail(402,"账号被封锁或用户身份有误");
        }return Result.fail(401,"未接收到token");
    }



}
