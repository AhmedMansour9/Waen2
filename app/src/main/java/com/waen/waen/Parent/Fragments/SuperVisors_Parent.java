package com.waen.waen.Parent.Fragments;


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

import com.waen.waen.Parent.Adapter.Buses_Students_Adapter;
import com.waen.waen.Parent.Adapter.SuperVisors_Adapter;
import com.waen.waen.Parent.Model.Buses_Students;
import com.waen.waen.Parent.Presenter.Buses_Students_Presnter;
import com.waen.waen.Parent.View.Buses_Students_View;
import com.waen.waen.R;
import com.waen.waen.SharedPrefManager;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuperVisors_Parent extends Fragment implements Buses_Students_View ,SwipeRefreshLayout.OnRefreshListener
{


    public SuperVisors_Parent() {
        // Required empty public constructor
    }

    Buses_Students_Presnter buses_students_presnter;
    View view;
    RecyclerView recyclerBus;
    SuperVisors_Adapter buses_adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    String User_token;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_super_visors__parent, container, false);
        buses_students_presnter=new Buses_Students_Presnter(getActivity(),this);
        User_token = SharedPrefManager.getInstance(getContext()).getUserTokenParent();

        Recyclview();
        SwipRefresh();
        return view;
    }

    @Override
    public void list(List<Buses_Students> list) {

        buses_adapter = new SuperVisors_Adapter(list, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerBus.setLayoutManager(linearLayoutManager);
        recyclerBus.setItemAnimator(new DefaultItemAnimator());
        recyclerBus.setAdapter(buses_adapter);
        mSwipeRefreshLayout.setRefreshing(false);
    }
    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_supervisor);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {

                    mSwipeRefreshLayout.setRefreshing(true);
                buses_students_presnter.GetBuses(User_token);

            }
        });
    }
    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
    }


    private void Recyclview() {
        recyclerBus = view.findViewById(R.id.recycler_supervisors);
        recyclerBus.setHasFixedSize(true);

    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        buses_students_presnter.GetBuses(User_token);

    }
}