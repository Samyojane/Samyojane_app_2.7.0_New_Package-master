package com.nadakacheri.samyojane_app.model.GenResult;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class genderResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("results")
    private List<GenResult> results = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<GenResult> getResults() {
        return results;
    }

    public void setResults(List<GenResult> results) {
        this.results = results;
    }
}
