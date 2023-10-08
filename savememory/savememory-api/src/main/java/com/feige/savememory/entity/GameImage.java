package com.feige.savememory.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@ApiModel(value = "GameImage对象", description = "")
@TableName("gameimage")
public class GameImage implements Serializable {
    private static final Long serialVersionUID = 1L;
    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getGimage() {
        return gimage;
    }

    public void setGimage(String gimage) {
        this.gimage = gimage;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    /**
     * id
     */
    private Long gid;
    /**
     * 照片地址
     */
    private String gimage;
    /**
     * 照片人昵称
     */
    private String nickname;
    /**
     * 照片人真实姓名
     */
    private String realname;
    /**
     * 老人id
     */
    private Long uid;

    @Override
    public String toString() {
        return "GameImage{" +
                "gid=" + gid +
                ", gimage='" + gimage + '\'' +
                ", nickname='" + nickname + '\'' +
                ", realname='" + realname + '\'' +
                ", uid=" + uid +
                '}';
    }
}
