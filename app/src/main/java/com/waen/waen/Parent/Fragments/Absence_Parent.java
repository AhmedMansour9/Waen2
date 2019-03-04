package com.waen.waen.Parent.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.waen.waen.Admin.Activities.Request_Absence;
import com.waen.waen.Admin.Adapter.Absence_Adapter;
import com.waen.waen.Admin.Model.Absences_Details;
import com.waen.waen.Admin.Model.Notifications;
import com.waen.waen.Admin.NetworikConntection;
import com.waen.waen.Admin.Presenter.Absence_Presnter;
import com.waen.waen.Admin.Presenter.GetNotifications_Presenter;
import com.waen.waen.Admin.View.GetAbsences_View;
import com.waen.waen.Admin.View.GetNotifications_View;
import com.waen.waen.Main.SendMessage;
import com.waen.waen.R;
import com.waen.waen.SharedPrefManager;
import com.waen.waen.SuperVisor.Adapter.Notification_Adapter_supervisor;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Absence_Parent extends Fragment implements GetAbsences_View,SwipeRefreshLayout.OnRefreshListener{


    public Absence_Parent() {
        // Required empty public constructor
    }
    RecyclerView recyclerNotification;
    Absence_Adapter Notification_adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    NetworikConntection networikConntection;
    RelativeLayout Frame_FeedBack;
    Absence_Presnter getNotifications_presenter;
    String Role,UserToken,UserTokenParent;
    View view;
    FloatingActionButton floatingActionButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_absence__admin, container, false);
        networikConntection=new NetworikConntection(getActivity());
        getNotifications_presenter=new Absence_Presnter(getContext(),this);
        Role= SharedPrefManager.getInstance(getContext()).getRole();
        UserToken=SharedPrefManager.getInstance(getContext()).getUserToken();
        if(Role.equals("parent")){
            UserToken=SharedPrefManager.getInstance(getContext()).getUserTokenParent();
        }
        floatingActionButton = view.findViewById(R.id.fabss);
        init();
        Recyclview();
        SwipRefresh();

        return view;
    }

    public void init(){
        Frame_FeedBack=view.findViewById(R.id.Frame_notification);
        if(Role.equals("admin")){
            floatingActionButton.setVisibility(View.GONE);
        }
        if(Role.equals("supervisor")){
            floatingActionButton.setVisibility(View.GONE);
        }
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Request_Absence.class);
                startActivity(intent);
            }
        });

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
                    getNotifications_presenter.GetAbsences(UserToken,Role);
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
            getNotifications_presenter.GetAbsences(UserToken,Role);
        } else {
            Snackbar.make(Frame_FeedBack,getResources().getString(R.string.internet),1500);
        }
    }

    @Override
    public void ListAbseces(List<Absences_Details> list) {
        Notification_adapter = new Absence_Adapter(list, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerNotification.setLayoutManager(linearLayoutManager);
        recyclerNotification.setItemAnimator(new DefaultItemAnimator());
        recyclerNotification.setAdapter(Notification_adapter);
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
