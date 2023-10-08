package com.feige.savememory.vo;

public class AdrTimeLineVo {
    /**
     * 记录所属通讯录id，与uid不能同时为空
     */
    private Long aid;
    /**
     * 备注
     */
    private String remark;

    public Long getAid() { return aid; }
    public void setAid(Long value) { this.aid = value; }

    public String getRemark() { return remark; }
    public void setRemark(String value) { this.remark = value; }

    @Override
    public String toString() {
        return "AdrTimeLineVo{" +
                "aid=" + aid +
                ", remark='" + remark + '\'' +
                '}';
    }
}
