package com.waen.waen.Admin.Fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.waen.waen.Admin.Adapter.LastMessage_Adapter_admin;
import com.waen.waen.Admin.NetworikConntection;
import com.waen.waen.Language;
import com.waen.waen.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Feedback_admin extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    public Feedback_admin() {
        // Required empty public constructor
    }
    View view;
    RecyclerView recyclerMessages;
    LastMessage_Adapter_admin LastMessage_adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    NetworikConntection networikConntection;
    FrameLayout Frame_FeedBack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_feedback_admin, container, false);
        networikConntection=new NetworikConntection(getActivity());
        init();
        Recyclview();
        SwipRefresh();


        return view;
    }
    public void init(){
        Frame_FeedBack=view.findViewById(R.id.Frame_FeedBack);
    }

    private void Recyclview() {
        recyclerMessages=view.findViewById(R.id.recycler_feedback);
        recyclerMessages.setHasFixedSize(true);
//        LastMessage_adapter = new LastMessage_Adapter_supervisor(filterPlaces, getContext());
//        CARS.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerMessages.setLayoutManager(linearLayoutManager);
        recyclerMessages.setItemAnimator(new DefaultItemAnimator());
        recyclerMessages.setAdapter(LastMessage_adapter);

    }

    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_feedback);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if (networikConntection.isNetworkAvailable(getContext())) {
                    if (Language.isRTL()) {
                        mSwipeRefreshLayout.setRefreshing(true);
//                        orders.OrderService(user, "ar");
                    } else {
                        mSwipeRefreshLayout.setRefreshing(true);
//
                    }
                } else {
                    Snackbar.make(Frame_FeedBack,getResources().getString(R.string.internet),1500);
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        if (networikConntection.isNetworkAvailable(getContext())) {
            if (Language.isRTL()) {
                mSwipeRefreshLayout.setRefreshing(true);
//                        orders.OrderService(user, "ar");
            } else {
                mSwipeRefreshLayout.setRefreshing(true);
//
            }
        } else {
            Snackbar.make(Frame_FeedBack,getResources().getString(R.string.internet),1500);
        }
    }
}
