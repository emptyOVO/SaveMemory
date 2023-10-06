package com.feige.savememory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feige.savememory.entity.User;
import com.feige.savememory.mapper.UserMapper;
import com.feige.savememory.service.IUserService;
import com.feige.savememory.util.IdCheckUtil;
import com.feige.savememory.util.JwtUtil;
import com.feige.savememory.vo.ForgetUser;
import com.feige.savememory.vo.Identify;
import com.feige.savememory.vo.LoginUser;
import com.feige.savememory.vo.UserReg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author feige
 * @since 2023-09-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /*@Override
    public List<User> getUserList() {
        MPJLambdaWrapper<User> wrapper = new MPJLambdaWrapper<User>()
                .selectAll(User.class);//查询UserComment表全部字段
        List<User> users = baseMapper.selectList(wrapper);
        return users;
    }*/

    @Override
    public User addUser(UserReg userReg) {
        User user = new User();
        user.setUid(IdWorker.getId());
        user.setUsername(userReg.getUsername());
        user.setNickname(userReg.getNickname());
        user.setPhone(userReg.getPhone());
        user.setPassword(passwordEncoder.encode(userReg.getPassword()));
        user.setIdentity(userReg.getIdentity());
        Date date=new Date();
        user.setRegisterTime(date);
        user.setBlocked(false);
        user.setProfile("https://img9.doubanio.com/view/group_topic/l/public/p570571224.webp");
        save(user);
        return user;
    }

    @Override
    public Map<String, Object> login(LoginUser loginUser) {
        //根据用户名和密码进行查询
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,loginUser.getUsername());
        User login=this.baseMapper.selectOne(wrapper);
        //结果不为空并且密码和传入密码匹配则生成token，并将用户名信息存在redis
        if(login != null&&passwordEncoder.matches(loginUser.getPassword(),login.getPassword())){
            //jwt
            String key = JwtUtil.getToken(login);

            //存入redis
            redisTemplate.opsForValue().set(String.valueOf(login.getUid()),key,5, TimeUnit.DAYS);

            //返回数据token
            Map<String,Object> data = new HashMap<>();
            data.put("token",key);
            data.put("username",login.getUsername());
            data.put("phone",login.getPhone());
            data.put("identity",login.getIdentity());
            return data;
        }else {
            return null;
        }
    }

    @Override
    public User selectByUsername(String username) {
        //根据用户名查询
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Map<String, Object> forgetpwd(ForgetUser forgetuser) {
        HashMap<String, Object> data = new HashMap<>();
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getUsername,forgetuser.getUsername());
        User user = new User();
        user.setPassword(passwordEncoder.encode(forgetuser.getPassword()));
        baseMapper.update(user,wrapper);
        User find=this.baseMapper.selectOne(wrapper);
        String token = JwtUtil.getToken(find);
        data.put("token",token);
        data.put("username",find.getUsername());
        data.put("identity",find.getIdentity());
        return data;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Map<String, Object> updpwd(String password, Long uid) {
        HashMap<String, Object> data = new HashMap<>();
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getUid,uid);
        User user = new User();
        user.setPassword(passwordEncoder.encode(password));
        User find=this.baseMapper.selectOne(wrapper);
        baseMapper.update(user,wrapper);
        data.put("username",find.getUsername());
        data.put("password",find.getPassword());
        return data;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Map<String, Object> updnn(String nickname, Long uid) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getUid,uid);
        User user = new User();
        user.setNickname(nickname);
        baseMapper.update(user,wrapper);
        User find=this.baseMapper.selectOne(wrapper);
        HashMap<String, Object> data = new HashMap<>();
        data.put("username",find.getUsername());
        data.put("nickname",find.getNickname());
        return data;
    }

    @Transactional(rollbackOn = Exception.class)
    public Map<String, Object> updateUserInfo(Long uid, Identify info) {
        if (IdCheckUtil.isIDNumber(info.getId_number())) {
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getUid,uid);
            User user = new User();
            user.setIdNumber(info.getId_number());
            user.setRealname(info.getRealname());
            baseMapper.update(user,wrapper);
            User find=this.baseMapper.selectOne(wrapper);
            HashMap<String, Object> data = new HashMap<>();
            data.put("username", find.getUsername());
            data.put("identity", find.getIdentity());
            return data;
        } else {
            return null;
        }
    }

    @Override
    public User selectById(long childid) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUid,childid);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public Map<String, Object> sosInfo(Long uid) {
        User user = selectById(uid);
        HashMap<String, Object> data = new HashMap<>();
        data.put("uid",user.getUid());
        data.put("username",user.getUsername());
        data.put("bindphone",user.getBindphone());
        return data;
    }

    @Override
    public Map<String, Object> addSos(Long uid, String phone) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUid,uid);
        User user = new User();
        user.setBindphone(phone);
        this.saveOrUpdate(user,wrapper);
        User user1 = this.baseMapper.selectOne(wrapper);
        HashMap<String, Object> data = new HashMap<>();
        data.put("uid",user1.getUid());
        data.put("identity",user1.getIdentity());
        data.put("bindphone",user1.getBindphone());
        return data;
    }

    @Override
    public Map<String, Object> updateBindPhone(Long uid, String toPhone) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUid,uid);
        User user = new User();
        user.setBindphone(toPhone);
        this.saveOrUpdate(user,wrapper);
        User user1 = this.baseMapper.selectOne(wrapper);
        HashMap<String, Object> data = new HashMap<>();
        data.put("uid",user1.getUid());
        data.put("identity",user1.getIdentity());
        data.put("bindphone",user1.getBindphone());
        return data;
    }

    @Override
    public Map<String, Object> deleteBindPhone(Long uid) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getUid,uid);
        User user = new User();
        user.setBindphone("");
        this.saveOrUpdate(user,wrapper);
        User user1 = this.baseMapper.selectOne(wrapper);
        HashMap<String, Object> data = new HashMap<>();
        data.put("uid",user1.getUid());
        data.put("identity",user1.getIdentity());
        data.put("bindphone",user1.getBindphone());
        return data;
    }

    @Override
    public void updProfile(String url, Long uid) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getUid,uid);
        User user = new User();
        user.setProfile(url);
        this.saveOrUpdate(user,wrapper);
    }

    @Override
    public Map<String, Object> getInfoById(long parentid) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getUid,parentid);
        User user = this.baseMapper.selectOne(wrapper);
        HashMap<String, Object> data = new HashMap<>();
        data.put("uid",user.getUid());
        data.put("username",user.getUsername());
        data.put("nickname",user.getNickname());
        data.put("realname",user.getRealname());
        data.put("id_number",user.getIdNumber());
        data.put("profile",user.getProfile());
        data.put("bindphone",user.getBindphone());
        return data;
    }



}
