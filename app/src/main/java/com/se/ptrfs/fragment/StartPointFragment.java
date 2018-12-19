package com.se.ptrfs.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.se.ptrfs.POJO.Coordinate;
import com.se.ptrfs.R;
import com.se.ptrfs.adapter.StartLocationRecyclerViewAdapter;
import com.se.ptrfs.retrofit.ApiClient;
import com.se.ptrfs.retrofit.RestInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class StartPointFragment extends Fragment {

    ImageButton startLocBtn, endLocBtn;
    EditText startAddressEdit, startCityEdit, endAddressEdit, endCityEdit;

    RestInterface restInterface;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    StartLocationRecyclerViewAdapter adapter = new StartLocationRecyclerViewAdapter();
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start_point, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = getView().findViewById(R.id.location_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        startLocBtn = getView().findViewById(R.id.btn_start_loc);
        endLocBtn = getView().findViewById(R.id.btn_end_loc);
        startAddressEdit = getView().findViewById(R.id.edit_start_address);
        endAddressEdit = getView().findViewById(R.id.edit_end_address);
        startCityEdit = getView().findViewById(R.id.edit_start_city);
        endCityEdit = getView().findViewById(R.id.edit_end_city);

        restInterface = ApiClient.getClient().create(RestInterface.class);


        startLocBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                compositeDisposable.add(restInterface.getLocation
                        ("ae1ab18538c57ddfe9cb3249e830b1f0", startAddressEdit.getText().toString(),
                                startCityEdit.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(location -> {

                            List<String> iconUrls = new ArrayList<>();
                            List<String> adresses = new ArrayList<>();
                            List<String> regions = new ArrayList<>();
                            List<Coordinate> coordinates = new ArrayList<>();

                            for (int i = 0; i < location.size(); i++) {
                                iconUrls.add(i, location.get(i).iconUrl);
                                adresses.add(i, location.get(i).name);
                                regions.add(i, location.get(i).region);
                                coordinates.add(i, location.get(i).coordinate);
                            }

                            adapter = new StartLocationRecyclerViewAdapter(iconUrls, adresses,
                                    regions, coordinates, getContext());
                            recyclerView.setAdapter(adapter);

                        })
                );
            }
        });
    }
}
