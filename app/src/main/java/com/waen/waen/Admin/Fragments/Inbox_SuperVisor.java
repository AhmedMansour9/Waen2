package com.waen.waen.Admin.Fragments;


import android.app.ProgressDialog;
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
import com.waen.waen.Admin.NetworikConntection;
import com.waen.waen.Main.Adapter.Inbox_SuperVisor_Admin;
import com.waen.waen.R;
import com.waen.waen.SharedPrefManager;
import com.waen.waen.SuperVisor.Model.Inbox_details;
import com.waen.waen.SuperVisor.Presenter.Message_Inbox_Presenter;
import com.waen.waen.SuperVisor.View.Messages_Inbox_View;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Inbox_SuperVisor extends Fragment implements Messages_Inbox_View,SwipeRefreshLayout.OnRefreshListener{


    public Inbox_SuperVisor() {
        // Required empty public constructor
    }

    View view;
    RecyclerView recyclerNotification;
    Inbox_SuperVisor_Admin super_visors_adapter_admin;
    SwipeRefreshLayout mSwipeRefreshLayout;
    NetworikConntection networikConntection;
    FrameLayout Frame_FeedBack;
    Message_Inbox_Presenter message_Inbox_Presenter;
    String User_Token;
    ProgressDialog progressdialog;
    String Role;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_inbox__super_visor, container, false);
        networikConntection=new NetworikConntection(getActivity());
        message_Inbox_Presenter=new Message_Inbox_Presenter(getActivity(),this);
        Role= SharedPrefManager.getInstance(getContext()).getRole();
        progressdialog=new ProgressDialog(getContext());
        init();
        Recyclview();
        SwipRefresh();
      return view;
    }

    public void init(){
        Frame_FeedBack=view.findViewById(R.id.Frame_inbox);
        User_Token = SharedPrefManager.getInstance(getContext()).getUserToken();
    }

    private void Recyclview() {
        recyclerNotification=view.findViewById(R.id.recycler_inbox);
        recyclerNotification.setHasFixedSize(true);

    }

    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_inbox);
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
                        message_Inbox_Presenter.Inbox_SuperVisor_Admin(User_Token,"supervisor");


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
                message_Inbox_Presenter.Inbox_SuperVisor_Admin(User_Token,"supervisor");

        } else {
            Snackbar.make(Frame_FeedBack,getResources().getString(R.string.internet),1500);
        }
    }


    @Override
    public void GetMessagesParent(List<Inbox_details> inbox_details) {

    }

    @Override
    public void SendMessages_SuperVisor(List<Inbox_details> inbox_details) {

    }

    @Override
    public void Inbox_Parent_Admin(List<Inbox_details> inbox_details) {

    }

    @Override
    public void Inbox_SuperVisor_Admin(List<Inbox_details> inbox_details) {
        Collections.reverse(inbox_details);
        super_visors_adapter_admin = new Inbox_SuperVisor_Admin(inbox_details, getContext());
//        CARS.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerNotification.setLayoutManager(linearLayoutManager);
        recyclerNotification.setItemAnimator(new DefaultItemAnimator());
        recyclerNotification.setAdapter(super_visors_adapter_admin);
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void Sent_Parent_Admin(List<Inbox_details> inbox_details) {

    }

    @Override
    public void Sent_SuperVisor_Admin(List<Inbox_details> inbox_details) {

    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}

