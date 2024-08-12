package com.amingharibi.hospital.Domain;

import com.parse.ParseFile;
import com.parse.ParseObject;

public class Doctor {
    private int categoryId;
    private ParseFile imageFileDoc;
    private String categoryName;
    private String doctorName;
    private String doctorTime;

    public Doctor() {
    }


    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public ParseFile getImageFileDoc() {
        return imageFileDoc;
    }

    public void setImageFileDoc(ParseFile imageFileCat) {
        this.imageFileDoc = imageFileCat;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorTime() {
        return doctorTime;
    }

    public void setDoctorTime(String doctorTime) {
        this.doctorTime = doctorTime;
    }


    public static Doctor fromParseObject(ParseObject parseObject) {
        Doctor doctor = new Doctor();
        doctor.setImageFileDoc(parseObject.getParseFile("ImageDoc"));
        doctor.setCategoryName(parseObject.getString("Category"));
        doctor.setDoctorName(parseObject.getString("FullName"));
        doctor.setDoctorTime(parseObject.getString("Dates"));
        return doctor;
    }
}
