package com.waen.waen.SuperVisor.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.waen.waen.Admin.NetworikConntection;
import com.waen.waen.Language;
import com.waen.waen.R;
import com.waen.waen.SharedPrefManager;
import com.waen.waen.SuperVisor.Adapter.Backup_Supervisor_Adapter;
import com.waen.waen.SuperVisor.Model.StudentDetails;
import com.waen.waen.SuperVisor.Presenter.StudenInFo_BackupAndReturn_Presnter;
import com.waen.waen.SuperVisor.View.StudenInfo_View;
import com.waen.waen.SuperVisor.View.Student_Id;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Backup_Supervisor extends Fragment implements Student_Id,StudenInfo_View,SwipeRefreshLayout.OnRefreshListener{


    public Backup_Supervisor() {
        // Required empty public constructor
    }
    RecyclerView recyclerNotification;
    Backup_Supervisor_Adapter Backup_Supervisor_adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    NetworikConntection networikConntection;
    FrameLayout Frame_FeedBack;
    StudenInFo_BackupAndReturn_Presnter StudenInFo_Presnter;
   View view;
   String User_Token;
    ProgressDialog progressdialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_backup_supervisor, container, false);
        networikConntection=new NetworikConntection(getActivity());
        progressdialog = new ProgressDialog(getActivity());
        StudenInFo_Presnter=new StudenInFo_BackupAndReturn_Presnter(getActivity(),this);
        init();
        Recyclview();
        SwipRefresh();


        return view;
    }
    public void init(){
        Frame_FeedBack=view.findViewById(R.id.Frame_notification);
        User_Token = SharedPrefManager.getInstance(getContext()).getUserToken();
    }
    private void Recyclview() {
        recyclerNotification=view.findViewById(R.id.recycler_backup);
        recyclerNotification.setHasFixedSize(true);
    }
    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_backup);
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
                        StudenInFo_Presnter.GetStudentinfo(User_Token, "backup_student","2");
                    } else {
                        mSwipeRefreshLayout.setRefreshing(true);
                        StudenInFo_Presnter.GetStudentinfo(User_Token, "backup_student","2");
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
                StudenInFo_Presnter.GetStudentinfo(User_Token, "backup_student","2");
            } else {
                mSwipeRefreshLayout.setRefreshing(true);
                StudenInFo_Presnter.GetStudentinfo(User_Token, "backup_student","2");
            }
        } else {
            Snackbar.make(Frame_FeedBack,getResources().getString(R.string.internet),1500);
        }
    }

    @Override
    public void GetStudents(List<StudentDetails> list) {
        if(progressdialog.isShowing()){
            progressdialog.dismiss();
        }
        Backup_Supervisor_Adapter.Trip="backup";
        Backup_Supervisor_adapter = new Backup_Supervisor_Adapter(list, getContext());
        Backup_Supervisor_adapter.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerNotification.setLayoutManager(linearLayoutManager);
        recyclerNotification.setItemAnimator(new DefaultItemAnimator());
        recyclerNotification.setAdapter(Backup_Supervisor_adapter);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
        if(progressdialog.isShowing()){
            progressdialog.dismiss();
        }
    }

    @Override
    public void Student_Id(String id) {
       String point= SharedPrefManager.getInstance(getActivity()).getStartTrip();
       if(point!=null) {
           progressdialog.setMessage("Please Wait While Sending Notification....");
           progressdialog.show();
           progressdialog.setCancelable(false);

           StudenInFo_Presnter.GetStudentinfo(User_Token, "backup", id);
       }else {
         ShowDialog();


       }

    }
    public void ShowDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                //set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
                //set title
                .setTitle("Warning")
                //set message
                .setMessage("Please Start a Trip First ..").show();

    }
}
