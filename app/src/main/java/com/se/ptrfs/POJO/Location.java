package com.se.ptrfs.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("Id")
    @Expose
    public String id;
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("Coordinate")
    @Expose
    public Coordinate coordinate;
    @SerializedName("AreaName")
    @Expose
    public String areaName;
    @SerializedName("IconUrl")
    @Expose
    public String iconUrl;
    @SerializedName("Region")
    @Expose
    public String region;
}
