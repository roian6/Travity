package com.david0926.travity.model;

import java.util.ArrayList;

public class UserModel {

    private String name, email, time;
    private ArrayList<NotificationModel> notificationModels;
    private ArrayList<TodoModel> todoModels;
    private ArrayList<TodoModel> thingModels; // 준비물
    private ArrayList<FlightModel> flightModels; // 현재 비행 정보

    public UserModel(){}

    public UserModel(String name, String email, String time) {
        this.name = name;
        this.email = email;
        this.time = time;
    }

    public UserModel(String name, String email, String time, ArrayList<NotificationModel> notificationModels, ArrayList<TodoModel> todoModels, ArrayList<TodoModel> thingModels) {
        this.name = name;
        this.email = email;
        this.time = time;
        this.notificationModels = notificationModels;
        this.todoModels = todoModels;
        this.thingModels = thingModels;
    }

    public UserModel(String name, String email, String time, ArrayList<NotificationModel> notificationModels, ArrayList<TodoModel> todoModels, ArrayList<TodoModel> thingModels, ArrayList<FlightModel> flightModels) {
        this.name = name;
        this.email = email;
        this.time = time;
        this.notificationModels = notificationModels;
        this.todoModels = todoModels;
        this.thingModels = thingModels;
        this.flightModels = flightModels;
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

    public ArrayList<TodoModel> getThingModels() {
        return thingModels;
    }

    public void setThingModels(ArrayList<TodoModel> thingModels) {
        this.thingModels = thingModels;
    }

    public ArrayList<TodoModel> getTodoModels() {
        return todoModels;
    }

    public void setTodoModels(ArrayList<TodoModel> todoModels) {
        this.todoModels = todoModels;
    }

    public ArrayList<FlightModel> getFlightModels() {
        return flightModels;
    }

    public void setFlightModels(ArrayList<FlightModel> flightModels) {
        this.flightModels = flightModels;
    }
}
