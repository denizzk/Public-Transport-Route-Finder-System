package com.se.ptrfs.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.se.ptrfs.POJO.Coordinate;
import com.se.ptrfs.R;
import com.se.ptrfs.fragment.PointViewerFragment;

import java.util.ArrayList;
import java.util.List;

public class EndLocationRecyclerViewAdapter extends RecyclerView
        .Adapter<EndLocationRecyclerViewAdapter.ViewHolder> {

    private List<String> mIcons = new ArrayList<>();
    private List<String> mAdresses = new ArrayList<>();
    private List<String> mRegions = new ArrayList<>();
    private List<Coordinate> mCoordinates = new ArrayList<>();
    private Context mContext;

    public EndLocationRecyclerViewAdapter(List<String> mIcons, List<String> mAdresses, List<String>
            mRegions, List<Coordinate> mCoordinates, Context mContext) {
        this.mIcons = mIcons;
        this.mAdresses = mAdresses;
        this.mRegions = mRegions;
        this.mCoordinates = mCoordinates;
        this.mContext = mContext;
    }

    public EndLocationRecyclerViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listloc,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EndLocationRecyclerViewAdapter.ViewHolder holder, int
            position) {

        if (!mIcons.get(position).equals("")) {
            Glide.with(mContext)
                    .asBitmap()
                    .load(mIcons.get(position))
                    .into(holder.icon);
        }
        holder.address.setText(mAdresses.get(position));
        holder.region.setText(mRegions.get(position));

        SharedPreferences sp = mContext.getSharedPreferences("coordinates", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("end_lat", mCoordinates.get(position).lat);
        editor.putFloat("end_lng", mCoordinates.get(position).lng);
        editor.apply();

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PointViewerFragment();
                Bundle b = new Bundle();
                b.putString("iconUrl", mIcons.get(position));
                b.putString("address", mAdresses.get(position));
                b.putString("region", mRegions.get(position));
                b.putDouble("lat", mCoordinates.get(position).lat);
                b.putDouble("lng", mCoordinates.get(position).lng);
                fragment.setArguments(b);
                FragmentManager fragmentManager = ((FragmentActivity) mContext)
                        .getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container_end,
                        fragment).addToBackStack(null).commit();


            }
        });
    }

    @Override
    public int getItemCount() {
        return mAdresses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView address;
        TextView region;
        LinearLayout parentLayout;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon_img);
            address = itemView.findViewById(R.id.text_address);
            region = itemView.findViewById(R.id.text_region);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
