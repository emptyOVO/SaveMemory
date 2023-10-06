package com.feige.savememory.vo;

public class ForgetUser {
    /**
     * 用户输入的验证码
     */
    private String captcha;
    /**
     * 密码，密码
     */
    private String password;
    /**
     * 用户账号，用户账号
     */
    private String username;

    public String getcaptcha() { return captcha; }
    public void setcaptcha(String value) { this.captcha = value; }

    public String getPassword() { return password; }
    public void setPassword(String value) { this.password = value; }

    public String getUsername() { return username; }
    public void setUsername(String value) { this.username = value; }
}