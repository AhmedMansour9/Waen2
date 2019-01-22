package com.waen.waen.SuperVisor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed on 24/12/2018.
 */

public class StartTrip_Response {
    @SerializedName("data")
    @Expose
    private StartTrip data;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("error")
    @Expose
    private String error;

    public StartTrip getData() {
        return data;
    }

    public void setData(StartTrip data) {
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
