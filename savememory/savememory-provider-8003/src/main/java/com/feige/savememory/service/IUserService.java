package com.feige.savememory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feige.savememory.entity.User;
import com.feige.savememory.vo.ForgetUser;
import com.feige.savememory.vo.Identify;
import com.feige.savememory.vo.LoginUser;
import com.feige.savememory.vo.UserReg;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author feige
 * @since 2023-09-29
 */
public interface IUserService extends IService<User> {


   // List<User> getUserList();

    User addUser(UserReg userReg);

    Map<String, Object> login(LoginUser loginUser);

    User selectByUsername(String username);

    Map<String, Object> forgetpwd(ForgetUser forgetuser);

    Map<String, Object> updpwd(String password, Long uid);

    Map<String, Object> updnn(String nickname, Long uid);

    Map<String, Object> updateUserInfo(Long uid, Identify info);

    User selectById(long childid);

    Map<String, Object> sosInfo(Long uid);

    Map<String, Object> addSos(Long uid, String phone);

    Map<String, Object> updateBindPhone(Long uid, String toPhone);

    Map<String, Object> deleteBindPhone(Long uid);

    void updProfile(String url, Long uid);

    Map<String, Object> getInfoById(long parentid);

}
