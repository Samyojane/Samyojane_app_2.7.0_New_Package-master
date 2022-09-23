package com.nadakacheri.samyojane_app.model.TownWard;

import com.google.gson.annotations.SerializedName;

public class TownWardData {
    @SerializedName("CODE")
    private Integer code;

    @SerializedName("TownCode")
    private Integer townCode;

    @SerializedName("KANNADANAME")
    private String kannadaname;

    @SerializedName("ENGLISHNAME")
    private String englishname;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getTownCode() {
        return townCode;
    }

    public void setTownCode(Integer townCode) {
        this.townCode = townCode;
    }

    public String getKannadaname() {
        return kannadaname;
    }

    public void setKannadaname(String kannadaname) {
        this.kannadaname = kannadaname;
    }

    public String getEnglishname() {
        return englishname;
    }

    public void setEnglishname(String englishname) {
        this.englishname = englishname;
    }

}


