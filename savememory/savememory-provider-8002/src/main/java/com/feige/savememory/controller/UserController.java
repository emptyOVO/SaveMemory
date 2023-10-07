package com.feige.savememory.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.feige.savememory.baseresponse.Result;
import com.feige.savememory.entity.Binding;
import com.feige.savememory.entity.User;
import com.feige.savememory.service.IBindingService;
import com.feige.savememory.service.IUserService;
import com.feige.savememory.handler.util.AliyunSmsUtil;
import com.feige.savememory.handler.util.CaptchaGeneratorUtil;
import com.feige.savememory.handler.util.JwtUtil;
import com.feige.savememory.vo.ForgetUser;
import com.feige.savememory.vo.Identify;
import com.feige.savememory.vo.LoginUser;
import com.feige.savememory.vo.UserReg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

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
public class UserController {

    @Autowired
    private IBindingService bindingService;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private IUserService userService;



    //test
    /*@GetMapping("/user/getall")
    public List<User> getAll() {
        return userService.getUserList();
    }*/

    //发送手机验证码
    @PostMapping("/captcha/{phone_number}")
    public Result<?> sendCaptcha(@PathVariable("phone_number") String phone) throws Exception {
        String isExit = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(isExit)) {
            return Result.fail(200, "phoneNumber exits,do not try again");
        } else {
            HashMap<String, String> code = new HashMap<>();
            String captcha = CaptchaGeneratorUtil.creatCaptcha();
            code.put("code", captcha);
            boolean result = AliyunSmsUtil.SendCaptcha(phone, code);
            if (result) {
                redisTemplate.opsForValue().set(phone, captcha, 1, TimeUnit.MINUTES);
                return Result.success(null, "captcha sending success!");
            }
            return Result.fail(400, "captcha sending fail");
        }
    }

    //UserRegister
    @PostMapping("/user/register")
    public Result<?> userRegister(@RequestBody UserReg userReg){
        Object sendCaptcha = redisTemplate.opsForValue().get(userReg.getPhone());
        if(Objects.nonNull(sendCaptcha) && sendCaptcha.equals(userReg.getcaptcha())){
            redisTemplate.delete(userReg.getPhone());
            User user = userService.addUser(userReg);
            String token = JwtUtil.getToken(user);
            redisTemplate.opsForValue().set(String.valueOf(user.getUid()), token, 5, TimeUnit.DAYS);
            Map<String, Object> data = new HashMap<>();
            data.put("token",token);
            data.put("username",user.getUsername());
            data.put("uid",user.getUid());
            data.put("identity",user.getIdentity());
            data.put("phone",user.getPhone());
            return Result.success(data,"注册成功");
        } else {
            return Result.fail(400,"注册失败");
        }
    }

    @PostMapping("/user/login")
    public Result<Map<String,Object>> login(@RequestBody LoginUser loginUser){
        Map<String, Object> data = userService.login(loginUser);
        if(data!=null){
            return Result.success(data);
        }else {
            return Result.fail(400,"用户名或密码错误");
        }
    }

    @PutMapping("/user")
    public Result<Map<String,Object>> forgetpwd(@RequestBody ForgetUser forgetuser) {
        User user = userService.selectByUsername(forgetuser.getUsername());
        Object sendCaptcha = redisTemplate.opsForValue().get(user.getPhone());
        if (Objects.nonNull(sendCaptcha) && sendCaptcha.equals(forgetuser.getcaptcha())) {
            Map<String, Object> data = userService.forgetpwd(forgetuser);
            redisTemplate.delete(user.getPhone());
            if (data != null) {
                return Result.success(data);
            }return Result.fail(400, "验证码错误，修改失败");
        }return Result.fail(405,"请求错误");
    }

    @GetMapping("/user/info")
    public Result<?> userInfo(@RequestHeader String token){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                User user = userService.selectByUsername(keyWord.getClaim("username").asString());
                return Result.success(user);
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");
    }

    @PatchMapping("/user/updpwd")
    public Result<?> updatePassword(@RequestHeader String token,@RequestBody LoginUser loginUser){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                Map<String,Object> data = userService.updpwd(loginUser.getPassword(),keyWord.getClaim("uid").asLong());
                return Result.success(data,"修改密码成功");
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");
    }

    @PatchMapping("/user/updnn")
    public Result<?> updateNickname(@RequestHeader String token,@RequestBody String nickname){
        JSONObject jsonObject = JSON.parseObject(nickname);
        String ToNickname = jsonObject.getString("nickname");
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                Map<String,Object> data = userService.updnn(ToNickname,keyWord.getClaim("uid").asLong());
                return Result.success(data);
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");
    }


    @PutMapping("/user/identify")
    public Result<?> UserIdentify(@RequestHeader String token,@RequestBody Identify info){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                Map<String, Object> data = userService.updateUserInfo(keyWord.getClaim("uid").asLong(),info);
                if(data!=null) {
                    return Result.success(data, "success");
                }return Result.fail(403,"不合法的身份证号");
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");
    }


    @GetMapping("/sos")
    public Result<?> getSosNum(@RequestHeader String token){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                Map<String, Object> data = userService.sosInfo(keyWord.getClaim("uid").asLong());
                if(data!=null) {
                    return Result.success(data, "success");
                }return Result.fail(403,"未查询到一键求助号码");
            }return Result.fail(402,"账号被封锁或用户为子女");
        }return Result.fail(401,"未接收到token");
    }

    @PostMapping("/sos")
    public Result<?> postSosNum(@RequestHeader String token,@RequestBody String phone){
        JSONObject jsonObject = JSON.parseObject(phone);
        String ToPhone = jsonObject.getString("phone");
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                Map<String, Object> data = userService.addSos(keyWord.getClaim("uid").asLong(),ToPhone);
                if(data!=null) {
                    return Result.success(data, "success");
                }return Result.fail(403,"未查询到一键求助号码");
            }return Result.fail(402,"账号被封锁或用户为子女");
        }return Result.fail(401,"未接收到token");
    }

    @PatchMapping("/sos")
    public Result<?> updateSosNum(@RequestHeader String token,@RequestBody String phone){
        JSONObject jsonObject = JSON.parseObject(phone);
        String ToPhone = jsonObject.getString("phone");
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                Map<String, Object> data = userService.updateBindPhone(keyWord.getClaim("uid").asLong(),ToPhone);
                if(data!=null) {
                    return Result.success(data, "success");
                }return Result.fail(403,"未查询到一键求助号码");
            }return Result.fail(402,"账号被封锁或用户为子女");
        }return Result.fail(401,"未接收到token");
    }

    @DeleteMapping("/sos")
    public Result<?> deleteSosNum(@RequestHeader String token){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                Map<String, Object> data = userService.deleteBindPhone(keyWord.getClaim("uid").asLong());
                if(data!=null) {
                    return Result.success(data, "success");
                }return Result.fail(403,"未查询到一键求助号码");
            }return Result.fail(402,"账号被封锁或用户为子女");
        }return Result.fail(401,"未接收到token");
    }



    @GetMapping("/parent/info")
    public Result<?> parentInfo(@RequestHeader String token){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                List<Binding> bindings = bindingService.getParentId(keyWord.getClaim("uid").asLong());
                ArrayList<Map<String, Object>> list = new ArrayList<>();
                for (Binding binding : bindings) {
                    Map<String, Object> data = userService.getInfoById(binding.getParentid());
                    list.add(data);
                }
                return Result.success(list);
            }return Result.fail(402,"账号被封锁或用户身份有误");
        }return Result.fail(401,"未接收到token");
    }
}
