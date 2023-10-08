package com.feige.savememory.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.feige.savememory.baseresponse.Result;
import com.feige.savememory.entity.AddressList;
import com.feige.savememory.service.IAddressListService;
import com.feige.savememory.service.IAdrTimeLineService;
import com.feige.savememory.util.JwtUtil;
import com.feige.savememory.vo.AddAddressList;
import com.feige.savememory.vo.UpdateAddressList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;


@RestController
@CrossOrigin
public class AddressListController {

    @Autowired
    private IAddressListService addressListService;

    @Autowired
    private IAdrTimeLineService adrTimeLineService;


    @PostMapping("/addresslist")
    public Result<?> postAddressList(@RequestHeader("token") String token,@RequestBody AddAddressList addAddressList){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                AddressList data = addressListService.addAddressList(addAddressList,keyWord.getClaim("uid").asLong());
                return Result.success(data,"通讯录添加成功");
            }return Result.fail(402,"账号被封锁或用户身份出错");
        }return Result.fail(401,"未接收到token");
    }


    @PutMapping("/addresslist")
    public Result<?> patchAddressList(@RequestHeader("token") String token,@RequestBody UpdateAddressList updateAddressList){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                AddressList data = addressListService.UpdateAddressList(updateAddressList,keyWord.getClaim("uid").asLong());
                return Result.success(data,"通讯录添加成功");
            }return Result.fail(402,"账号被封锁或用户身份出错");
        }return Result.fail(401,"未接收到token");
    }
    @DeleteMapping("/addresslist")
    public Result<?> deleteAddressList(@RequestHeader("token") String token,@RequestBody String aid){
        JSONObject jsonObject = JSON.parseObject(aid);
        Long aid1 = Long.valueOf(jsonObject.getString("aid"));
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                Map<String,Object> data = addressListService.deleteAddressList(aid1);
                return Result.success(data,"通讯录删除成功");
            }return Result.fail(402,"账号被封锁或用户身份出错");
        }return Result.fail(401,"未接收到token");
    }

    @GetMapping("/addresslist/addinfo/{aid}")
    public Result<?> getAAdrlInfo(@RequestHeader("token") String token ,@PathVariable("aid") String aid){
        Long raid = Long.valueOf(aid);
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                Map<String,Object> data = addressListService.getAInfo(raid,keyWord.getClaim("uid").asLong());
                return Result.success(data,"add通讯录详情查询成功");
            }return Result.fail(402,"账号被封锁或用户身份出错");
        }return Result.fail(401,"未接收到token");
    }

    @GetMapping("/addresslist/bindinfo/{bid}")
    public Result<?> getBAdrlInfo(@RequestHeader("token") String token ,@PathVariable("bid") String bid){
        Long rbid = Long.valueOf(bid);
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                Map<String,Object> data = addressListService.getBInfo(rbid,keyWord.getClaim("uid").asLong());
                return Result.success(data,"bind通讯录详情查询成功");
            }return Result.fail(402,"账号被封锁或用户身份出错");
        }return Result.fail(401,"未接收到token");
    }

    @GetMapping("/addresslist")
    public Result<?> getAddressList(@RequestHeader("token") String token){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                Map<String,ArrayList<Map<String, Object>>> data = addressListService.getAdsListInfo(keyWord.getClaim("uid").asLong());
                return Result.success(data,"bind通讯录详情查询成功");
            }return Result.fail(402,"账号被封锁或用户身份出错");
        }return Result.fail(401,"未接收到token");
    }

    @GetMapping("/play/fill")
    public Result<?> fillGame(@RequestHeader("token") String token){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                while (true) {
                    AddressList addressList = addressListService.selectRandomAddressList(keyWord.getClaim("uid").asLong());
                    if (adrTimeLineService.selectTimeLine(addressList.getAid())) {
                        Map<String,Object> data = addressListService.selectAddressListInfo(keyWord.getClaim("uid").asLong(),addressList.getAid());
                        return Result.success(data);
                    }
                }
            }return Result.fail(402,"账号被封锁或用户身份有误");
        }return Result.fail(401,"未接收到token");
    }

}
