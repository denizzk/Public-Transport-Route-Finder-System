package com.se.ptrfs;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.se.ptrfs.adapter.RouteRecyclerViewAdapter;
import com.se.ptrfs.retrofit.ApiClient;
import com.se.ptrfs.retrofit.RestInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    RestInterface restInterface;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    Button searchBtn;
    FrameLayout startFrame, endFrame;

    RouteRecyclerViewAdapter adapter = new RouteRecyclerViewAdapter();
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restInterface = ApiClient.getClient().create(RestInterface.class);

        startFrame = findViewById(R.id.fragment_container_start);
        endFrame = findViewById(R.id.fragment_container_end);
        searchBtn = findViewById(R.id.btn_search);

        recyclerView = findViewById(R.id.route_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        StartPointFragment startPointFragment = new StartPointFragment();
        fragmentTransaction.add(R.id.fragment_container_start, startPointFragment);
        fragmentTransaction.commit();

        if (startFrame.isClickable()) {
            startFrame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment startPointFragment = new StartPointFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container_start,
                            startPointFragment).addToBackStack(null).commit();
                }
            });
        }

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        EndPointFragment endPointFragment = new EndPointFragment();
        fragmentTransaction.add(R.id.fragment_container_end, endPointFragment);
        fragmentTransaction.commit();

        if (endFrame.isClickable()) {
            endFrame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment endPointFragment = new EndPointFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container_end,
                            endPointFragment).addToBackStack(null).commit();
                }
            });
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        if (true) {
            searchBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sp = getSharedPreferences("coordinates", Activity
                            .MODE_PRIVATE);
                    float start_lat = sp.getFloat("start_lat", -1);
                    float end_lat = sp.getFloat("end_lat", -1);
                    float start_lng = sp.getFloat("start_lng", -1);
                    float end_lng = sp.getFloat("end_lng", -1);

                    compositeDisposable.add(restInterface.getRouteRepo
                            ("ae1ab18538c57ddfe9cb3249e830b1f0",
                                    new Double(start_lat),
                                    new Double(start_lng), String.valueOf(end_lat), String.valueOf
                                            (end_lng))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(routeRepo -> {

                                List<String> preferences = new ArrayList<>();
                                List<Integer> durations = new ArrayList<>();
                                List<Integer> walktimes = new ArrayList<>();
                                List<String> departureTime = new ArrayList<>();
                                List<String> arrivalTime = new ArrayList<>();
                                List<Float> cost = new ArrayList<>();
                                List<String> currency = new ArrayList<>();
                                List<List<Integer>> subrouteSegmentType = new ArrayList<>();
                                List<List<String>> subrouteIcons = new ArrayList<>();
                                List<List<Integer>> subDurations = new ArrayList<>();
                                List<List<Integer>> subrouteStopCount = new ArrayList<>();

                                for (int i = 0; i < routeRepo.routes.size(); i++) {
                                    preferences.add(i, routeRepo.routes.get(i).preferenceLabel);
                                    durations.add(i, routeRepo.routes.get(i).durationMinutes);
                                    walktimes.add(i, routeRepo.routes.get(i).walkMinutes);
                                    departureTime.add(i, routeRepo.routes.get(i).departureTime);
                                    arrivalTime.add(i, routeRepo.routes.get(i).arrivalTime);
                                    cost.add(i, routeRepo.routes.get(i).price);
                                    currency.add(i, routeRepo.routes.get(i).currency);

                                    subrouteSegmentType.add(new ArrayList<>());
                                    subrouteIcons.add( new ArrayList<>());
                                    subDurations.add( new ArrayList<>());
                                    subrouteStopCount.add( new ArrayList<>());
                                    for (int j = 0; j < routeRepo.routes.get(i).routeSegments
                                            .size();
                                         j++) {
                                        subrouteSegmentType.get(i).add(j, routeRepo.routes.get(i)
                                                .routeSegments.get
                                                        (j).routeSegmentType);
                                        subrouteIcons.get(i).add(j, routeRepo.routes.get(i)
                                                .routeSegments.get
                                                        (j).iconUrl);
                                        subDurations.get(i).add(j, routeRepo.routes.get(i)
                                                .routeSegments
                                                .get
                                                        (j).durationMinutes);
                                        subrouteStopCount.get(i).add(j, routeRepo.routes.get(i)
                                                .routeSegments.get
                                                        (j).stopsCount);

                                    }
                                }

                                adapter = new RouteRecyclerViewAdapter(preferences, durations,
                                        walktimes, departureTime, arrivalTime, cost, currency,
                                        subrouteSegmentType,
                                        subrouteIcons, subDurations, subrouteStopCount,
                                        getBaseContext());
                                recyclerView.setAdapter(adapter);

                            })
                    );
                }
            });
        }
    }
}
