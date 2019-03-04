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

import com.waen.waen.Admin.Adapter.Notification_Adapter_admin;
import com.waen.waen.Admin.Model.Notifications;
import com.waen.waen.Admin.NetworikConntection;
import com.waen.waen.Admin.Presenter.GetNotifications_Presenter;
import com.waen.waen.Admin.View.GetNotifications_View;
import com.waen.waen.Language;
import com.waen.waen.R;
import com.waen.waen.SharedPrefManager;
import com.waen.waen.SuperVisor.Adapter.Notification_Adapter_supervisor;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Notification_admin extends Fragment implements GetNotifications_View,SwipeRefreshLayout.OnRefreshListener{


    public Notification_admin() {
        // Required empty public constructor
    }
    RecyclerView recyclerNotification;
    Notification_Adapter_supervisor Notification_adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    NetworikConntection networikConntection;
    FrameLayout Frame_FeedBack;
    GetNotifications_Presenter getNotifications_presenter;
    String Role,UserToken;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_notification_admin, container, false);
        networikConntection=new NetworikConntection(getActivity());
        getNotifications_presenter=new GetNotifications_Presenter(getContext(),this);
        Role= SharedPrefManager.getInstance(getContext()).getRole();
        UserToken=SharedPrefManager.getInstance(getContext()).getUserToken();
        if(Role.equals("parent")){
            UserToken=SharedPrefManager.getInstance(getContext()).getUserTokenParent();
        }
        init();
        Recyclview();
        SwipRefresh();


        return view;
    }
    public void init(){
        Frame_FeedBack=view.findViewById(R.id.Frame_notification);
    }

    private void Recyclview() {
        recyclerNotification=view.findViewById(R.id.recycler_notification);
        recyclerNotification.setHasFixedSize(true);

    }

    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_notification);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if (networikConntection.isNetworkAvailable(getContext())) {

                        mSwipeRefreshLayout.setRefreshing(true);
                        getNotifications_presenter.GetNotifications(UserToken,Role);
                } else {
                    Snackbar.make(Frame_FeedBack,getResources().getString(R.string.internet),1500);
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        if (networikConntection.isNetworkAvailable(getContext())) {

                mSwipeRefreshLayout.setRefreshing(true);
                getNotifications_presenter.GetNotifications(UserToken,Role);
        } else {
            Snackbar.make(Frame_FeedBack,getResources().getString(R.string.internet),1500);
        }
    }

    @Override
    public void getnotifcations(List<Notifications> list) {
        Notification_adapter = new Notification_Adapter_supervisor(list, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerNotification.setLayoutManager(linearLayoutManager);
        recyclerNotification.setItemAnimator(new DefaultItemAnimator());
        recyclerNotification.setAdapter(Notification_adapter);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void Error() {

    }
}
