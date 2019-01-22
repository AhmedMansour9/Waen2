package com.waen.waen.SuperVisor.Model;

/**
 * Created by Ahmed on 23/12/2018.
 */

public class StudentsInfo{

    private String studentName;
    private String studentAddress;
    private String parentPhone;
    private String parentAddress;
    private String studentLat;
    private String studentLng;

    private String routesLatStartPint;
    private String routesLngStartPint;
    private String routesLatEndPint;
    private String routesLngEndPint;
    private Double Distance;

    public Double getDistance() {
        return Distance;
    }

    public void setDistance(Double distance) {
        Distance = distance;
    }

    public String getRoutesLatStartPint() {
        return routesLatStartPint;
    }

    public void setRoutesLatStartPint(String routesLatStartPint) {
        this.routesLatStartPint = routesLatStartPint;
    }

    public String getRoutesLngStartPint() {
        return routesLngStartPint;
    }

    public void setRoutesLngStartPint(String routesLngStartPint) {
        this.routesLngStartPint = routesLngStartPint;
    }

    public String getRoutesLatEndPint() {
        return routesLatEndPint;
    }

    public void setRoutesLatEndPint(String routesLatEndPint) {
        this.routesLatEndPint = routesLatEndPint;
    }

    public String getRoutesLngEndPint() {
        return routesLngEndPint;
    }

    public void setRoutesLngEndPint(String routesLngEndPint) {
        this.routesLngEndPint = routesLngEndPint;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getParentAddress() {
        return parentAddress;
    }

    public void setParentAddress(String parentAddress) {
        this.parentAddress = parentAddress;
    }

    public String getStudentLat() {
        return studentLat;
    }

    public void setStudentLat(String studentLat) {
        this.studentLat = studentLat;
    }

    public String getStudentLng() {
        return studentLng;
    }

    public void setStudentLng(String studentLng) {
        this.studentLng = studentLng;
    }
}
