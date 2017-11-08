package com.entity;

/**
 * Created by HP on 05.09.2017.
 */

public class CategoryQuestion {
    private int categoryQuestionID;
    private String category;

    public CategoryQuestion(){

    }

    public CategoryQuestion(int categoryQuestionID, String category){
        this.category =  category;
        this.categoryQuestionID = categoryQuestionID;
    }

    public int getCategoryQuestionID() {
        return categoryQuestionID;
    }

    public void setCategoryQuestionID(int categoryQuestionID) {
        this.categoryQuestionID = categoryQuestionID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
