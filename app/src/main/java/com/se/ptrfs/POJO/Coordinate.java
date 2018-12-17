package com.se.ptrfs.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coordinate {

    @SerializedName("Lat")
    @Expose
    public Float lat;
    @SerializedName("Lng")
    @Expose
    public Float lng;
}
