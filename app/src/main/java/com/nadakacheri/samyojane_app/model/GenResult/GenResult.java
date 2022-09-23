package com.nadakacheri.samyojane_app.model.GenResult;

import com.google.gson.annotations.SerializedName;

public class GenResult {
    @SerializedName("person")
    private String person;

    @SerializedName("human_face")
    private String humanFace;

    @SerializedName("gender")
    private String gender;

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getHumanFace() {
        return humanFace;
    }

    public void setHumanFace(String humanFace) {
        this.humanFace = humanFace;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
