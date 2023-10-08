package com.feige.savememory.vo;

public class BindUser {
    /**
     * 身份证号，身份证号
     */
    private String idNumber;
    /**
     * 手机号，手机号
     */
    private String phone;
    /**
     * 真实姓名，真实姓名
     */
    private String realname;


    /**
     * 用户账号，用户账号
     */
    private String username;

    public String getidNumber() { return idNumber; }
    public void setidNumber(String value) { this.idNumber = value; }

    public String getPhone() { return phone; }
    public void setPhone(String value) { this.phone = value; }

    public String getRealname() { return realname; }
    public void setRealname(String value) { this.realname = value; }


    public String getUsername() { return username; }
    public void setUsername(String value) { this.username = value; }

    @Override
    public String toString() {
        return "BindUser{" +
                "idNumber='" + idNumber + '\'' +
                ", phone='" + phone + '\'' +
                ", realname='" + realname + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
