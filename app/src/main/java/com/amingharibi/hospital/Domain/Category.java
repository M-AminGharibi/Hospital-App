package com.amingharibi.hospital.Domain;

import com.parse.ParseFile;
import com.parse.ParseObject;

public class Category {
    private int categoryId;
    private ParseFile imageFileCat;
    private String CategoryName;

    public Category() {
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
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public static Category fromParseObject(ParseObject parseObject) {
        Category category = new Category();
        category.setImageFileCat(parseObject.getParseFile("ImagePath"));
        category.setCategoryName(parseObject.getString("CategoryName"));
        return category;
    }

}
