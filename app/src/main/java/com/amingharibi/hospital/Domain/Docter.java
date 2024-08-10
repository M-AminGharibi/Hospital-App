package com.amingharibi.hospital.Domain;

import com.parse.ParseFile;

public class Docter {
    private int categoryId;
    private ParseFile imageFileCat;
    private String categoryName;
    private String docterName;
    private String docterTime;

    public Docter() {
    }


    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public ParseFile getImageFileCat() {
        return imageFileCat;
    }

    public void setImageFileCat(ParseFile imageFileCat) {
        this.imageFileCat = imageFileCat;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDocterName() {
        return docterName;
    }

    public void setDocterName(String docterName) {
        this.docterName = docterName;
    }

    public String getDocterTime() {
        return docterTime;
    }

    public void setDocterTime(String docterTime) {
        this.docterTime = docterTime;
    }
}
