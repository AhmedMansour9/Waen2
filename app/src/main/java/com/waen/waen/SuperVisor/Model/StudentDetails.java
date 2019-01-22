package com.waen.waen.SuperVisor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed on 25/12/2018.
 */

public class StudentDetails {

    @SerializedName("studentId")
    @Expose
    private String studentId;
    @SerializedName("studentName")
    @Expose
    private String studentName;
    @SerializedName("studentAddress")
    @Expose
    private String studentAddress;
    @SerializedName("parentName")
    @Expose
    private String parentName;
    @SerializedName("parentPhone")
    @Expose
    private String parentPhone;
    @SerializedName("parentAddress")
    @Expose
    private String parentAddress;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
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
}
