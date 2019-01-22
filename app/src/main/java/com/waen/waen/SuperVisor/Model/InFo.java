package com.waen.waen.SuperVisor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed on 24/12/2018.
 */

public class InFo {

    @SerializedName("routingID")
    @Expose
    private String routingID;
    @SerializedName("routingStudentId")
    @Expose
    private String routingStudentId;
    @SerializedName("studentName")
    @Expose
    private String studentName;
    @SerializedName("studentAddress")
    @Expose
    private String studentAddress;
    @SerializedName("parentPhone")
    @Expose
    private String parentPhone;
    @SerializedName("parentAddress")
    @Expose
    private String parentAddress;
    @SerializedName("studentLat")
    @Expose
    private String studentLat;
    @SerializedName("studentLng")
    @Expose
    private String studentLng;
    @SerializedName("routingCountChair")
    @Expose
    private String routingCountChair;

    public String getRoutingID() {
        return routingID;
    }

    public void setRoutingID(String routingID) {
        this.routingID = routingID;
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

    public String getRoutingCountChair() {
        return routingCountChair;
    }

    public void setRoutingCountChair(String routingCountChair) {
        this.routingCountChair = routingCountChair;
    }
}
