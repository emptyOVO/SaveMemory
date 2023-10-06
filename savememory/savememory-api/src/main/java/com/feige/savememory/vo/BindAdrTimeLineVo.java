package com.feige.savememory.vo;

public class BindAdrTimeLineVo {
    /**
     * 备注
     */
    private String remark;
    /**
     * 记录所属亲友id，与aid不能同时为空
     */
    private Long uid;

    public String getRemark() { return remark; }
    public void setRemark(String value) { this.remark = value; }

    public Long getUid() { return uid; }
    public void setUid(Long value) { this.uid = value; }
}
