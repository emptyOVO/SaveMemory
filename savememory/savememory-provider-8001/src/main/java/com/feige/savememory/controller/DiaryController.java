package com.feige.savememory.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.feige.savememory.baseresponse.Result;
import com.feige.savememory.entity.Diary;
import com.feige.savememory.entity.Image;
import com.feige.savememory.service.IDiaryService;
import com.feige.savememory.service.IImageService;
import com.feige.savememory.util.JwtUtil;
import com.feige.savememory.util.PictureUploadUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class DiaryController {

    @Autowired
    private IDiaryService diaryService;

    @Autowired
    private PictureUploadUtil pictureUploadUtil;

    @Autowired
    private IImageService imageService;


    public ArrayList<Map<String, Object>> UploadPictureList(MultipartFile[] files,Diary diary,DecodedJWT keyWord){
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            HashMap<String, Object> diary_images = new HashMap<>();
            String picUrl = pictureUploadUtil.PictureUpload(files[i]);
            Image image = imageService.addDiaryPic(picUrl, keyWord.getClaim("uid").asLong(), diary.getDid());
            diary_images.put("imid",image.getImid());
            diary_images.put("image",image.getImage());
            maps.add(diary_images);
        }return maps;
    }


    @PostMapping("/diary/write")
    public Result<?> addDiary(@RequestHeader("token") String token
            ,@RequestParam("image") MultipartFile[] files
            ,@RequestParam("title") String title,@RequestParam("text") String text){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                Diary diary = diaryService.addDiary(keyWord.getClaim("uid").asLong(),title,text);
                HashMap<String, Object> data = new HashMap<>();
                data.put("did",diary.getDid());
                data.put("title",diary.getTitle());
                data.put("datetime",diary.getDatetime());
                if(files!=null){
                    ArrayList<Map<String, Object>> maps = UploadPictureList(files, diary, keyWord);
                    data.put("diary_images",maps);
               }else {data.put("diary_images",null);}
                return Result.success(data);
            }return Result.fail(402,"账号被封锁或用户为子女");
        }return Result.fail(401,"未接收到token");
    }

    @DeleteMapping("/diary/{did}")
    public Result<?> deleteDiary(@RequestHeader("token") String token,@PathVariable("did") String did){
        Long did1 = Long.valueOf(did);
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                Map<String, Object> data = diaryService.deleteDiary(keyWord.getClaim("uid").asLong(),did1);
                if(data!=null) {
                    return Result.success(data, "success");
                }return Result.fail(403,"删除日记失败");
            }return Result.fail(402,"账号被封锁或用户为子女");
        }return Result.fail(401,"未接收到token");
    }

    @PutMapping("/diary/update")
    public Result<?> updateDiary(@RequestHeader("token") String token
            ,@RequestParam("title") String title,@RequestParam("text") String text
            ,@RequestParam("image") MultipartFile[] files,@RequestParam("did") String did){
        if(token!=null) {
            Long rdid = Long.valueOf(did);
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            HashMap<String, Object> data = new HashMap<>();
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                imageService.DeleteByDiaryId(rdid);
                Diary diary = diaryService.updateDiary(text,title,rdid);
                data.put("did",diary.getDid());
                data.put("title",diary.getTitle());
                data.put("datetime",diary.getDatetime());
                if(files!=null) {
                    ArrayList<Map<String, Object>> maps = UploadPictureList(files, diary, keyWord);
                    data.put("diary_images",maps);
                }else {data.put("diary_images",null);}
                return Result.success(data);
            }return Result.fail(402,"账号被封锁或用户为子女");
        }return Result.fail(401,"未接收到token");
    }

    @GetMapping("/diary/{did}")
    public Result<?> getDiaryDetail(@RequestHeader("token") String token,@PathVariable("did") String did){
        Long rdid = Long.valueOf(did);
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()) {
                Map<String, Object> data = diaryService.getDiary(rdid);
                if(data!=null) {
                    return Result.success(data, "success");
                }return Result.fail(403,"获取日记详情失败");
            }return Result.fail(402,"账号被封锁");
        }return Result.fail(401,"未接收到token");
    }

    @GetMapping("/diary/list")
    public Result<?> diaryList(@RequestHeader("token") String token){
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                ArrayList<Map<String, Object>> data = diaryService.getDiaryList(keyWord.getClaim("uid").asLong());
                if(data!=null) {
                    return Result.success(data, "success");
                }return Result.fail(403,"获取日记list失败");
            }return Result.fail(402,"账号被封锁或用户为子女");
        }return Result.fail(401,"未接收到token");
    }

    @GetMapping("/diary/list/{pid}")
    public Result<?> childGetDiaryList(@RequestHeader("token") String token,@PathVariable("pid") String pid1){
        Long pid = Long.valueOf(pid1);
        if(token!=null){
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if(!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("1")){
                List<Map<String,Object>> data = diaryService.childGetDiaryList(keyWord.getClaim("uid").asLong(),pid);
                if(data!=null) {
                    return Result.success(data, "success");
                }return Result.fail(403,"获取日记list失败");
            }return Result.fail(402,"账号被封锁或用户为父母");
        }return Result.fail(401,"未接收到token");
    }

}
