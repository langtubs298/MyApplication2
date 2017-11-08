package com.entity;

/**
 * Created by HP on 18.10.2017.
 */

public class Question {
    private int questionID;
    private String question;

    public Question(){

    }

    public Question(int questionID, String question){
        this.questionID = questionID;
        this.question = question;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
