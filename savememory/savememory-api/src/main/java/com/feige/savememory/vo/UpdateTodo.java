package com.feige.savememory.vo;

public class UpdateTodo {
    /**
 * 是否完成
 */
private boolean done;
    /**
     * 代办id
     */
    private long tid;
    /**
     * 代办内容
     */

    public boolean getDone() { return done; }
    public void setDone(boolean value) { this.done = value; }

    public long getTid() { return tid; }
    public void setTid(long value) { this.tid = value; }

}
