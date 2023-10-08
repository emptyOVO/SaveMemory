package com.feige.savememory.entityservice;

import com.feige.savememory.baseresponse.Result;
import com.feige.savememory.entity.AddressList;
import com.feige.savememory.vo.*;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Map;

@Component
public class EntityClientServiceFallbackFactory implements FallbackFactory {

    @Override
    public EntityClientService create(Throwable throwable) {
        return new EntityClientService() {

            @Override
            public Result<?> postAddressList(String token, AddAddressList addAddressList) {
                return Result.success(addAddressList.toString()+ '\t'+"userToken:"+token,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> patchAddressList(String token, UpdateAddressList updateAddressList) {
                return Result.success(updateAddressList.toString()+'\t'+"userToken:"+token,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> deleteAddressList(String token, String aid) {
                return Result.success("userToken:"+token+'\t'+"aid:"+aid,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> getAAdrlInfo(String token, String aid) {
                return Result.success("userToken:"+token+'\t'+"aid:"+aid,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> getBAdrlInfo(String token, String bid) {
                return Result.success("userToken:"+token+'\t'+"bid:"+bid,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> getAddressList(String token) {
                return Result.success("userToken:"+token,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> fillGame(String token) {
                return Result.success("userToken:"+token,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> addTimeLine(String token, AdrTimeLineVo adrTimeLineVo) {
                return Result.success(adrTimeLineVo.toString()+'\t'+"userToken:"+token,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> addTimeLineDelete(String token, String adrtlid) {
                return Result.success("userToken:"+token+'\t'+"adrtlid:"+adrtlid,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> bindTimeLine(String token, BindAdrTimeLineVo bindAdrTimeLineVo) {
                return Result.success(bindAdrTimeLineVo.toString()+'\t'+"userToken:"+token,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> addTimeLineUpd(String token, AdrTimeLineUpdVo adrTimeLineUpdVo) {
                return Result.success(adrTimeLineUpdVo.toString()+'\t'+"userToken:"+token,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> addBinding(String token, BindUser bindUser) {
                return Result.success(bindUser.toString()+'\t'+"userToken:"+token,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> deleteBinding(String token, String uid) {
                return Result.success(+'\t'+"userToken:"+token+'\t'+"uid:"+uid,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> addDiary(String token, MultipartFile[] files, String title, String text) {
                return Result.success("userToken:"+token+'\t'+"files:"+ Arrays.toString(files)+'\t'+"title:"+title+'\t'+"text:"+text,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> deleteDiary(String token, String did) {
                return Result.success("userToken:"+token+'\t'+"did:"+did,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> updateDiary(String token, String title, String text, MultipartFile[] files, String did) {
                return Result.success("userToken:"+token+'\t'+"files:"+ Arrays.toString(files)+'\t'+"title:"+title+'\t'+"text:"+text+'\t'+"did:"+did,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> getDiaryDetail(String token, String did) {
                return Result.success("userToken:"+token+'\t'+"did:"+did,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> diaryList(String token) {
                return Result.success("userToken:"+token,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> childGetDiaryList(String token, String pid1) {
                return Result.success("userToken:"+token+'\t'+"pid:"+pid1,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> addGameImage(String token, String realname, String nickname, MultipartFile[] files, Long uid) {
                return Result.success("userToken:"+token+'\t'+"realname:"+'\t'+ Arrays.toString(files)+'\t'+"nickname:"+nickname+'\t'+"uid:"+uid,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> gameInfo(String token, String uid1) {
                return Result.success("userToken:"+token+'\t'+"uid:"+ uid1,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> updGameImage(String token, String realname, String nickname, MultipartFile file, Long gid) {
                return Result.success("userToken:"+token+'\t'+"realname:"+'\t'+ file.toString()+'\t'+"nickname:"+nickname+'\t'+"gid:"+gid,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> deleteGameImage(String token, String gid1) {
                return Result.success("userToken:"+token+'\t'+"gid:"+gid1,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> playPic(String token) {
                return Result.success("userToken:"+token,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> uploadUserProfile(MultipartFile file, String token) {
                return Result.success("userToken:"+token+'\t'+file.toString(),"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> uploadAddressProfile(MultipartFile file, String token, String aid) {
                return Result.success("userToken:"+token+'\t'+file.toString()+'\t'+"aid:"+aid,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> pUploadMemory(String token, MultipartFile[] files, String date, String detail) {
                return Result.success("userToken:"+token+'\t'+ Arrays.toString(files) +'\t'+"date:"+date+'\t'+"detail:"+detail,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> pUpdateMemory(String token, MultipartFile[] files, String date, String detail, Long meid) {
                return Result.success("userToken:"+token+'\t'+ Arrays.toString(files) +'\t'+"date:"+date+'\t'+"detail:"+detail+'\t'+"meid:"+meid,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> deleteMemory(String token, String meid) {
                return Result.success("userToken:"+token+'\t'+"meid:"+meid,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> getPMemory(String token) {
                return Result.success("userToken:"+token,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> childGetMemory(String token, String pid1) {
                return Result.success("userToken:"+token+'\t'+"pid:"+pid1,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> pUploadMemory(String token, MultipartFile[] files, String date, String detail, Long pid) {
                return Result.success("userToken:"+token+'\t'+ Arrays.toString(files) +'\t'+"date:"+date+'\t'+"detail:"+detail+'\t'+"pid:"+pid,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> childDeleteMemory(String token, String meid) {
                return Result.success("userToken:"+token+'\t'+"meid:"+meid,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> cUpdateMemory(String token, MultipartFile[] files, String date, String detail, Long meid, Long pid) {
                return Result.success("userToken:"+token+'\t'+ Arrays.toString(files) +'\t'+"date:"+date+'\t'+"detail:"+detail+'\t'+"meid:"+meid+'\t'+"pid:"+pid,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> sendMessage(String token, MessageVo messageVo) {
                return Result.success("userToken:"+token+'\t'+messageVo.toString(),"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> getMsgList(String token, Long toUserId) {
                return Result.success("userToken:"+token+'\t'+"toUserId:"+toUserId,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> add(String token, AddTodo todo) {
                return Result.success("userToken:"+token+todo.toString(),"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> list(String token) {
                return Result.success("userToken:"+token,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> delete(String token, String tid) {
                return Result.success("userToken:"+token+'\t'+"tid:"+tid,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> update(String token, UpdateTodo updateTodo) {
                return Result.success("userToken:"+token+updateTodo.toString(),"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> sendCaptcha(String phone) {
                return Result.success("phone:"+phone,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> userRegister(UserReg userReg) {
                return Result.success(userReg.toString(),"服务器下线，请检查服务集群");
            }

            @Override
            public Result<Map<String, Object>> login(LoginUser loginUser) {
                return Result.success(null,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<Map<String, Object>> forgetpwd(ForgetUser forgetuser) {
                return Result.success(null,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> userInfo(String token) {
                return Result.success("userToken:"+token,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> updatePassword(String token, LoginUser loginUser) {
                return Result.success("userToken:"+token+'\t'+loginUser.toString(),"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> updateNickname(String token, String nickname) {
                return Result.success("userToken:"+token+'\t'+"nickname:"+nickname,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> UserIdentify(String token, Identify info) {
                return Result.success("userToken:"+token+'\t'+info.toString(),"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> getSosNum(String token) {
                return Result.success("userToken:"+token,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> postSosNum(String token, String phone) {
                return Result.success("userToken:"+token+'\t'+"phone:"+phone,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> updateSosNum(String token, String phone) {
                return Result.success("userToken:"+token+'\t'+"phone:"+phone,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> deleteSosNum(String token) {
                return Result.success("userToken:"+token,"服务器下线，请检查服务集群");
            }

            @Override
            public Result<?> parentInfo(String token) {
                return Result.success("userToken:"+token,"服务器下线，请检查服务集群");
            }
        };
    }
}
