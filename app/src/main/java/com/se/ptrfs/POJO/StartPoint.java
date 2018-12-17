package com.se.ptrfs.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class StartPoint {

    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("Coordinate")
    @Expose
    public Coordinate coordinate;
    @SerializedName("Time")
    @Expose
    public String time;
    @SerializedName("Id")
    @Expose
    public String id;
}
