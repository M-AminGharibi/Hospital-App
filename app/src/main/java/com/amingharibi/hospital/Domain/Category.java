package com.amingharibi.hospital.Domain;

import com.parse.ParseFile;
import com.parse.ParseObject;

public class Category {
    private ParseFile imagePath;
    private String CategoryName;

    public Category() {
    }

    public ParseFile getImagePath() {
        return imagePath;
    }

    public void setImagePath(ParseFile imagePath) {
        this.imagePath = imagePath;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public static Category fromParseObject(ParseObject parseObject) {
        Category category = new Category();
        category.setImagePath(parseObject.getParseFile("ImagePath"));
        category.setCategoryName(parseObject.getString("CategoryName"));
        return category;
    }

}
