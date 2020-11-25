package com.david0926.travity.model;

import java.util.Date;

public class FlightModel {

    private String startAirport, endAirport, Terminal, Gate, Seat;
    private Date startTime, endTime;

    public FlightModel(){

    }

    /**
     * @param startAirport 출발 공항
     * @param endAirport 도착 공항
     * @param terminal 터미널
     * @param gate 게이트 번호
     * @param seat 좌석 번호
     * @param startTime 출발 시간
     * @param endTime 도착 시간
     */
    public FlightModel(String startAirport, String endAirport, String terminal, String gate, String seat, Date startTime, Date endTime) {
        this.startAirport = startAirport;
        this.endAirport = endAirport;
        Terminal = terminal;
        Gate = gate;
        Seat = seat;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartAirport() {
        return startAirport;
    }

    public void setStartAirport(String startAirport) {
        this.startAirport = startAirport;
    }

    public String getEndAirport() {
        return endAirport;
    }

    public void setEndAirport(String endAirport) {
        this.endAirport = endAirport;
    }

    public String getTerminal() {
        return Terminal;
    }

    public void setTerminal(String terminal) {
        Terminal = terminal;
    }

    public String getGate() {
        return Gate;
    }

    public void setGate(String gate) {
        Gate = gate;
    }

    public String getSeat() {
        return Seat;
    }

    public void setSeat(String seat) {
        Seat = seat;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
