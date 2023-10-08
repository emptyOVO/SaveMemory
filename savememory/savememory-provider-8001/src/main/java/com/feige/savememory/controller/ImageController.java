package com.feige.savememory.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.feige.savememory.baseresponse.Result;
import com.feige.savememory.entity.AddressList;
import com.feige.savememory.entity.Image;
import com.feige.savememory.service.IAddressListService;
import com.feige.savememory.service.IImageService;
import com.feige.savememory.service.IUserService;
import com.feige.savememory.util.JwtUtil;
import com.feige.savememory.util.PictureUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

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
public class ImageController {
    @Autowired
    private PictureUploadUtil pictureUploadUtil;

    @Autowired
    private IUserService userService;

    @Autowired
    private IImageService imageService;

    @Autowired
    private IAddressListService addressListService;

    @PostMapping("/user/profile")
    public Result<?> uploadUserProfile(@RequestParam("profile") MultipartFile file, @RequestHeader("token") String token) {
        if (token != null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                //封装工具类上传图片
                String url = pictureUploadUtil.PictureUpload(file);
                userService.updProfile(url,keyWord.getClaim("uid").asLong());
                //在图片表中新建头像图片储存
                Image image = imageService.addOwnProfilePic(url,keyWord.getClaim("uid").asLong());
                if(image!=null) {
                    return Result.success(image, "头像上传成功");
                }return Result.fail(403,"头像上传失败");

            } return Result.fail(402, "账号被封锁");
        }return Result.fail(401, "未接收到token");
    }

    @PostMapping("/addresslist/profile")
    public Result<?> uploadAddressProfile(@RequestParam("profile") MultipartFile file
            , @RequestHeader("token") String token,@RequestParam("aid") String aid) {
        Long raid = Long.valueOf(aid);
        if (token != null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                //封装工具类上传图片
                String url = pictureUploadUtil.PictureUpload(file);
                AddressList addressList = addressListService.updProfile(url,aid);
                //在图片表中新建头像图片储存
                Image image = imageService.addAddressListProfilePic(url,keyWord.getClaim("uid").asLong(),raid);
                if(image!=null) {
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("aid",addressList.getAid());
                    data.put("uid",addressList.getUid());
                    data.put("realname",addressList.getRealname());
                    data.put("nickname",addressList.getNickname());
                    data.put("phone",addressList.getPhone());
                    data.put("profile",addressList.getProfile());
                    return Result.success(data, "头像上传成功");
                }return Result.fail(403,"头像上传失败");
            } return Result.fail(402, "账号被封锁");
        }return Result.fail(401, "未接收到token");
    }

}
