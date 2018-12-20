package com.se.ptrfs.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.se.ptrfs.POJO.Coordinate;
import com.se.ptrfs.R;
import com.se.ptrfs.adapter.RouteRecyclerViewAdapter;
import com.se.ptrfs.retrofit.ApiClient;
import com.se.ptrfs.retrofit.RestInterface;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class TabNearbyStopFragment extends Fragment {

    EditText editAddress, editCity;
    ImageButton btnNearStop;
    MapView mapView;

    RestInterface restInterface;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        Mapbox.getInstance(getActivity(), "pk" +
                ".eyJ1IjoiZGthcmFrYXlhIiwiYSI6ImNqcHZsbG12ZTAydDk0OHBxZG11ams3a24ifQ" +
                ".zFitn6awDGV9GkYMM7xt3w");
        View view = inflater.inflate(R.layout.fragment_nearby_stop, container, false);

        restInterface = ApiClient.getClient().create(RestInterface.class);

        editAddress = view.findViewById(R.id.edit_destination_address);
        editCity = view.findViewById(R.id.edit_destination_city);
        btnNearStop = view.findViewById(R.id.btn_near_stop);
        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        btnNearStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editCity.getText().toString().length() > 0) {

                    List<String> iconUrls = new ArrayList<>();
                    List<String> adresses = new ArrayList<>();
                    List<String> regions = new ArrayList<>();
                    List<Coordinate> coordinates = new ArrayList<>();

                    compositeDisposable.add(restInterface.getLocation
                            ("ae1ab18538c57ddfe9cb3249e830b1f0", editAddress.getText()
                                            .toString().toLowerCase(),
                                    editCity.getText().toString().toLowerCase())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(location -> {

                                for (int i = 0; i < location.size(); i++) {
                                    iconUrls.add(i, location.get(i).iconUrl);
                                    adresses.add(i, location.get(i).name);
                                    regions.add(i, location.get(i).region);
                                    coordinates.add(i, location.get(i).coordinate);
                                }

                                mapView.getMapAsync(new OnMapReadyCallback() {

                                    @Override
                                    public void onMapReady(MapboxMap mapboxMap) {

                                        mapboxMap.removeAnnotations();

                                        for (int i = 0; i < coordinates.size(); i++) {
                                            LatLng coordinate = new LatLng(coordinates
                                                    .get(i).lat, coordinates
                                                    .get(i).lng);

                                            mapboxMap.addMarker(new MarkerOptions()
                                                    .position(coordinate)
                                                    .title(adresses.get(i)
                                                            .toUpperCase()));
                                        }
                                        CameraPosition position = new CameraPosition
                                                .Builder()
                                                .target(new LatLng(coordinates.get(0)
                                                        .lat, coordinates.get(0).lng))
                                                .zoom(10)
                                                .tilt(20)
                                                .build();

                                        mapboxMap.animateCamera(CameraUpdateFactory
                                                        .newCameraPosition(position),
                                                100);
                                    }
                                });
                            })
                    );
                }
            }
        });
        return view;
    }
}
