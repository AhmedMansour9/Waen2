package com.waen.waen.Admin.Model;

public class BusDetail {

    String busName;
    String action;
    String busNumber;
    String supervisorName;
    String lat;
    String lng;
    int speed;

    public BusDetail() {
    }

    public BusDetail(String busName, String action, String busNumber, String supervisorName, String lat, String lng, int speed) {
        this.busName = busName;
        this.action = action;
        this.busNumber = busNumber;
        this.supervisorName = supervisorName;
        this.lat = lat;
        this.lng = lng;
        this.speed = speed;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}