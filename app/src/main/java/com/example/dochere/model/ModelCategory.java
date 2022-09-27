package com.example.dochere.model;

public class ModelCategory {

    int image, string category;

    public ModelCategory(int image, int string) {
        this.image = image;
        this.string = string;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getString() {
        return string;
    }

    public void setString(int string) {
        this.string = string;
    }
}
