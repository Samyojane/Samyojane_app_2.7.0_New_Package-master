package com.nadakacheri.samyojane_app.model.TownWard;

import com.google.gson.annotations.SerializedName;

public class TownWardResponse {
    @SerializedName("StatusCode")
    private Integer statusCode;

    @SerializedName("StatusMessage")
    private TownWardList statusMessage;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public TownWardList getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(TownWardList statusMessage) {
        this.statusMessage = statusMessage;
    }

}

