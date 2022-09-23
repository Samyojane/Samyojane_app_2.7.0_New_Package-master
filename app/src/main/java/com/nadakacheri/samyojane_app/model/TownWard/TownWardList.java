package com.nadakacheri.samyojane_app.model.TownWard;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TownWardList {
    @SerializedName("Table")
    private List<TownWardData> table = null;

    public List<TownWardData> getTable() {
        return table;
    }

    public void setTable(List<TownWardData> table) {
        this.table = table;
    }

}

