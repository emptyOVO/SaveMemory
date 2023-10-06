package com.feige.savememory.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author feige
 * @since 2023-09-29
 */
@ApiModel(value = "Memory对象", description = "")
public class Memory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long meid;

    private Long pid;

    private Date datetime;

    private Long uid;

    private String profile;

    private Date createTime;

    private String detail;

    public Long getMeid() {
        return meid;
    }

    public void setMeid(Long meid) {
        this.meid = meid;
    }
    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Memory{" +
            "meid=" + meid +
            ", pid=" + pid +
            ", datetime=" + datetime +
            ", uid=" + uid +
            ", profile=" + profile +
            ", createTime=" + createTime +
            ", detail=" + detail +
        "}";
    }
}
