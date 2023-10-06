package com.feige.savememory.vo;

public class MessageVo {
    /**
     * 消息内容
     */
    private String content;
    /**
     * 接收者id
     */
    private Long to_user_id;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(Long to_user_id) {
        this.to_user_id = to_user_id;
    }
}
