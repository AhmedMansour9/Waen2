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

import com.waen.waen.Admin.Adapter.Super_Visors_Adapter_Admin;
import com.waen.waen.Admin.Model.GetBusesInfo;
import com.waen.waen.Admin.NetworikConntection;
import com.waen.waen.Admin.Presenter.GetBuses_Presenter;
import com.waen.waen.Admin.View.GetBusesMap_View;
import com.waen.waen.R;
import com.waen.waen.SharedPrefManager;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuperVisors_Admin extends Fragment implements GetBusesMap_View,SwipeRefreshLayout.OnRefreshListener{


    public SuperVisors_Admin() {
        // Required empty public constructor
    }
    RecyclerView recycler_SuperVisors;
    Super_Visors_Adapter_Admin SuperVisor_adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    NetworikConntection networikConntection;
    FrameLayout Frame_FeedBack;
    GetBuses_Presenter getBuses_presenter;
    View view;
    String User_Token;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_super_visors_admin, container, false);
        networikConntection=new NetworikConntection(getActivity());
        getBuses_presenter=new GetBuses_Presenter(getActivity(),this);
        User_Token= SharedPrefManager.getInstance(getContext()).getUserToken();
        init();
        Recyclview();
        SwipRefresh();
        return view;
    }
    public void init(){
        Frame_FeedBack=view.findViewById(R.id.Frame_FeedBack);
    }

    private void Recyclview() {
        recycler_SuperVisors=view.findViewById(R.id.recycler_super_visors);
        recycler_SuperVisors.setHasFixedSize(true);
    }

    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_super_visors);
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
                        getBuses_presenter.GetBuses(User_Token);
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
            getBuses_presenter.GetBuses(User_Token);

        } else {
            Snackbar.make(Frame_FeedBack,getResources().getString(R.string.internet),1500);
        }
    }

    @Override
    public void ListBuses(List<GetBusesInfo> list) {
       SuperVisor_adapter = new Super_Visors_Adapter_Admin(list, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_SuperVisors.setLayoutManager(linearLayoutManager);
        recycler_SuperVisors.setItemAnimator(new DefaultItemAnimator());
        recycler_SuperVisors.setAdapter(SuperVisor_adapter);

    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
