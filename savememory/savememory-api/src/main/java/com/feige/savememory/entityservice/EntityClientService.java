package com.feige.savememory.entityservice;

import com.feige.savememory.baseresponse.Result;
import com.feige.savememory.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@FeignClient(value = "savememory-providers"/*,fallbackFactory = EntityClientServiceFallbackFactory.class*/)
public interface EntityClientService {

    @PostMapping("/addresslist")
    public Result<?> postAddressList(@RequestHeader("token") String token,@RequestBody AddAddressList addAddressList);

    @PutMapping("/addresslist")
    public Result<?> patchAddressList(@RequestHeader("token") String token,@RequestBody UpdateAddressList updateAddressList);

    @DeleteMapping("/addresslist")
    public Result<?> deleteAddressList(@RequestHeader("token") String token,@RequestBody String aid);

    @GetMapping("/addresslist/addinfo/{aid}")
    public Result<?> getAAdrlInfo(@RequestHeader("token") String token ,@PathVariable("aid") String aid);

    @GetMapping("/addresslist/bindinfo/{bid}")
    public Result<?> getBAdrlInfo(@RequestHeader("token") String token ,@PathVariable("bid") String bid);

    @GetMapping("/addresslist")
    public Result<?> getAddressList(@RequestHeader("token") String token);

    @GetMapping("/play/fill")
    public Result<?> fillGame(@RequestHeader("token") String token);

    @PostMapping("/addtimeline")
    public Result<?> addTimeLine(@RequestHeader("token") String token, @RequestBody AdrTimeLineVo adrTimeLineVo);

    @DeleteMapping("/deletetimeline")
    public Result<?> addTimeLineDelete(@RequestHeader("token") String token, @RequestBody String adrtlid);

    @PostMapping("/bindtimeline")
    public Result<?> bindTimeLine(@RequestHeader("token") String token, @RequestBody BindAdrTimeLineVo bindAdrTimeLineVo);

    @PutMapping("/updtimeline")
    public Result<?> addTimeLineUpd(@RequestHeader("token") String token, @RequestBody AdrTimeLineUpdVo adrTimeLineUpdVo);

    @PostMapping("/bind")
    public Result<?> addBinding(@RequestHeader("token") String token, @RequestBody BindUser bindUser);

    @DeleteMapping("/bind")
    public Result<?> deleteBinding(@RequestHeader("token") String token,@RequestBody String uid);

    @PostMapping("/diary/write")
    public Result<?> addDiary(@RequestHeader("token") String token
            ,@RequestParam("image") MultipartFile[] files
            ,@RequestParam("title") String title,@RequestParam("text") String text);

    @DeleteMapping("/diary/{did}")
    public Result<?> deleteDiary(@RequestHeader("token") String token,@PathVariable("did") String did);

    @PutMapping("/diary/update")
    public Result<?> updateDiary(@RequestHeader("token") String token
            ,@RequestParam("title") String title,@RequestParam("text") String text
            ,@RequestParam("image") MultipartFile[] files,@RequestParam("did") String did);

    @GetMapping("/diary/{did}")
    public Result<?> getDiaryDetail(@RequestHeader("token") String token,@PathVariable("did") String did);

    @GetMapping("/diary/list")
    public Result<?> diaryList(@RequestHeader("token") String token);

    @GetMapping("/diary/list/{pid}")
    public Result<?> childGetDiaryList(@RequestHeader("token") String token,@PathVariable("pid") String pid1);

    @PostMapping("/game")
    public Result<?> addGameImage(@RequestHeader("token") String token, @RequestParam("realname") String realname
            , @RequestParam("nickname") String nickname, @RequestParam("pictures")MultipartFile[] files
            ,@RequestParam("uid")Long uid);

    @GetMapping("game/info/{uid}")
    public Result<?> gameInfo(@RequestHeader("token") String token,@PathVariable("uid") String uid1);

    @PutMapping("/game")
    public Result<?> updGameImage(@RequestHeader("token") String token, @RequestParam("realname") String realname
            , @RequestParam("nickname") String nickname, @RequestParam("picture")MultipartFile file
            ,@RequestParam("gid")Long gid);

    @DeleteMapping("game/{gid}")
    public Result<?> deleteGameImage(@RequestHeader("token") String token,@PathVariable("gid") String gid1);

    @GetMapping("/play/pic")
    public Result<?> playPic(@RequestHeader("token") String token);

    @PostMapping("/user/profile")
    public Result<?> uploadUserProfile(@RequestParam("profile") MultipartFile file, @RequestHeader("token") String token);

    @PostMapping("/addresslist/profile")
    public Result<?> uploadAddressProfile(@RequestParam("profile") MultipartFile file
            , @RequestHeader("token") String token,@RequestParam("aid") String aid);

    @PostMapping("/memory/pupload")
    public Result<?> pUploadMemory(@RequestHeader("token") String token, @RequestParam("pictures")MultipartFile[] files
            , @RequestParam("datetime")String date,@RequestParam("detail") String detail);

    @PutMapping("/memory/pupdate")
    public Result<?> pUpdateMemory(@RequestHeader("token") String token, @RequestParam("pictures")MultipartFile[] files
            , @RequestParam("datetime")String date,@RequestParam("detail") String detail
            ,@RequestParam("meid") Long meid);

    @DeleteMapping("/memory/pdelete")
    public Result<?> deleteMemory(@RequestHeader("token") String token,@RequestBody String meid);

    @GetMapping("/pmemory")
    public Result<?> getPMemory(@RequestHeader("token") String token);

    @GetMapping("/memory/{pid}")
    public Result<?> childGetMemory(@RequestHeader("token") String token,@PathVariable("pid") String pid1);

    @PostMapping("/memory/cupload")
    public Result<?> pUploadMemory(@RequestHeader("token") String token, @RequestParam("pictures")MultipartFile[] files
            , @RequestParam("datetime")String date,@RequestParam("detail") String detail,@RequestParam("pid") Long pid);

    @DeleteMapping("/memory/cdelete")
    public Result<?> childDeleteMemory(@RequestHeader("token") String token,@RequestBody String meid);

    @PutMapping("/memory/cupdate")
    public Result<?> cUpdateMemory(@RequestHeader("token") String token, @RequestParam("pictures")MultipartFile[] files
            , @RequestParam("datetime")String date,@RequestParam("detail") String detail
            ,@RequestParam("meid") Long meid,@RequestParam("pid") Long pid);

    @PostMapping("/sendmsg")
    public Result<?> sendMessage(@RequestHeader("token") String token, @RequestBody MessageVo messageVo);

    @GetMapping("/getmsglist/{to_user_id}")
    public Result<?> getMsgList(@RequestHeader("token") String token,@PathVariable("to_user_id") Long toUserId );

    @PostMapping("/add")
    public Result<?> add(@RequestHeader("token") String token, @RequestBody AddTodo todo);

    @GetMapping("/list")
    public Result<?> list(@RequestHeader("token") String token);

    @DeleteMapping("/delete")
    public Result<?> delete(@RequestHeader("token") String token, @RequestBody String tid);

    @PutMapping("/update")
    public Result<?> update(@RequestHeader("token") String token, @RequestBody UpdateTodo updateTodo);

    @PostMapping("/captcha/{phone_number}")
    public Result<?> sendCaptcha(@PathVariable("phone_number") String phone);

    @PostMapping("/user/register")
    public Result<?> userRegister(@RequestBody UserReg userReg);

    @PostMapping("/user/login")
    public Result<Map<String,Object>> login(@RequestBody LoginUser loginUser);

    @PutMapping("/user")
    public Result<Map<String,Object>> forgetpwd(@RequestBody ForgetUser forgetuser);

    @GetMapping("/user/info")
    public Result<?> userInfo(@RequestHeader("token") String token);

    @PutMapping("/user/updpwd")
    public Result<?> updatePassword(@RequestHeader("token") String token,@RequestBody LoginUser loginUser);

    @PutMapping("/user/updnn")
    public Result<?> updateNickname(@RequestHeader("token") String token,@RequestBody String nickname);

    @PutMapping("/user/identify")
    public Result<?> UserIdentify(@RequestHeader("token") String token,@RequestBody Identify info);

    @GetMapping("/sos")
    public Result<?> getSosNum(@RequestHeader("token") String token);

    @PostMapping("/sos")
    public Result<?> postSosNum(@RequestHeader("token") String token,@RequestBody String phone);

    @PutMapping("/sos")
    public Result<?> updateSosNum(@RequestHeader("token") String token,@RequestBody String phone);

    @DeleteMapping("/sos")
    public Result<?> deleteSosNum(@RequestHeader("token") String token);

    @GetMapping("/parent/info")
    public Result<?> parentInfo(@RequestHeader("token") String token);
}
