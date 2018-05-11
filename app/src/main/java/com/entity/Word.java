package com.entity;

/**
 * Created by Luong Vien on 12.01.2018.
 */

public class Word {
    private int id;
    private String word;
    private String pronoun;
    private String mean;
    private String ok;

    public Word(){

    }
    public Word(int id, String word, String pronoun, String mean, String ok){
        this.id = id;
        this.word = word;
        this.pronoun = pronoun;
        this.mean = mean;
        this.ok = ok;
    }
    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPronoun() {
        return pronoun;
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }
}
