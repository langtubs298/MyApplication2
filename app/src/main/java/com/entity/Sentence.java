package com.entity;

/**
 * Created by Luong Vien on 22.01.2018.
 */

public class Sentence {
    private int id;
    private String sentence;
    public Sentence(){

    }
    public Sentence(int id, String sentence){
        this.id = id;
        this.sentence = sentence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
