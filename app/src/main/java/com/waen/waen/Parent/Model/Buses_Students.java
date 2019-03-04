package com.waen.waen.Parent.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Buses_Students {

    @SerializedName("studentsId")
    @Expose
    private String studentsId;
    @SerializedName("studentsName")
    @Expose
    private String studentsName;
    @SerializedName("studentsAge")
    @Expose
    private String studentsAge;
    @SerializedName("studentsGender")
    @Expose
    private String studentsGender;
    @SerializedName("studentsImage")
    @Expose
    private String studentsImage;
    @SerializedName("busesName")
    @Expose
    private String busesName;
    @SerializedName("numberBus")
    @Expose
    private String numberBus;
    @SerializedName("routesName")
    @Expose
    private String routesName;
    @SerializedName("supervisorsName")
    @Expose
    private String supervisorsName;
    @SerializedName("supervisorsGender")
    @Expose
    private String supervisorsGender;
    @SerializedName("supervisorsPhone")
    @Expose
    private String supervisorsPhone;
    @SerializedName("supervisorsUserToken")
    @Expose
    private String supervisorsUserToken;
    @SerializedName("driversName")
    @Expose
    private String driversName;
    @SerializedName("driversPhone")
    @Expose
    private String driversPhone;

    public String getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(String studentsId) {
        this.studentsId = studentsId;
    }

    public String getStudentsName() {
        return studentsName;
    }

    public void setStudentsName(String studentsName) {
        this.studentsName = studentsName;
    }

    public String getStudentsAge() {
        return studentsAge;
    }

    public void setStudentsAge(String studentsAge) {
        this.studentsAge = studentsAge;
    }

    public String getStudentsGender() {
        return studentsGender;
    }

    public void setStudentsGender(String studentsGender) {
        this.studentsGender = studentsGender;
    }

    public String getStudentsImage() {
        return studentsImage;
    }

    public void setStudentsImage(String studentsImage) {
        this.studentsImage = studentsImage;
    }

    public String getBusesName() {
        return busesName;
    }

    public void setBusesName(String busesName) {
        this.busesName = busesName;
    }

    public String getNumberBus() {
        return numberBus;
    }

    public void setNumberBus(String numberBus) {
        this.numberBus = numberBus;
    }

    public String getRoutesName() {
        return routesName;
    }

    public void setRoutesName(String routesName) {
        this.routesName = routesName;
    }

    public String getSupervisorsName() {
        return supervisorsName;
    }

    public void setSupervisorsName(String supervisorsName) {
        this.supervisorsName = supervisorsName;
    }

    public String getSupervisorsGender() {
        return supervisorsGender;
    }

    public void setSupervisorsGender(String supervisorsGender) {
        this.supervisorsGender = supervisorsGender;
    }

    public String getSupervisorsPhone() {
        return supervisorsPhone;
    }

    public void setSupervisorsPhone(String supervisorsPhone) {
        this.supervisorsPhone = supervisorsPhone;
    }

    public String getSupervisorsUserToken() {
        return supervisorsUserToken;
    }

    public void setSupervisorsUserToken(String supervisorsUserToken) {
        this.supervisorsUserToken = supervisorsUserToken;
    }

    public String getDriversName() {
        return driversName;
    }

    public void setDriversName(String driversName) {
        this.driversName = driversName;
    }

    public String getDriversPhone() {
        return driversPhone;
    }

    public void setDriversPhone(String driversPhone) {
        this.driversPhone = driversPhone;
    }

    @Override
    public String toString() {
        return studentsName;
    }
}
