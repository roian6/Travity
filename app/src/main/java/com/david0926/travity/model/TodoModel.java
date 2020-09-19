package com.david0926.travity.model;

public class TodoModel {

    private String msg;
    private boolean Finished;

    /**
     * @param msg 할일 내용
     * @param finish 할일은 끝냈능교
     */
    public TodoModel(String msg, boolean finish) {
        this.msg = msg;
        Finished = finish;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isFinished() {
        return Finished;
    }

    public void setFinished(boolean finish) {
        Finished = finish;
    }
}
