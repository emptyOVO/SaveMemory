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
@ApiModel(value = "Diary对象", description = "")
public class Diary implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long did;

    private Long uid;

    private String title;

    private String text;

    private Date datetime;

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }
    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Diary{" +
            "did=" + did +
            ", uid=" + uid +
            ", title=" + title +
            ", text=" + text +
            ", datetime=" + datetime +
        "}";
    }
}
