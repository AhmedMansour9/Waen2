package com.waen.waen.SuperVisor.Fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.waen.waen.Admin.Adapter.Details_Chat_Adapter_admin;
import com.waen.waen.Admin.NetworikConntection;
import com.waen.waen.SharedPrefManager;
import com.waen.waen.SuperVisor.Model.message;
import com.waen.waen.SuperVisor.Presenter.Send_Message_Presenter;
import com.waen.waen.Admin.View.SendMessage_View;
import com.waen.waen.Language;
import com.waen.waen.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class Details_Message_Supervisor extends Fragment implements SwipeRefreshLayout.OnRefreshListener,SendMessage_View{


    public Details_Message_Supervisor() {
        // Required empty public constructor
    }
    View view;
    RecyclerView recyclerMessages;
    Details_Chat_Adapter_admin DetailsChat_adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    NetworikConntection networikConntection;
    FrameLayout Frame_FeedBack;
    Button Btn_SendMessage;
    Send_Message_Presenter send_message_presenter;
    EditText E_Message;
    DatabaseReference reference;
    String Msg;
    public static String Myuserid;
    String userid;
    RecyclerView recyclerView;

    public   static int totalnumber=10;
    private int itempostion=0;
    private int padge=1;
    private String key="";
    private String prekey="";
    LinearLayoutManager linearLayoutManager;
    String User_Admin,User;
    DatabaseReference datamsg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_details__message_supervisor, container, false);
        networikConntection=new NetworikConntection(getActivity());
        send_message_presenter=new Send_Message_Presenter(getContext(),this);
       User_Admin=SharedPrefManager.getInstance(getActivity()).getUserTokenAdmin();
        User=SharedPrefManager.getInstance(getActivity()).getUserToken();
        datamsg = FirebaseDatabase.getInstance().getReference("Chat");

        init();
        SwipRefresh();
//        Recyclview();
//        SwipRefresh();
        OnClick();
        SendMeesges();
//        loadesmassg();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                padge++;

                itempostion=0;
//                loademoresmassg();
            }
        });

        return view;
    }

    public void init(){
        Frame_FeedBack=view.findViewById(R.id.Frame_Chat);
        Btn_SendMessage=view.findViewById(R.id.Btn_SendMessage);
        E_Message=view.findViewById(R.id.E_Message);
    }
    public void OnClick(){
        Btn_SendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String delegate = "hh:mm aaa";
                String date= (String) DateFormat.format(delegate, Calendar.getInstance().getTime());

                if(!E_Message.getText().toString().isEmpty()){
                    HashMap<String,Object> hashMap=new HashMap<>();
                    hashMap.put("time",date);
                    hashMap.put("Msg",E_Message.getText().toString());
                    hashMap.put("user",User);
                  reference.child(User_Admin).push().setValue(hashMap);

                }
            }
        });
    }
//    public void loadesmassg(){
//        DatabaseReference datams=datamsg.child(Myuserid).child(userid);
//
//        Query mqery=datams.limitToLast(padge+totalnumber);
//
//        mqery.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                meesage a=dataSnapshot.getValue(meesage.class);
//
//                itempostion++;
//                if(itempostion==1){
//                    String KEY=dataSnapshot.getKey();
//                    key=KEY;
//                    prekey=KEY;
//                }
//                moviesList.add(a);
//                mAdapter.notifyDataSetChanged();
//                recyclerView.scrollToPosition(moviesList.size()-1);
//                mSwipeRefreshLayout.setRefreshing(false);
//
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//
//    }
    private void Recyclview() {
        recyclerMessages=view.findViewById(R.id.recycler_details_chat);
        recyclerMessages.setHasFixedSize(true);
//        DetailsChat_adapter = new Details_Chat_Adapter_supervisor(filterPlaces, getContext());
//        CARS.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerMessages.setLayoutManager(linearLayoutManager);
        recyclerMessages.setItemAnimator(new DefaultItemAnimator());
        recyclerMessages.setAdapter(DetailsChat_adapter);

    }

    public void SwipRefresh() {
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_details_chat);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void Success() {

    }

    @Override
    public void Failed() {

    }
    public void SendMeesges(){
        Btn_SendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (chek.isNetworkAvailable(getActivity())) {
                    Msg = E_Message.getText().toString().trim();

                if (Msg != null && Msg.equals("")) {
                    } else {
                        String delegate = "hh:mm aaa";
                        String time= (String) DateFormat.format(delegate, Calendar.getInstance().getTime());
                       String date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());

                        HashMap<String, String> map = new HashMap<>();
                        map.put("Msg", Msg);
                        map.put("time", time);
                        map.put("user", User);
                        map.put("date", date);
                        datamsg.child(User_Admin).child(User).push().setValue(map);
                        datamsg.child(User).child(User_Admin).push().setValue(map);
//                        Messages.setText("");
                    }
                }
//                else {
//                    chek.ShowNetworkdialoge();
//                }

        });


    }
}
