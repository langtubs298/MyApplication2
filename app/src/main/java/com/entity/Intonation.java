package com.entity;

/**
 * Created by Luong Vien on 18.10.2017.
 */

public class Intonation {
    private int id;
    private String content;

    public Intonation(){

    }

    public  Intonation(int id, String content){
        this.id = id;
        this.content = content;
    }

    public int getIntonationId() {
        return id;
    }

    public void setIntonationId(int id) {
        this.id = id;
    }

    public String getIntonation() {
        return content;
    }

    public void setIntonation(String content) {
        this.content = content;
    }
}
