package com.se.ptrfs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.se.ptrfs.R;

public class PointViewerFragment extends Fragment {

    ImageView icon;
    TextView address, region;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_point_viewer, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        icon = getView().findViewById(R.id.icon_img);
        address = getView().findViewById(R.id.text_address);
        region = getView().findViewById(R.id.text_region);

        Glide.with(getContext())
                .asBitmap()
                .load(getArguments().getString("iconUrl"))
                .into(icon);

        address.setText(getArguments().getString("address"));
        String s = getArguments().getString("region");
        region.setText(Character.toUpperCase(s.charAt(0)) + s.substring(1));
    }
}
