package com.example.precioustime_one;

import android.annotation.SuppressLint;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Chat {

    private String name;
    private String message;
    private String time;
    private String team;


    public Chat(){}



    public Chat(String name, String message ,String team) {
        this.name = name;
        this.message = message;
        this.team=team;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            Calendar calendar =Calendar.getInstance();
            String currentDate = DateFormat.getTimeInstance().format(DateFormat.FULL);
            this.time="time: " + currentDate;

        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    //
//    public String getIcon() {
//        return icon;
//    }
//
//    public void setIcon(String icon) {
//        this.icon = icon;
//    }
//
//    public int getUnread() {
//        return unread;
//    }
//
//    public void setUnread(int unread) {
//        this.unread = unread;
//    }
}
