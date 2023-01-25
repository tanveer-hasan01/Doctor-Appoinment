package com.example.dochere.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelContact {

    @SerializedName("")
    @Expose
    private String id;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("number")
    @Expose
    private String number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
