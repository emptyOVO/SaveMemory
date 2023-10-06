package com.feige.savememory.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(value = "Binding对象", description = "")
@TableName("binding")
public class Binding implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 关系id
     */
    private long bid;
    /**
     * 子女用户id
     */
    private long childId;
    /**
     * 父母用户id
     */
    private long parentId;

    @Override
    public String toString() {
        return "Binding{" +
                "bid=" + bid +
                ", childid=" + childId +
                ", parentid=" + parentId +
                '}';
    }

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public long getChildid() {
        return childId;
    }

    public void setChildid(long childid) {
        this.childId = childid;
    }

    public long getParentid() {
        return parentId;
    }

    public void setParentid(long parentid) {
        this.parentId = parentid;
    }
}
