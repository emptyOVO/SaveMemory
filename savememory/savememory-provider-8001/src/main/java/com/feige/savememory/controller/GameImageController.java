package com.feige.savememory.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.feige.savememory.baseresponse.Result;
import com.feige.savememory.entity.GameImage;
import com.feige.savememory.entity.Memory;
import com.feige.savememory.service.IBindingService;
import com.feige.savememory.service.IGameImageService;
import com.feige.savememory.util.JwtUtil;
import com.feige.savememory.util.PictureUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class GameImageController {

    @Autowired
    private IGameImageService gameImageService;


    @Autowired
    private IBindingService bindingService;

    @PostMapping("/game")
    public Result<?> addGameImage(@RequestHeader("token") String token, @RequestParam("realname") String realname
            , @RequestParam("nickname") String nickname, @RequestParam("pictures")MultipartFile[] files
            ,@RequestParam("uid")Long uid){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("1")) {
                boolean res = bindingService.selectRelationShip(keyWord.getClaim("uid").asLong(), uid);
                if(res) {
                    List<GameImage> gameImageList = gameImageService.AddGameImages(files,uid,keyWord.getClaim("uid").asLong(), nickname, realname);
                    return Result.success(gameImageList);
                }return Result.fail(403,"未与目标用户亲子绑定");
            }return Result.fail(402,"账号被封锁或用户为父母");
        }return Result.fail(401,"未接收到token");
    }

    @GetMapping("game/info/{uid}")
    public Result<?> gameInfo(@RequestHeader("token") String token,@PathVariable("uid") String uid1){
        if(token!=null) {
            Long uid = Long.valueOf(uid1);
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("1")) {
                boolean res = bindingService.selectRelationShip(keyWord.getClaim("uid").asLong(), uid);
                if(res) {
                    List<GameImage> gameImageList = gameImageService.selectGameImages(uid);
                    return Result.success(gameImageList);
                }return Result.fail(403,"未与目标用户亲子绑定");
            }return Result.fail(402,"账号被封锁或用户为父母");
        }return Result.fail(401,"未接收到token");
    }

    @PutMapping("/game")
    public Result<?> updGameImage(@RequestHeader("token") String token, @RequestParam("realname") String realname
            , @RequestParam("nickname") String nickname, @RequestParam("picture")MultipartFile file
            ,@RequestParam("gid")Long gid){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("1")) {
                GameImage gameImage = gameImageService.updGameImage(realname,nickname,file,gid);
                return Result.success(gameImage);
            }return Result.fail(402,"账号被封锁或用户为父母");
        }return Result.fail(401,"未接收到token");
    }

    @DeleteMapping("game/{gid}")
    public Result<?> deleteGameImage(@RequestHeader("token") String token,@PathVariable("gid") String gid1){
        Long gid = Long.valueOf(gid1);
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("1")) {
                GameImage gameImage = gameImageService.deleteGameImage(gid);
                return Result.success(gameImage);
            }return Result.fail(402,"账号被封锁或用户为父母");
        }return Result.fail(401,"未接收到token");
    }

    @GetMapping("/play/pic")
    public Result<?> playPic(@RequestHeader("token") String token){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                Map<String,Object> gameImage = gameImageService.getGameImage(keyWord.getClaim("uid").asLong());
                if(gameImage!=null) {
                    return Result.success(gameImage);
                }return Result.fail(403,"未导入游戏资料");
            }return Result.fail(402,"账号被封锁或用户身份出错");
        }return Result.fail(401,"未接收到token");
    }

}
