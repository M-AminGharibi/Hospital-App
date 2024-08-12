package com.amingharibi.hospital.Domain;

import java.util.List;

public class DoctorDataHolder {
    private static DoctorDataHolder instance;
    private List<Doctor> fullList;

    private DoctorDataHolder() {}

    public static synchronized DoctorDataHolder getInstance() {
        if (instance == null) {
            instance = new DoctorDataHolder();
        }
        return instance;
    }

    public List<Doctor> getFullList() {
        return fullList;
    }

    public void setFullList(List<Doctor> fullList) {
        this.fullList = fullList;
    }
}
