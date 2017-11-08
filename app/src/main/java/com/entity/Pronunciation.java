package com.entity;

/**
 * Created by HP on 02.09.2017.
 */

public class Pronunciation{

    private int pronunciationID;
    private String name;

    public Pronunciation(){
    }

    public  Pronunciation(int pronunciationID, String name){
        this.pronunciationID = pronunciationID;
        this.name = name;
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
}
