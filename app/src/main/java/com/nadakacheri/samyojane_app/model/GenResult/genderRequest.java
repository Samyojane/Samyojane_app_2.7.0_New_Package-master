package com.nadakacheri.samyojane_app.model.GenResult;

import com.google.gson.annotations.SerializedName;

public class genderRequest {

    @SerializedName("image")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public genderRequest(String image) {
        this.image = image;
    }
}
