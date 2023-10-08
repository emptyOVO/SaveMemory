package com.feige.savememory.vo;

public class AddTodo {
    /**
     * 是否完成
     */
    private boolean done;
    /**
     * 标题
     */
    private String title;
    /**
     * 代办内容
     */
    private String todo;

    public boolean getDone() { return done; }
    public void setDone(boolean value) { this.done = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getTodo() { return todo; }
    public void setTodo(String value) { this.todo = value; }

    @Override
    public String toString() {
        return "AddTodo{" +
                "done=" + done +
                ", title='" + title + '\'' +
                ", todo='" + todo + '\'' +
                '}';
    }
}
