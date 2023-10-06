package com.feige.savememory.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "AddressList对象", description = "")
@TableName("addresslist")
public class AddressList implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Long aid;
    /**
     * 联系人昵称
     */
    private String nickname;
    /**
     * 联系人电话号码
     */
    private String phone;
    /**
     * 联系人头像
     */
    private String profile;
    /**
     * 联系人真实姓名
     */
    private String realname;
    /**
     * 通讯录所属用户id
     */
    private Long uid;


    @Override
    public String toString() {
        return "AddressList{" +
                "aid=" + aid +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", profile='" + profile + '\'' +
                ", realname='" + realname + '\'' +
                ", uid=" + uid +
                '}';
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }



    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }


}
