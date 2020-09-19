package com.david0926.travity.model;

import java.util.ArrayList;

public class UserModel {

    private String name, email, time;
    private ArrayList<NotificationModel> notificationModels;
    private ArrayList<TodoModel> todoModels;
    private FlightModel flight; // 현재 비행 정보

    public UserModel(){}

    public UserModel(String name, String email, String time) {
        this.name = name;
        this.email = email;
        this.time = time;
    }

    public UserModel(String name, String email, String time, ArrayList<NotificationModel> notificationModels, ArrayList<TodoModel> todoModels) {
        this.name = name;
        this.email = email;
        this.time = time;
        this.notificationModels = notificationModels;
        this.todoModels = todoModels;
    }

    public UserModel(String name, String email, String time, ArrayList<NotificationModel> notificationModels, ArrayList<TodoModel> todoModels, FlightModel flight) {
        this.name = name;
        this.email = email;
        this.time = time;
        this.notificationModels = notificationModels;
        this.todoModels = todoModels;
        this.flight = flight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<NotificationModel> getNotificationModels() {
        return notificationModels;
    }

    public void setNotificationModels(ArrayList<NotificationModel> notificationModels) {
        this.notificationModels = notificationModels;
    }

    public ArrayList<TodoModel> getTodoModels() {
        return todoModels;
    }

    public void setTodoModels(ArrayList<TodoModel> todoModels) {
        this.todoModels = todoModels;
    }

    public FlightModel getFlight() {
        return flight;
    }

    public void setFlight(FlightModel flight) {
        this.flight = flight;
    }
}
