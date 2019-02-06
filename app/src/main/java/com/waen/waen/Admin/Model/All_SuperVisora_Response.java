package com.waen.waen.Admin.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class All_SuperVisora_Response {

    @SerializedName("data")
    @Expose
    private List<AllSuperVisors> data = null;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("error")
    @Expose
    private String error;

    public List<AllSuperVisors> getData() {
        return data;
    }

    public void setData(List<AllSuperVisors> data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
