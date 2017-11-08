package com.entity;

/**
 * Created by HP on 31.08.2017.
 */

public class InforPicture {
    private int image;
    private String englishMeaning;
    private String vietnameseMeaning;
    private String pronouciation;

    public String getEnglishMeaning() {
        return englishMeaning;
    }

    public void setEnglishMeaning(String englishMeaning) {
        this.englishMeaning = englishMeaning;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getVietnameseMeaning() {
        return vietnameseMeaning;
    }

    public void setVietnameseMeaning(String vietnameseMeaning) {
        this.vietnameseMeaning = vietnameseMeaning;
    }

    public String getPronouciation() {
        return pronouciation;
    }

    public void setPronouciation(String pronouciation) {
        this.pronouciation = pronouciation;
    }
}
