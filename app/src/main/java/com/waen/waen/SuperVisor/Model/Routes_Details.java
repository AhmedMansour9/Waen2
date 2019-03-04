package com.waen.waen.SuperVisor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ahmed on 23/12/2018.
 */

public class Routes_Details {


    @SerializedName("data")
    @Expose
    private List<InFo> data = null;
    @SerializedName("supervisorsID")
    @Expose
    private String supervisorsID;
    @SerializedName("supervisorsName")
    @Expose
    private String supervisorsName;
    @SerializedName("busId")
    @Expose
    private String busId;
    @SerializedName("busName")
    @Expose
    private String busName;
    @SerializedName("busNumbers")
    @Expose
    private String busNumbers;
    @SerializedName("busCapacity")
    @Expose
    private String busCapacity;
    @SerializedName("busesNumberStudents")
    @Expose
    private String busesNumberStudents;
    @SerializedName("busesDriversName")
    @Expose
    private String busesDriversName;
    @SerializedName("routesId")
    @Expose
    private String routesId;
    @SerializedName("routesName")
    @Expose
    private String routesName;
    @SerializedName("routesNumberStudents")
    @Expose
    private String routesNumberStudents;
    @SerializedName("routesLatStartPint")
    @Expose
    private String routesLatStartPint;
    @SerializedName("routesLngStartPint")
    @Expose
    private String routesLngStartPint;
    @SerializedName("routesLatEndPint")
    @Expose
    private String routesLatEndPint;
    @SerializedName("routesLngEndPint")
    @Expose
    private String routesLngEndPint;
    @SerializedName("busSpeed")
    @Expose
    private String busSpeed;

    public String getBusSpeed() {
        return busSpeed;
    }

    public void setBusSpeed(String busSpeed) {
        this.busSpeed = busSpeed;
    }


    public List<InFo> getData() {
        return data;
    }

    public void setData(List<InFo> data) {
        this.data = data;
    }

    public String getSupervisorsID() {
        return supervisorsID;
    }

    public void setSupervisorsID(String supervisorsID) {
        this.supervisorsID = supervisorsID;
    }

    public String getSupervisorsName() {
        return supervisorsName;
    }

    public void setSupervisorsName(String supervisorsName) {
        this.supervisorsName = supervisorsName;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusNumbers() {
        return busNumbers;
    }

    public void setBusNumbers(String busNumbers) {
        this.busNumbers = busNumbers;
    }

    public String getBusCapacity() {
        return busCapacity;
    }

    public void setBusCapacity(String busCapacity) {
        this.busCapacity = busCapacity;
    }

    public String getBusesNumberStudents() {
        return busesNumberStudents;
    }

    public void setBusesNumberStudents(String busesNumberStudents) {
        this.busesNumberStudents = busesNumberStudents;
    }

    public String getBusesDriversName() {
        return busesDriversName;
    }

    public void setBusesDriversName(String busesDriversName) {
        this.busesDriversName = busesDriversName;
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

    public String getRoutesNumberStudents() {
        return routesNumberStudents;
    }

    public void setRoutesNumberStudents(String routesNumberStudents) {
        this.routesNumberStudents = routesNumberStudents;
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

}