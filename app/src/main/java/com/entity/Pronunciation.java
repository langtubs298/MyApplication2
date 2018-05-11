package com.entity;

/**
 * Created by Luong Vien on 02.09.2017.
 */

public class Pronunciation{

    private int pronunciationID;
    private String name;
    private String description;

    public Pronunciation(){
    }

    public  Pronunciation(int pronunciationID, String name, String description){
        this.pronunciationID = pronunciationID;
        this.name = name;
        this.description = description;
    }
    public int getPronunciationID() {
        return pronunciationID;
    }

    public void setPronunciationID(int pronunciationID) {
        this.pronunciationID = pronunciationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }
}
