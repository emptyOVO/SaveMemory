package com.feige.savememory.vo;

public class AdrTimeLineUpdVo {
    /**
     * 记录id
     */
    private long adrtlid;
    /**
     * 备注
     */
    private String remark;

    public long getAdrtlid() { return adrtlid; }
    public void setAdrtlid(long value) { this.adrtlid = value; }

    public String getRemark() { return remark; }
    public void setRemark(String value) { this.remark = value; }

    @Override
    public String toString() {
        return "AdrTimeLineUpdVo{" +
                "adrtlid=" + adrtlid +
                ", remark='" + remark + '\'' +
                '}';
    }
}
