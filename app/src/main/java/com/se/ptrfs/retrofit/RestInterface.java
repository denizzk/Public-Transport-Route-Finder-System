package com.se.ptrfs.retrofit;

import com.se.ptrfs.POJO.Location;
import com.se.ptrfs.POJO.RouteRepo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestInterface {

    @GET("routes")
    Observable<RouteRepo> getRouteRepo(@Query("api_key") String api_key, @Query("start_lat")
            Double start_lat,
                                       @Query("start_lng") Double start_lng, @Query("end_lat")
                                               String end_lat,
                                       @Query("end_lng") String end_lng);

    @GET("locations")
    Observable<List<Location>> getLocation(@Query("api_key") String api_key, @Query("q")
            String query,
                                           @Query("region") String region);
}
