package com.amingharibi.hospital.Domain;

import java.util.List;

public class DataHolder {
    private static DataHolder instance;
    private List<Category> fullList;

    private DataHolder() {}

    public static synchronized DataHolder getInstance() {
        if (instance == null) {
            instance = new DataHolder();
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
