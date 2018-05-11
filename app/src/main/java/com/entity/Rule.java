package com.entity;

/**
 * Created by Luong Vien on 30.01.2018.
 */

public class Rule {
    private int id;
    private String content1;
    private String content2;
    public Rule(){

    }

    public Rule(int id, String content1, String content2){
        this.id = id;
        this.content1 = content1;
        this.content2 = content2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }
}
