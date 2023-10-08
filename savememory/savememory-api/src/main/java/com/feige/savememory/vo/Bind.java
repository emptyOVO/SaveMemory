package com.feige.savememory.vo;

public class Bind {
    /**
     * 身份，0为父母1为子女
     */
    private String identity;
    /**
     * 昵称，昵称
     */
    private String nickname;
    /**
     * 手机号，手机号
     */
    private String phone;
    /**
     * 头像，头像图片
     */
    private String profile;
    /**
     * 真实姓名，真实姓名
     */
    private String realname;
    /**
     * 用户账号，用户账号
     */
    private String username;

    public Bind(String identity, String nickname, String phone, String profile, String realname, String username) {
        this.identity = identity;
        this.nickname = nickname;
        this.phone = phone;
        this.profile = profile;
        this.realname = realname;
        this.username = username;
    }

    public String getIdentity() { return identity; }
    public void setIdentity(String value) { this.identity = value; }

    public String getNickname() { return nickname; }
    public void setNickname(String value) { this.nickname = value; }

    public String getPhone() { return phone; }
    public void setPhone(String value) { this.phone = value; }

    public String getProfile() { return profile; }
    public void setProfile(String value) { this.profile = value; }

    public String getRealname() { return realname; }
    public void setRealname(String value) { this.realname = value; }

    public String getUsername() { return username; }
    public void setUsername(String value) { this.username = value; }

    @Override
    public String toString() {
        return "Bind{" +
                "identity='" + identity + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", profile='" + profile + '\'' +
                ", realname='" + realname + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}