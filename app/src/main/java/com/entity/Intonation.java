package com.entity;

/**
 * Created by HP on 18.10.2017.
 */

public class Intonation {
    private int intonationId;
    private String intonation;

    public Intonation(){

    }

    public  Intonation(int intonationId, String intonation){
        this.intonationId = intonationId;
        this.intonation = intonation;
    }

    public int getIntonationId() {
        return intonationId;
    }

    public void setIntonationId(int intonationId) {
        this.intonationId = intonationId;
    }

    public String getIntonation() {
        return intonation;
    }

    public void setIntonation(String intonation) {
        this.intonation = intonation;
    }
}
