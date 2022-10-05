package com.example.dochere.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelMedicine {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("night")
    @Expose
    private String night;

    @SerializedName("morning")
    @Expose
    private String morning;

    @SerializedName("userId")
    @Expose
    private String userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getMorning() {
        return morning;
    }

    public void setMorning(String morning) {
        this.morning = morning;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
