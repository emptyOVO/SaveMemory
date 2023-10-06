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
@ApiModel(value = "Image对象", description = "")
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long imid;

    @ApiModelProperty("枚举值")
    private String belongs;

    private Long uid;

    private String image;

    private Date datetime;

    @ApiModelProperty("可以为空")
    private Long memoryId;

    @ApiModelProperty("可以为空")
    private Long diaryId;

    @ApiModelProperty("可以为空")
    private Long adrslId;

    public Long getAdrslId() {
        return adrslId;
    }

    public void setAdrslId(Long adrslId) {
        this.adrslId = adrslId;
    }

    public Long getImid() {
        return imid;
    }

    public void setImid(Long imid) {
        this.imid = imid;
    }
    public String getBelongs() {
        return belongs;
    }

    public void setBelongs(String belongs) {
        this.belongs = belongs;
    }
    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
    public Long getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(Long memoryId) {
        this.memoryId = memoryId;
    }
    public Long getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Long diaryId) {
        this.diaryId = diaryId;
    }

}
