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

public class TabDeleteStopFragment extends Fragment {

    Button btnDeleteStop;
    EditText editStopName, editStopId, editStopLat, editStopLng;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_stop_tab, container, false);

        editStopName = view.findViewById(R.id.edit_stop_name);
        editStopId = view.findViewById(R.id.edit_stop_id);
        editStopLat = view.findViewById(R.id.edit_stop_lat);
        editStopLng = view.findViewById(R.id.edit_stop_lng);
        btnDeleteStop = view.findViewById(R.id.btn_delete_stop);


        btnDeleteStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editStopName.getText().toString().equals("") && !editStopId.getText()
                        .toString().equals("") &&
                        !editStopLat.getText().toString().equals("") && !editStopLng.getText()
                        .toString().equals("")) {
                    Toast.makeText(getContext(), editStopId.getText().toString() + " " +
                            editStopName
                                    .getText()
                                    .toString() + " stop successfully deleted!", Toast
                            .LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), " Fill all the blanks!", Toast.LENGTH_SHORT)
                            .show();
            }
        });


        return view;
    }
}
