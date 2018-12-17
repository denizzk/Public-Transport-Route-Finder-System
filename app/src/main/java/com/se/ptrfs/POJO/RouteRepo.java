package com.se.ptrfs.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RouteRepo {

    @SerializedName("Routes")
    @Expose
    public List<Route> routes = null;
}
