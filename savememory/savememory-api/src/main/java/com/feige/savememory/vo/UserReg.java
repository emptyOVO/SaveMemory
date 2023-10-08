package com.feige.savememory.vo;


public class UserReg {
    /**
     * 用户输入的验证码
     */
    private String captcha;
    /**
     * 身份，0为父母1为子女
     */
    private String identity;
    /**
     * 昵称，昵称
     */
    private String nickname;
    /**
     * 密码，密码
     */
    private String password;
    /**
     * 手机号，手机号
     */
    private String phone;
    /**
     * 用户账号，用户账号
     */
    private String username;

    public String getcaptcha() { return captcha; }
    public void setcaptcha(String value) { this.captcha = value; }

    public String getIdentity() { return identity; }
    public void setIdentity(String value) { this.identity = value; }

    public String getNickname() { return nickname; }
    public void setNickname(String value) { this.nickname = value; }

    public String getPassword() { return password; }
    public void setPassword(String value) { this.password = value; }

    public String getPhone() { return phone; }
    public void setPhone(String value) { this.phone = value; }

    public String getUsername() { return username; }
    public void setUsername(String value) { this.username = value; }

    @Override
    public String toString() {
        return "UserReg{" +
                "captcha='" + captcha + '\'' +
                ", identity='" + identity + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
