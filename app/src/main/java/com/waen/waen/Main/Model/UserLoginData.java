package com.waen.waen.Main.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed on 17/12/2018.
 */

public class UserLoginData {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("user_token")
    @Expose
    private String userToken;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("busName")
    @Expose
    private String busName;
    @SerializedName("busNumberBus")
    @Expose
    private String busNumberBus;
    @SerializedName("busCapacity")
    @Expose
    private String busCapacity;
    @SerializedName("busNumberStudent")
    @Expose
    private String busNumberStudent;
    @SerializedName("driverName")
    @Expose
    private String driverName;
    @SerializedName("nameAdmin")
    @Expose
    private String nameAdmin;
    @SerializedName("userTokenAdmin")
    @Expose
    private String userTokenAdmin;

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("userTokenParent")
    @Expose
    private String userTokenParent;


    public String getUserTokenParent() {
        return userTokenParent;
    }

    public void setUserTokenParent(String userTokenParent) {
        this.userTokenParent = userTokenParent;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusNumberBus() {
        return busNumberBus;
    }

    public void setBusNumberBus(String busNumberBus) {
        this.busNumberBus = busNumberBus;
    }

    public String getBusCapacity() {
        return busCapacity;
    }

    public void setBusCapacity(String busCapacity) {
        this.busCapacity = busCapacity;
    }

    public String getBusNumberStudent() {
        return busNumberStudent;
    }

    public void setBusNumberStudent(String busNumberStudent) {
        this.busNumberStudent = busNumberStudent;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getNameAdmin() {
        return nameAdmin;
    }

    public void setNameAdmin(String nameAdmin) {
        this.nameAdmin = nameAdmin;
    }

    public String getUserTokenAdmin() {
        return userTokenAdmin;
    }

    public void setUserTokenAdmin(String userTokenAdmin) {
        this.userTokenAdmin = userTokenAdmin;
    }
}
