package com.se.ptrfs.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RouteSegment {

    @SerializedName("RouteSegmentType")
    @Expose
    public Integer routeSegmentType;
    @SerializedName("IconUrl")
    @Expose
    public String iconUrl;
    @SerializedName("DurationMinutes")
    @Expose
    public Integer durationMinutes;
    @SerializedName("WalkDistanceMeters")
    @Expose
    public Integer walkDistanceMeters;
    @SerializedName("DistanceMeters")
    @Expose
    public Integer distanceMeters;
    @SerializedName("StopsCount")
    @Expose
    public Integer stopsCount;
    @SerializedName("StartPoint")
    @Expose
    public StartPoint startPoint;
    @SerializedName("EndPoint")
    @Expose
    public EndPoint endPoint;
    @SerializedName("Shape")
    @Expose
    public String shape;
    @SerializedName("Transport")
    @Expose
    public Object transport;
    @SerializedName("OtherTransports")
    @Expose
    public List<Object> otherTransports = null;
}
