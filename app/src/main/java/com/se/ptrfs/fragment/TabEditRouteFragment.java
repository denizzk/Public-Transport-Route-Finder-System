package com.se.ptrfs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.se.ptrfs.R;

public class TabEditRouteFragment extends Fragment {

    Button btnEditRoute;
    EditText editRouteName, editRouteId, editRouteStartLat, editRouteStartLng, editRouteEndLat,
            editRouteEndLng;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_route_tab, container, false);

        editRouteName = view.findViewById(R.id.edit_route_name);
        editRouteId = view.findViewById(R.id.edit_route_id);
        editRouteStartLat = view.findViewById(R.id.edit_route_start_lat);
        editRouteStartLng = view.findViewById(R.id.edit_route_start_lng);
        editRouteEndLat = view.findViewById(R.id.edit_route_end_lat);
        editRouteEndLng = view.findViewById(R.id.edit_route_end_lng);
        btnEditRoute = view.findViewById(R.id.btn_edit_route);


        btnEditRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editRouteName.getText().toString().equals("") && !editRouteId.getText()
                        .toString().equals("") &&
                        !editRouteStartLat.getText().toString().equals("") && !editRouteStartLng
                        .getText()
                        .toString().equals("") && !editRouteEndLat.getText().toString().equals
                        ("") && !editRouteEndLng.getText()
                        .toString().equals("")) {
                    Toast.makeText(getContext(), editRouteId.getText().toString() + " " +
                            editRouteName
                                    .getText()
                                    .toString() + " route successfully updated!", Toast
                            .LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), " Fill all the blanks!", Toast.LENGTH_SHORT)
                            .show();
            }
        });


        return view;
    }
}

