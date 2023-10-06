package com.feige.savememory.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.feige.savememory.baseresponse.Result;
import com.feige.savememory.entity.Diary;
import com.feige.savememory.entity.Image;
import com.feige.savememory.entity.Memory;
import com.feige.savememory.entity.Todo;
import com.feige.savememory.service.*;
import com.feige.savememory.util.JwtUtil;
import com.feige.savememory.util.PictureUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
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
public class MemoryController {

    @Autowired
    private IMemoryService memoryService;

    @Autowired
    private PictureUploadUtil pictureUploadUtil;

    @Autowired
    private IImageService imageService;

    @Autowired
    private IDiaryService diaryService;

    @Autowired
    private ITodoService todoService;

    @Autowired
    private IBindingService bindingService;

    public ArrayList<Map<String, Object>> UploadPictureList(MultipartFile[] files,Memory memory,DecodedJWT keyWord){
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            HashMap<String, Object> diary_images = new HashMap<>();
            String picUrl = pictureUploadUtil.PictureUpload(files[i]);
            Image image = imageService.addMemoryPic(picUrl, keyWord.getClaim("uid").asLong(), memory.getMeid());
            diary_images.put("imid",image.getImid());
            diary_images.put("image",image.getImage());
            maps.add(diary_images);
        }return maps;
    }

    private Map<String,Object> returnData(Memory memory,DecodedJWT keyWord){
        HashMap<String, Object> data = new HashMap<>();
        data.put("meid",memory.getMeid());
        data.put("pid",memory.getPid());
        data.put("datetime",memory.getDatetime());
        data.put("uid",keyWord.getClaim("uid").asLong());
        data.put("profile",memory.getProfile());
        data.put("detail",memory.getDetail());
        return data;
    }


    @PostMapping("/memory/pupload")
    public Result<?> pUploadMemory(@RequestHeader String token, @RequestParam("pictures")MultipartFile[] files
           , @RequestParam("datetime")String date,@RequestParam("detail") String detail) throws ParseException {
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                Memory memory = memoryService.addMemory(keyWord.getClaim("uid").asLong(),date,detail);
                Map<String, Object> data = returnData(memory, keyWord);
                if(files!=null){
                    ArrayList<Map<String, Object>> maps = UploadPictureList(files, memory, keyWord);
                    data.put("images",maps);
                }else {data.put("images",null);}
                return Result.success(data);
            }return Result.fail(402,"账号被封锁或用户为子女");
        }return Result.fail(401,"未接收到token");
    }

    @PatchMapping("/memory/pupdate")
    public Result<?> pUpdateMemory(@RequestHeader String token, @RequestParam("pictures")MultipartFile[] files
            , @RequestParam("datetime")String date,@RequestParam("detail") String detail
            ,@RequestParam("meid") Long meid) throws ParseException {
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                Memory memory = memoryService.pUpdMemory(keyWord.getClaim("uid").asLong(),meid,date,detail);
                Map<String, Object> data = returnData(memory,keyWord);
                if(files!=null){
                    ArrayList<Map<String, Object>> maps = UpdPictureList(files, memory, keyWord);
                    data.put("images",maps);
                }else {data.put("images",null);}
                return Result.success(data);
            }return Result.fail(402,"账号被封锁或用户为子女");
        }return Result.fail(401,"未接收到token");
    }

    private ArrayList<Map<String, Object>> UpdPictureList(MultipartFile[] files, Memory memory, DecodedJWT keyWord) {
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        imageService.deleteMemoryPic(memory.getMeid());
        for (int i = 0; i < files.length; i++) {
            HashMap<String, Object> diary_images = new HashMap<>();
            String picUrl = pictureUploadUtil.PictureUpload(files[i]);
            Image image = imageService.addMemoryPic(picUrl, keyWord.getClaim("uid").asLong(), memory.getMeid());
            diary_images.put("imid",image.getImid());
            diary_images.put("image",image.getImage());
            maps.add(diary_images);
        }return maps;
    }

    @DeleteMapping("/memory/pdelete")
    public Result<?> deleteMemory(@RequestHeader String token,@RequestBody String meid){
        JSONObject jsonObject = JSON.parseObject(meid);
        Long toMeid = jsonObject.getLong("meid");
        if(token!=null){
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")) {
                ArrayList<Map<String, Object>> imageList = imageService.selectByMemoryId(toMeid);
                HashMap<String, Object> data = new HashMap<>();
                data.put("meid",toMeid);
                data.put("images",imageList);
                imageService.deleteMemoryPic(toMeid);
                boolean res = memoryService.deleteMemoryById(toMeid,keyWord.getClaim("uid").asLong());
                if(res){
                    return Result.success(data);
                }
            }return Result.fail(402,"账号被封锁或用户为子女");
        }return Result.fail(401,"未接收到token");
    }

    @GetMapping("/pmemory")
    public Result<?> getPMemory(@RequestHeader String token){
        if(token!=null){
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if(!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("0")){
                List<Diary> diaryList = diaryService.selectDiaryByUid(keyWord.getClaim("uid").asLong());
                HashMap<String, Object> data = new HashMap<>();
                ArrayList<Map<String, Object>> diary_axis = new ArrayList<>();
                for (Diary value : diaryList) {
                    Map<String, Object> diary = diaryService.getDiary(value.getDid());
                    diary_axis.add(diary);
                }
                data.put("diary_axis",diary_axis);
                List<Todo> todoList = todoService.getListById(keyWord.getClaim("uid").asLong());
                ArrayList<Map<String, Object>> memory_axis = memoryService.getMemoryList(keyWord.getClaim("uid").asLong());
                data.put("memory_axis",memory_axis);
                data.put("todo_axis",todoList);
                return Result.success(data);
            }return Result.fail(402,"账号被封锁或用户为子女");
        }return Result.fail(401,"未接收到token");
    }

    @GetMapping("/memory/{pid}")
    public Result<?> childGetMemory(@RequestHeader String token,@PathVariable("pid") String pid1){
        if(token!=null){
            Long pid = Long.valueOf(pid1);
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if(!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("1")){
                boolean res = bindingService.selectRelationShip(keyWord.getClaim("uid").asLong(), pid);
                if(res) {
                    List<Diary> diaryList = diaryService.selectDiaryByUid(pid);
                    HashMap<String, Object> data = new HashMap<>();
                    ArrayList<Map<String, Object>> diary_axis = new ArrayList<>();
                    for (Diary value : diaryList) {
                        Map<String, Object> diary = diaryService.getDiary(value.getDid());
                        diary_axis.add(diary);
                    }
                    data.put("diary_axis", diary_axis);
                    List<Todo> todoList = todoService.getListById(pid);
                    ArrayList<Map<String, Object>> memory_axis = memoryService.getMemoryList(pid);
                    data.put("memory_axis", memory_axis);
                    data.put("todo_axis", todoList);
                    return Result.success(data);
                }return Result.fail(403,"未与目标用户亲子绑定");
            }return Result.fail(402,"账号被封锁或用户为父母");
        }return Result.fail(401,"未接收到token");
    }



    @PostMapping("/memory/cupload")
    public Result<?> pUploadMemory(@RequestHeader String token, @RequestParam("pictures")MultipartFile[] files
            , @RequestParam("datetime")String date,@RequestParam("detail") String detail,@RequestParam("pid") Long pid) throws ParseException {
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("1")) {
                boolean res = bindingService.selectRelationShip(keyWord.getClaim("uid").asLong(), pid);
                if(res) {
                    Memory memory = memoryService.childAddMemory(pid,keyWord.getClaim("uid").asLong(), date, detail);
                    Map<String, Object> data = returnData(memory, keyWord);
                    if (files != null) {
                        ArrayList<Map<String, Object>> maps = ChildUploadPictureList(files, memory, keyWord);
                        data.put("images", maps);
                        return Result.success(data);
                    } else {
                        data.put("images", null);
                        return Result.success(data);
                    }
                }return Result.fail(403,"未与目标用户亲子绑定");
            }return Result.fail(402,"账号被封锁或用户为父母");
        }return Result.fail(401,"未接收到token");
    }



    @DeleteMapping("/memory/cdelete")
    public Result<?> childDeleteMemory(@RequestHeader String token,@RequestBody String meid){
        JSONObject jsonObject = JSON.parseObject(meid);
        Long toMeid = jsonObject.getLong("meid");
        if(token!=null){
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("1")) {
                ArrayList<Map<String, Object>> imageList = imageService.selectByMemoryId(toMeid);
                HashMap<String, Object> data = new HashMap<>();
                data.put("meid",toMeid);
                data.put("images",imageList);
                imageService.deleteMemoryPic(toMeid);
                boolean res = memoryService.deleteMemoryById(toMeid,keyWord.getClaim("uid").asLong());
                if(res){
                    return Result.success(data);
                }
            }return Result.fail(402,"账号被封锁或用户为父母");
        }return Result.fail(401,"未接收到token");
    }

    @PatchMapping("/memory/cupdate")
    public Result<?> cUpdateMemory(@RequestHeader String token, @RequestParam("pictures")MultipartFile[] files
            , @RequestParam("datetime")String date,@RequestParam("detail") String detail
            ,@RequestParam("meid") Long meid,@RequestParam("pid") Long pid) throws ParseException {
        if(token!=null) {
            DecodedJWT keyWord = JwtUtil.parseToken(token);
            if (!keyWord.getClaim("blocked").asBoolean()&&keyWord.getClaim("identity").asString().equals("1")) {
                Memory memory = memoryService.cUpdMemory(keyWord.getClaim("uid").asLong(),pid,meid,date,detail);
                Map<String, Object> data = returnData(memory,keyWord);

                if(files!=null){
                    ArrayList<Map<String, Object>> maps = ChildUploadPictureList(files, memory, keyWord);
                    System.out.println(maps);
                    data.put("images",maps);
                }else {data.put("images",null);}
                return Result.success(data);

            }return Result.fail(402,"账号被封锁或用户为父母");
        }return Result.fail(401,"未接收到token");
    }

    private ArrayList<Map<String, Object>> ChildUploadPictureList(MultipartFile[] files, Memory memory, DecodedJWT keyWord) {
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        imageService.deleteMemoryPic(memory.getMeid());
        for (MultipartFile file : files) {
            HashMap<String, Object> diary_images = new HashMap<>();
            String picUrl = pictureUploadUtil.PictureUpload(file);
            Image image = imageService.childAddMemoryPic(picUrl, keyWord.getClaim("uid").asLong(), memory.getMeid());

            diary_images.put("imid", image.getImid());
            diary_images.put("image", image.getImage());
            maps.add(diary_images);
        }
        return maps;
    }

}
