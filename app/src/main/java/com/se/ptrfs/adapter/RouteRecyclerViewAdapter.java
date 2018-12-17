package com.se.ptrfs.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.se.ptrfs.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class RouteRecyclerViewAdapter extends RecyclerView.Adapter<RouteRecyclerViewAdapter
        .ViewHolder> {

    private List<String> mPreferences = new ArrayList<>();
    private List<Integer> mDurations = new ArrayList<>();
    private List<Integer> mWalktimes = new ArrayList<>();
    private List<String> mDepartureTime = new ArrayList<>();
    private List<String> mArrivalTime = new ArrayList<>();
    private List<Float> mCost = new ArrayList<>();
    private List<String> mCurrency = new ArrayList<>();
    private List<List<Integer>> mSubrouteSegmentType = new ArrayList<>();
    private List<List<String>> mSubrouteIcons = new ArrayList<>();
    private List<List<Integer>> mSubDurations = new ArrayList<>();
    private List<List<Integer>> mSubrouteStopCount = new ArrayList<>();
    private Context mContext;

    public RouteRecyclerViewAdapter(List<String> mPreferences, List<Integer> mDurations,
                                    List<Integer> mWalktimes, List<String> mDepartureTime,
                                    List<String> mArrivalTime, List<Float> mCost, List<String>
                                            mCurrency, List<List<Integer>> mSubrouteSegmentType,
                                    List<List<String>> mSubrouteIcons, List<List<Integer>>
                                            mSubDurations, List<List<Integer>>
                                            mSubrouteStopCount, Context mContext) {
        this.mPreferences = mPreferences;
        this.mDurations = mDurations;
        this.mWalktimes = mWalktimes;
        this.mDepartureTime = mDepartureTime;
        this.mArrivalTime = mArrivalTime;
        this.mCost = mCost;
        this.mCurrency = mCurrency;
        this.mSubrouteSegmentType = mSubrouteSegmentType;
        this.mSubrouteIcons = mSubrouteIcons;
        this.mSubDurations = mSubDurations;
        this.mSubrouteStopCount = mSubrouteStopCount;
        this.mContext = mContext;
    }

    public RouteRecyclerViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listroutes,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    @Override
    public void onBindViewHolder(@NonNull RouteRecyclerViewAdapter.ViewHolder holder, int
            position) {

        holder.preference.setText(mPreferences.get(position));
        holder.walkMinutes.setText("Walking time: " + mWalktimes.get(position) + " min");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm");
        mdformat.setTimeZone(TimeZone.getTimeZone("Europe/Istanbul"));
        if (mDepartureTime.get(position).equals(mdformat.format(calendar.getTime())))
            holder.departTime.setText("Leave now ");
        else
            holder.departTime.setText("Leave at " + mDepartureTime.get(position));
        holder.arriveTime.setText("Arrive at " + mArrivalTime.get(position));
        if (mCost.get(position) == 0)
            holder.cost.setText("-");
        else
            holder.cost.setText("TRY " + mCost.get(position));
        holder.duration.setText(mDurations.get(position) + " min");

        LinearLayoutManager subrouteLayoutManager = new LinearLayoutManager(holder.subroute_rv
                .getContext(), LinearLayout.HORIZONTAL, false);
        subrouteLayoutManager.setInitialPrefetchItemCount(0);

        holder.subroute_rv.setLayoutManager(subrouteLayoutManager);
        holder.subroute_rv.setAdapter(new SubRouteRecyclerViewAdapter(mSubrouteSegmentType.get
                (position),
                mSubrouteIcons.get(position), mSubDurations.get(position), mSubrouteStopCount.get
                (position), mContext));
        holder.subroute_rv.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return mPreferences.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView preference, duration, walkMinutes, departTime, arriveTime, cost;
        LinearLayout parentLayout;
        RecyclerView subroute_rv;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            preference = itemView.findViewById(R.id.txt_pref);
            duration = itemView.findViewById(R.id.txt_duration);
            walkMinutes = itemView.findViewById(R.id.txt_walktime);
            departTime = itemView.findViewById(R.id.txt_departure_time);
            arriveTime = itemView.findViewById(R.id.txt_arrival_time);
            cost = itemView.findViewById(R.id.txt_cost);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            subroute_rv = itemView.findViewById(R.id.subroute_recycler_view);
        }
    }
}

