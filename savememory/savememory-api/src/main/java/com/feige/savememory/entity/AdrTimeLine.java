package com.feige.savememory.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "AdrTimeLine对象", description = "")
@TableName("adrtimeline")
public class AdrTimeLine implements Serializable {
    private static final long serialVersionUID = 1L;
    public long getAdrtlid() {
        return adrtlid;
    }

    public void setAdrtlid(long adrtlid) {
        this.adrtlid = adrtlid;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    /**
     * 记录id
     */
    private Long adrtlid;
    /**
     * 记录所属通讯录id，与uid不能同时为空
     */
    private Long aid;
    /**
     * 备注
     */
    private String remark;
    /**
     * 记录所属亲友id，与aid不能同时为空
     */
    private Long uid;
    /**
     * 插入时间/更新时间
     */
    private Date updateAt;

    @Override
    public String toString() {
        return "AdrTimeLine{" +
                "adrtlid=" + adrtlid +
                ", aid=" + aid +
                ", remark='" + remark + '\'' +
                ", uid=" + uid +
                ", updateAt=" + updateAt +
                '}';
    }
}
