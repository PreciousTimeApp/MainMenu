package com.example.precioustime_one;

import java.util.ArrayList;

import kotlin.Pair;

public class Task {


    private String ownerTaskName;
    private String descriptionTask;
    private String statusTask ;
    private String teamTask ;




    public Task(String descriptionTask , String ownerTaskName  ,String statusTask ,String team) {
        this.descriptionTask = descriptionTask;
        this.ownerTaskName = ownerTaskName;
        this.statusTask = statusTask;
        this.teamTask=team;

    }

    public Task(){}

    public String getOwnerTaskName() {
        return ownerTaskName;
    }

    public void setOwnerTaskName(String ownerTaskName) {
        this.ownerTaskName = ownerTaskName;
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public void setDescriptionTask(String descriptionTask) {
        this.descriptionTask = descriptionTask;
    }

    public String getStatusTask() {
        return statusTask;
    }

    public void setStatusTask(String statusTask) {
        this.statusTask = statusTask;
    }

    public String getTeamTask() {
        return teamTask;
    }

    public void setTeamTask(String teamTask) {
        this.teamTask = teamTask;
    }
}
