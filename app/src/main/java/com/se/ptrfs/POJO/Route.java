package com.se.ptrfs.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Route {

    @SerializedName("PreferenceLabel")
    @Expose
    public String preferenceLabel;
    @SerializedName("DurationMinutes")
    @Expose
    public Integer durationMinutes;
    @SerializedName("WalkMinutes")
    @Expose
    public Integer walkMinutes;
    @SerializedName("DepartureTime")
    @Expose
    public String departureTime;
    @SerializedName("ArrivalTime")
    @Expose
    public String arrivalTime;
    @SerializedName("Price")
    @Expose
    public Float price;
    @SerializedName("Currency")
    @Expose
    public String currency;
    @SerializedName("RouteSegments")
    @Expose
    public List<RouteSegment> routeSegments = null;
}
