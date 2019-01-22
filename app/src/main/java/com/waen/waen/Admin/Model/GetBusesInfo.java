package com.waen.waen.Admin.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBusesInfo {

    @SerializedName("supervisorsUserToken")
    @Expose
    private String supervisorsUserToken;
    @SerializedName("supervisorsId")
    @Expose
    private String supervisorsId;
    @SerializedName("supervisorsName")
    @Expose
    private String supervisorsName;
    @SerializedName("supervisorsPhone")
    @Expose
    private String supervisorsPhone;
    @SerializedName("supervisorsAddress")
    @Expose
    private String supervisorsAddress;
    @SerializedName("supervisorsAction")
    @Expose
    private Object supervisorsAction;
    @SerializedName("supervisorsStartMoveLat")
    @Expose
    private Object supervisorsStartMoveLat;
    @SerializedName("supervisorsStartMoveLng")
    @Expose
    private Object supervisorsStartMoveLng;
    @SerializedName("supervisorsEndMoveLat")
    @Expose
    private Object supervisorsEndMoveLat;
    @SerializedName("supervisorsEndMoveLng")
    @Expose
    private Object supervisorsEndMoveLng;
    @SerializedName("supervisorsDateStart")
    @Expose
    private String supervisorsDateStart;
    @SerializedName("supervisorsTimeStart")
    @Expose
    private String supervisorsTimeStart;
    @SerializedName("supervisorsDateEnd")
    @Expose
    private String supervisorsDateEnd;
    @SerializedName("supervisorsTimeEnd")
    @Expose
    private String supervisorsTimeEnd;
    @SerializedName("busesId")
    @Expose
    private String busesId;
    @SerializedName("busesName")
    @Expose
    private String busesName;
    @SerializedName("busesNumberBus")
    @Expose
    private String busesNumberBus;
    @SerializedName("busesCapacity")
    @Expose
    private String busesCapacity;
    @SerializedName("busesNumberStudent")
    @Expose
    private String busesNumberStudent;
    @SerializedName("routesId")
    @Expose
    private String routesId;
    @SerializedName("routesName")
    @Expose
    private String routesName;
    @SerializedName("routesLatStartPoint")
    @Expose
    private String routesLatStartPoint;
    @SerializedName("routesLngStartPoint")
    @Expose
    private String routesLngStartPoint;
    @SerializedName("routesLatEndPoint")
    @Expose
    private String routesLatEndPoint;
    @SerializedName("routesLngEndPoint")
    @Expose
    private String routesLngEndPoint;
    @SerializedName("routingId")
    @Expose
    private String routingId;
    @SerializedName("routingStudentId")
    @Expose
    private String routingStudentId;
    @SerializedName("studentName")
    @Expose
    private String studentName;
    @SerializedName("studentAddress")
    @Expose
    private String studentAddress;
    @SerializedName("studentLat")
    @Expose
    private String studentLat;
    @SerializedName("studentLng")
    @Expose
    private String studentLng;
    @SerializedName("parentId")
    @Expose
    private String parentId;
    @SerializedName("parentName")
    @Expose
    private String parentName;
    @SerializedName("parentAddress")
    @Expose
    private String parentAddress;
    @SerializedName("parentPhone")
    @Expose
    private String parentPhone;

    public String getSupervisorsUserToken() {
        return supervisorsUserToken;
    }

    public void setSupervisorsUserToken(String supervisorsUserToken) {
        this.supervisorsUserToken = supervisorsUserToken;
    }

    public String getSupervisorsId() {
        return supervisorsId;
    }

    public void setSupervisorsId(String supervisorsId) {
        this.supervisorsId = supervisorsId;
    }

    public String getSupervisorsName() {
        return supervisorsName;
    }

    public void setSupervisorsName(String supervisorsName) {
        this.supervisorsName = supervisorsName;
    }

    public String getSupervisorsPhone() {
        return supervisorsPhone;
    }

    public void setSupervisorsPhone(String supervisorsPhone) {
        this.supervisorsPhone = supervisorsPhone;
    }

    public String getSupervisorsAddress() {
        return supervisorsAddress;
    }

    public void setSupervisorsAddress(String supervisorsAddress) {
        this.supervisorsAddress = supervisorsAddress;
    }

    public Object getSupervisorsAction() {
        return supervisorsAction;
    }

    public void setSupervisorsAction(Object supervisorsAction) {
        this.supervisorsAction = supervisorsAction;
    }

    public Object getSupervisorsStartMoveLat() {
        return supervisorsStartMoveLat;
    }

    public void setSupervisorsStartMoveLat(Object supervisorsStartMoveLat) {
        this.supervisorsStartMoveLat = supervisorsStartMoveLat;
    }

    public Object getSupervisorsStartMoveLng() {
        return supervisorsStartMoveLng;
    }

    public void setSupervisorsStartMoveLng(Object supervisorsStartMoveLng) {
        this.supervisorsStartMoveLng = supervisorsStartMoveLng;
    }

    public Object getSupervisorsEndMoveLat() {
        return supervisorsEndMoveLat;
    }

    public void setSupervisorsEndMoveLat(Object supervisorsEndMoveLat) {
        this.supervisorsEndMoveLat = supervisorsEndMoveLat;
    }

    public Object getSupervisorsEndMoveLng() {
        return supervisorsEndMoveLng;
    }

    public void setSupervisorsEndMoveLng(Object supervisorsEndMoveLng) {
        this.supervisorsEndMoveLng = supervisorsEndMoveLng;
    }

    public String getSupervisorsDateStart() {
        return supervisorsDateStart;
    }

    public void setSupervisorsDateStart(String supervisorsDateStart) {
        this.supervisorsDateStart = supervisorsDateStart;
    }

    public String getSupervisorsTimeStart() {
        return supervisorsTimeStart;
    }

    public void setSupervisorsTimeStart(String supervisorsTimeStart) {
        this.supervisorsTimeStart = supervisorsTimeStart;
    }

    public String getSupervisorsDateEnd() {
        return supervisorsDateEnd;
    }

    public void setSupervisorsDateEnd(String supervisorsDateEnd) {
        this.supervisorsDateEnd = supervisorsDateEnd;
    }

    public String getSupervisorsTimeEnd() {
        return supervisorsTimeEnd;
    }

    public void setSupervisorsTimeEnd(String supervisorsTimeEnd) {
        this.supervisorsTimeEnd = supervisorsTimeEnd;
    }

    public String getBusesId() {
        return busesId;
    }

    public void setBusesId(String busesId) {
        this.busesId = busesId;
    }

    public String getBusesName() {
        return busesName;
    }

    public void setBusesName(String busesName) {
        this.busesName = busesName;
    }

    public String getBusesNumberBus() {
        return busesNumberBus;
    }

    public void setBusesNumberBus(String busesNumberBus) {
        this.busesNumberBus = busesNumberBus;
    }

    public String getBusesCapacity() {
        return busesCapacity;
    }

    public void setBusesCapacity(String busesCapacity) {
        this.busesCapacity = busesCapacity;
    }

    public String getBusesNumberStudent() {
        return busesNumberStudent;
    }

    public void setBusesNumberStudent(String busesNumberStudent) {
        this.busesNumberStudent = busesNumberStudent;
    }

    public String getRoutesId() {
        return routesId;
    }

    public void setRoutesId(String routesId) {
        this.routesId = routesId;
    }

    public String getRoutesName() {
        return routesName;
    }

    public void setRoutesName(String routesName) {
        this.routesName = routesName;
    }

    public String getRoutesLatStartPoint() {
        return routesLatStartPoint;
    }

    public void setRoutesLatStartPoint(String routesLatStartPoint) {
        this.routesLatStartPoint = routesLatStartPoint;
    }

    public String getRoutesLngStartPoint() {
        return routesLngStartPoint;
    }

    public void setRoutesLngStartPoint(String routesLngStartPoint) {
        this.routesLngStartPoint = routesLngStartPoint;
    }

    public String getRoutesLatEndPoint() {
        return routesLatEndPoint;
    }

    public void setRoutesLatEndPoint(String routesLatEndPoint) {
        this.routesLatEndPoint = routesLatEndPoint;
    }

    public String getRoutesLngEndPoint() {
        return routesLngEndPoint;
    }

    public void setRoutesLngEndPoint(String routesLngEndPoint) {
        this.routesLngEndPoint = routesLngEndPoint;
    }

    public String getRoutingId() {
        return routingId;
    }

    public void setRoutingId(String routingId) {
        this.routingId = routingId;
    }

    public String getRoutingStudentId() {
        return routingStudentId;
    }

    public void setRoutingStudentId(String routingStudentId) {
        this.routingStudentId = routingStudentId;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentAddress() {
        return parentAddress;
    }

    public void setParentAddress(String parentAddress) {
        this.parentAddress = parentAddress;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }
}
