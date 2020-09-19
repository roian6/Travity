package com.david0926.travity.model;

public class NotificationModel {

    private String type, message, time;

    public NotificationModel() {}

    /**
     * @param type 알림 제목
     * @param message 알림 내용
     * @param time 알림 시간
     */
    public NotificationModel(String type, String message, String time) {
        this.type = type;
        this.message = message;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
