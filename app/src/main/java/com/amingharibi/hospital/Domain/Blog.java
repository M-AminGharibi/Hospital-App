package com.amingharibi.hospital.Domain;

import com.parse.ParseFile;
import com.parse.ParseObject;

public class Blog {
    private int categoryId;
    private String descTxt;
    private String title;
    private ParseFile imageFileBlog;

    public Blog() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescTxt() {
        return descTxt;
    }

    public void setDescTxt(String descTxt) {
        this.descTxt = descTxt;
    }

    public ParseFile getImageFileBlog() {
        return imageFileBlog;
    }

    public void setImageFileBlog(ParseFile imageFileBlog) {
        this.imageFileBlog = imageFileBlog;
    }

    public static Blog fromParseObject(ParseObject parseObject) {
        Blog blog = new Blog();
        blog.setImageFileBlog(parseObject.getParseFile("ImagePath"));
        blog.setTitle(parseObject.getString("Title"));
        blog.setDescTxt(parseObject.getString("Text"));
        return blog;
    }

}
