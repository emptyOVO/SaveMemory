package com.feige.consumer.controller;

import com.feige.savememory.baseresponse.Result;
import com.feige.savememory.entityservice.EntityClientService;
import com.feige.savememory.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@CrossOrigin
public class SaveMemoryConsumerController {

    @Autowired
    private EntityClientService entityClientService;

    @PostMapping("/addresslist")
    public Result<?> postAddressList(@RequestHeader String token,@RequestBody AddAddressList addAddressList){
        return entityClientService.postAddressList(token,addAddressList);
    }

    @PutMapping("/addresslist")
    public Result<?> patchAddressList(@RequestHeader String token,@RequestBody UpdateAddressList updateAddressList){
        return entityClientService.patchAddressList(token,updateAddressList);
    }

    @DeleteMapping("/addresslist")
    public Result<?> deleteAddressList(@RequestHeader String token,@RequestBody String aid){
        return entityClientService.deleteAddressList(token,aid);
    }

    @GetMapping("/addresslist/addinfo/{aid}")
    public Result<?> getAAdrlInfo(@RequestHeader String token ,@PathVariable("aid") String aid){
        return entityClientService.getAAdrlInfo(token,aid);
    }

    @GetMapping("/addresslist/bindinfo/{bid}")
    public Result<?> getBAdrlInfo(@RequestHeader String token ,@PathVariable("bid") String bid){
        return entityClientService.getBAdrlInfo(token,bid);
    }

    @GetMapping("/addresslist")
    public Result<?> getAddressList(@RequestHeader String token){
        return entityClientService.getAddressList(token);
    }

    @GetMapping("/play/fill")
    public Result<?> fillGame(@RequestHeader String token){
        return entityClientService.fillGame(token);
    }

    @PostMapping("/addtimeline")
    public Result<?> addTimeLine(@RequestHeader String token, @RequestBody AdrTimeLineVo adrTimeLineVo){
        return entityClientService.addTimeLine(token,adrTimeLineVo);
    }

    @DeleteMapping("/deletetimeline")
    public Result<?> addTimeLineDelete(@RequestHeader String token, @RequestBody String adrtlid){
        return entityClientService.addTimeLineDelete(token,adrtlid);
    }

    @PostMapping("/bindtimeline")
    public Result<?> bindTimeLine(@RequestHeader String token, @RequestBody BindAdrTimeLineVo bindAdrTimeLineVo){
        return entityClientService.bindTimeLine(token,bindAdrTimeLineVo);
    }

    @PutMapping("/updtimeline")
    public Result<?> addTimeLineUpd(@RequestHeader String token, @RequestBody AdrTimeLineUpdVo adrTimeLineUpdVo){
        return entityClientService.addTimeLineUpd(token,adrTimeLineUpdVo);
    }

    @PostMapping("/bind")
    public Result<?> addBinding(@RequestHeader String token, @RequestBody BindUser bindUser){
        return entityClientService.addBinding(token,bindUser);
    }

    @DeleteMapping("/bind")
    public Result<?> deleteBinding(@RequestHeader String token,@RequestBody String uid){
        return entityClientService.deleteBinding(token,uid);
    }

    @PostMapping("/diary/write")
    public Result<?> addDiary(@RequestHeader String token
            ,@RequestParam("image") MultipartFile[] files
            ,@RequestParam("title") String title,@RequestParam("text") String text){
        return entityClientService.addDiary(token,files,title,text);
    }

    @DeleteMapping("/diary/{did}")
    public Result<?> deleteDiary(@RequestHeader String token,@PathVariable("did") String did){
        return entityClientService.deleteDiary(token,did);
    }

    @PutMapping("/diary/update")
    public Result<?> updateDiary(@RequestHeader String token
            ,@RequestParam("title") String title,@RequestParam String text
            ,@RequestParam("image") MultipartFile[] files,@RequestParam String did){
        return entityClientService.updateDiary(token,title,text,files,did);
    }

    @GetMapping("/diary/{did}")
    public Result<?> getDiaryDetail(@RequestHeader String token,@PathVariable("did") String did){
        return entityClientService.getDiaryDetail(token,did);
    }

    @GetMapping("/diary/list")
    public Result<?> diaryList(@RequestHeader String token){
        return entityClientService.diaryList(token);
    }

    @GetMapping("/diary/list/{pid}")
    public Result<?> childGetDiaryList(@RequestHeader String token,@PathVariable("pid") String pid1){
        return entityClientService.childGetDiaryList(token,pid1);
    }

    @PostMapping("/game")
    public Result<?> addGameImage(@RequestHeader String token, @RequestParam("realname") String realname
            , @RequestParam("nickname") String nickname, @RequestParam("pictures")MultipartFile[] files
            ,@RequestParam("uid")Long uid){
        return entityClientService.addGameImage(token,realname,nickname,files,uid);
    }

    @GetMapping("game/info/{uid}")
    public Result<?> gameInfo(@RequestHeader String token,@PathVariable("uid") String uid1){
        return entityClientService.gameInfo(token,uid1);
    }

    @PutMapping("/game")
    public Result<?> updGameImage(@RequestHeader String token, @RequestParam("realname") String realname
            , @RequestParam("nickname") String nickname, @RequestParam("picture")MultipartFile file
            ,@RequestParam("gid")Long gid){
        return entityClientService.updGameImage(token,realname,nickname,file,gid);
    }

    @DeleteMapping("game/{gid}")
    public Result<?> deleteGameImage(@RequestHeader String token,@PathVariable("gid") String gid1){
        return entityClientService.deleteGameImage(token,gid1);
    }

    @GetMapping("/play/pic")
    public Result<?> playPic(@RequestHeader String token){
        return entityClientService.playPic(token);
    }

    @PostMapping("/user/profile")
    public Result<?> uploadUserProfile(@RequestParam("profile") MultipartFile file, @RequestHeader String token){
        return entityClientService.uploadUserProfile(file,token);
    }

    @PostMapping("/addresslist/profile")
    public Result<?> uploadAddressProfile(@RequestParam("profile") MultipartFile file
            , @RequestHeader String token,@RequestParam("aid") String aid){
        return entityClientService.uploadAddressProfile(file,token,aid);
    }

    @PostMapping("/memory/pupload")
    public Result<?> pUploadMemory(@RequestHeader String token, @RequestParam("pictures")MultipartFile[] files
            , @RequestParam("datetime")String date,@RequestParam("detail") String detail){
        return entityClientService.pUploadMemory(token,files,date,detail);
    }

    @PutMapping("/memory/pupdate")
    public Result<?> pUpdateMemory(@RequestHeader String token, @RequestParam("pictures")MultipartFile[] files
            , @RequestParam("datetime")String date,@RequestParam("detail") String detail
            ,@RequestParam("meid") Long meid){
        return entityClientService.pUpdateMemory(token,files,date,detail,meid);
    }

    @DeleteMapping("/memory/pdelete")
    public Result<?> deleteMemory(@RequestHeader String token,@RequestBody String meid){
        return entityClientService.deleteMemory(token,meid);
    }

    @GetMapping("/pmemory")
    public Result<?> getPMemory(@RequestHeader String token){
        return entityClientService.getPMemory(token);
    }

    @GetMapping("/memory/{pid}")
    public Result<?> childGetMemory(@RequestHeader String token,@PathVariable("pid") String pid1){
        return entityClientService.childGetMemory(token,pid1);
    }

    @PostMapping("/memory/cupload")
    public Result<?> pUploadMemory(@RequestHeader String token, @RequestParam("pictures")MultipartFile[] files
            , @RequestParam("datetime")String date,@RequestParam("detail") String detail,@RequestParam("pid") Long pid){
        return entityClientService.pUploadMemory(token,files,date,detail,pid);
    }

    @DeleteMapping("/memory/cdelete")
    public Result<?> childDeleteMemory(@RequestHeader String token,@RequestBody String meid){
        return entityClientService.childDeleteMemory(token,meid);
    }

    @PutMapping("/memory/cupdate")
    public Result<?> cUpdateMemory(@RequestHeader String token, @RequestParam("pictures")MultipartFile[] files
            , @RequestParam("datetime")String date,@RequestParam("detail") String detail
            ,@RequestParam("meid") Long meid,@RequestParam("pid") Long pid){
        return entityClientService.cUpdateMemory(token,files,date,detail,meid,pid);
    }

    @PostMapping("/sendmsg")
    public Result<?> sendMessage(@RequestHeader String token, @RequestBody MessageVo messageVo){
        return entityClientService.sendMessage(token,messageVo);
    }

    @GetMapping("/getmsglist/{to_user_id}")
    public Result<?> getMsgList(@RequestHeader String token,@PathVariable("to_user_id") Long toUserId ){
        return entityClientService.getMsgList(token,toUserId);
    }

    @PostMapping("/add")
    public Result<?> add(@RequestHeader String token, @RequestBody AddTodo todo){
        return entityClientService.add(token,todo);
    }

    @GetMapping("/list")
    public Result<?> list(@RequestHeader String token){
        return entityClientService.list(token);
    }

    @DeleteMapping("/delete")
    public Result<?> delete(@RequestHeader String token, @RequestBody String tid){
        return entityClientService.delete(token,tid);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestHeader String token, @RequestBody UpdateTodo updateTodo){
        return entityClientService.update(token,updateTodo);
    }

    @PostMapping("/captcha/{phone_number}")
    public Result<?> sendCaptcha(@PathVariable("phone_number") String phone){
        return entityClientService.sendCaptcha(phone);
    }

    @PostMapping("/user/register")
    public Result<?> userRegister(@RequestBody UserReg userReg){
        return entityClientService.userRegister(userReg);
    }

    @PostMapping("/user/login")
    public Result<Map<String,Object>> login(@RequestBody LoginUser loginUser){
        return entityClientService.login(loginUser);
    }

    @PutMapping("/user")
    public Result<Map<String,Object>> forgetpwd(@RequestBody ForgetUser forgetuser){
        return entityClientService.forgetpwd(forgetuser);
    }

    @GetMapping("/user/info")
    public Result<?> userInfo(@RequestHeader String token){
        return entityClientService.userInfo(token);
    }

    @PutMapping("/user/updpwd")
    public Result<?> updatePassword(@RequestHeader String token,@RequestBody LoginUser loginUser){
        return entityClientService.updatePassword(token,loginUser);
    }

    @PutMapping("/user/updnn")
    public Result<?> updateNickname(@RequestHeader String token,@RequestBody String nickname){
        return entityClientService.updateNickname(token,nickname);
    }

    @PutMapping("/user/identify")
    public Result<?> UserIdentify(@RequestHeader String token,@RequestBody Identify info){
        return entityClientService.UserIdentify(token,info);
    }

    @GetMapping("/sos")
    public Result<?> getSosNum(@RequestHeader String token){
        return entityClientService.getSosNum(token);
    }

    @PostMapping("/sos")
    public Result<?> postSosNum(@RequestHeader String token,@RequestBody String phone){
        return entityClientService.postSosNum(token,phone);
    }

    @PutMapping("/sos")
    public Result<?> updateSosNum(@RequestHeader String token,@RequestBody String phone){
        return entityClientService.updateSosNum(token,phone);
    }

    @DeleteMapping("/sos")
    public Result<?> deleteSosNum(@RequestHeader String token){
        return entityClientService.deleteSosNum(token);
    }

    @GetMapping("/parent/info")
    public Result<?> parentInfo(@RequestHeader String token){
        return entityClientService.parentInfo(token);
    }
}
