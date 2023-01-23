package com.example.precioustime_one;

public class Schedule {
    private String event;
    private String date;
    private String time;
    private String team;


    public Schedule( String event, String time,String date,String team) {
        this.event = event;
        this.date = date;
        this.time = time;
        this.team=team;
    }

    public Schedule(){}


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
