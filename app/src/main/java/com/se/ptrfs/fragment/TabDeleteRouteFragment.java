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

public class TabDeleteRouteFragment extends Fragment {

    Button btnDeleteRoute;
    EditText editRouteName, editRouteId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_route_tab, container, false);

        editRouteName = view.findViewById(R.id.edit_route_name);
        editRouteId = view.findViewById(R.id.edit_route_id);
        btnDeleteRoute = view.findViewById(R.id.btn_delete_route);


        btnDeleteRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editRouteName.getText().toString().equals("") || !editRouteId.getText()
                        .toString().equals("")) {
                    Toast.makeText(getContext(), editRouteId.getText().toString() + " " +
                            editRouteName
                                    .getText()
                                    .toString() + " route successfully deleted!", Toast
                            .LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), " Fill all the blanks!", Toast.LENGTH_SHORT)
                            .show();
            }
        });


        return view;
    }
}
