package com.feige.savememory.vo;

import java.util.Date;

public class Add {
    @Override
    public String toString() {
        return "Add{" +
                "aid=" + aid +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", profile='" + profile + '\'' +
                ", realname='" + realname + '\'' +
                ", remark='" + remark + '\'' +
                ", updateAt=" + updateAt +
                '}';
    }

    private Long aid;

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

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
     * 备注
     */
    private String remark;
    /**
     * 更新时间
     */
    private Date updateAt;

    public Add(Long aid, String nickname, String phone, String profile, String realname, String remark, Date updateAt) {
        this.aid = aid;
        this.nickname = nickname;
        this.phone = phone;
        this.profile = profile;
        this.realname = realname;
        this.remark = remark;
        this.updateAt = updateAt;
    }

    public String getNickname() { return nickname; }
    public void setNickname(String value) { this.nickname = value; }

    public String getPhone() { return phone; }
    public void setPhone(String value) { this.phone = value; }

    public String getProfile() { return profile; }
    public void setProfile(String value) { this.profile = value; }

    public String getRealname() { return realname; }
    public void setRealname(String value) { this.realname = value; }

    public String getRemark() { return remark; }
    public void setRemark(String value) { this.remark = value; }

    public Date getUpdateAt() { return updateAt; }
    public void setUpdateAt(Date value) { this.updateAt = value; }
}

