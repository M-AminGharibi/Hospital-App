package com.amingharibi.hospital.Domain;

import java.util.List;

public class CategoryDataHolder {
    private static CategoryDataHolder instance;
    private List<Category> fullList;

    private CategoryDataHolder() {}

    public static synchronized CategoryDataHolder getInstance() {
        if (instance == null) {
            instance = new CategoryDataHolder();
        }
        return instance;
    }

    public List<Category> getFullList() {
        return fullList;
    }

    public void setFullList(List<Category> fullList) {
        this.fullList = fullList;
    }
}
