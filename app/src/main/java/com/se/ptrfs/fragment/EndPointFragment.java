package com.se.ptrfs.fragment;

import android.os.Bundle;
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
import com.se.ptrfs.adapter.EndLocationRecyclerViewAdapter;
import com.se.ptrfs.retrofit.ApiClient;
import com.se.ptrfs.retrofit.RestInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class EndPointFragment extends Fragment {

    ImageButton endLocBtn;
    EditText endAddressEdit, endCityEdit;

    RestInterface restInterface;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    EndLocationRecyclerViewAdapter adapter = new EndLocationRecyclerViewAdapter();
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_end_point, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = getView().findViewById(R.id.location_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        endLocBtn = getView().findViewById(R.id.btn_end_loc);
        endAddressEdit = getView().findViewById(R.id.edit_end_address);

        restInterface = ApiClient.getClient().create(RestInterface.class);

        endLocBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                compositeDisposable.add(restInterface.getLocation
                        ("ae1ab18538c57ddfe9cb3249e830b1f0", endAddressEdit.getText()
                                        .toString().toLowerCase(),
                                getArguments().getString("City").toLowerCase())
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

                            adapter = new EndLocationRecyclerViewAdapter(iconUrls, adresses,
                                    regions, coordinates, getContext());
                            recyclerView.setAdapter(adapter);

                        })
                );
            }
        });
    }
}
