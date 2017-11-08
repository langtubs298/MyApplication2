package com.entity;

/**
 * Created by HP on 29.08.2017.
 */

public class Picture {
    private int pictureID;
    private String name;

    public Picture(){

    }

    public Picture(int pictureID, String name){
        this.pictureID = pictureID;
        this.name = name;
    }
    public int getPictureID() {
        return pictureID;
    }

    public void setPictureID(int pictureID) {
        this.pictureID = pictureID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
