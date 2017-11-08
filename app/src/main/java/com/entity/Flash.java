package com.entity;

/**
 * Created by HP on 29.08.2017.
 */

public class Flash {
    private int flashID;
    private String name;

    public Flash(){

    }

    public Flash(int flashID, String name){
        this.flashID = flashID;
        this.name = name;
    }

    public int getFlashID() {
        return flashID;
    }

    public void setFlashID(int flashID) {
        this.flashID = flashID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
