package com.feige.savememory.vo;

public class UpdateAddressList {
    /**
     * id
     */
    private long aid;
    /**
     * 联系人昵称
     */
    private String nickname;
    /**
     * 联系人电话号码
     */
    private String phone;
    /**
     * 联系人真实姓名
     */
    private String realname;


    public long getAid() { return aid; }
    public void setAid(long value) { this.aid = value; }

    public String getNickname() { return nickname; }
    public void setNickname(String value) { this.nickname = value; }

    public String getPhone() { return phone; }
    public void setPhone(String value) { this.phone = value; }



    public String getRealname() { return realname; }
    public void setRealname(String value) { this.realname = value; }

    @Override
    public String toString() {
        return "UpdateAddressList{" +
                "aid=" + aid +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", realname='" + realname + '\'' +
                '}';
    }
}
