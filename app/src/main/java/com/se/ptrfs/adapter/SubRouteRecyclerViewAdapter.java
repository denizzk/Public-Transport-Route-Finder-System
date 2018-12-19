package com.se.ptrfs.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.se.ptrfs.R;

import java.util.ArrayList;
import java.util.List;

public class SubRouteRecyclerViewAdapter extends RecyclerView.Adapter<SubRouteRecyclerViewAdapter
        .ViewHolder> {

    private List<Integer> mSubrouteSegmentType = new ArrayList<>();
    private List<String> mSubrouteIcons = new ArrayList<>();
    private List<Integer> mSubDurations = new ArrayList<>();
    private List<Integer> mSubrouteStopCount = new ArrayList<>();
    private Context mContext;

    public SubRouteRecyclerViewAdapter(List<Integer> mSubrouteSegmentType, List<String>
            mSubrouteIcons, List<Integer> mSubDurations, List<Integer> mSubrouteStopCount,
                                       Context mContext) {
        this.mSubrouteSegmentType = mSubrouteSegmentType;
        this.mSubrouteIcons = mSubrouteIcons;
        this.mSubDurations = mSubDurations;
        this.mSubrouteStopCount = mSubrouteStopCount;
        this.mContext = mContext;
    }

    public SubRouteRecyclerViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listsubroutes,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubRouteRecyclerViewAdapter.ViewHolder holder, int
            position) {

        Glide.with(mContext)
                .asBitmap()
                .load(mSubrouteIcons.get(position))
                .into(holder.subrouteIcon);

        holder.subDuration.setText(mSubDurations.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return mSubrouteIcons.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView subrouteIcon;
        TextView subDuration;
        LinearLayout parentLayout;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            subrouteIcon = itemView.findViewById(R.id.subroute_icon);
            subDuration = itemView.findViewById(R.id.txt_subroute_duration);
            parentLayout = itemView.findViewById(R.id.child_layout);
        }
    }
}