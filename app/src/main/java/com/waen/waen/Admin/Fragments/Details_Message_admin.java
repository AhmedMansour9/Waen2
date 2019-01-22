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
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.waen.waen.Admin.Adapter.Details_Chat_Adapter_admin;
import com.waen.waen.Admin.NetworikConntection;
import com.waen.waen.SuperVisor.Presenter.Send_Message_Presenter;
import com.waen.waen.Admin.View.SendMessage_View;
import com.waen.waen.Language;
import com.waen.waen.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Details_Message_admin extends Fragment implements SwipeRefreshLayout.OnRefreshListener,SendMessage_View{


    public Details_Message_admin() {
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_details__message_admin, container, false);
        networikConntection=new NetworikConntection(getActivity());
        send_message_presenter=new Send_Message_Presenter(getContext(),this);
        init();
        Recyclview();
        SwipRefresh();
        OnClick();


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
                if(!E_Message.getText().toString().isEmpty()){
                 send_message_presenter.Send_Message("1",E_Message.getText().toString());
                }
            }
        });
    }

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

    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_details_chat);
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

    }

    @Override
    public void Success() {

    }

    @Override
    public void Failed() {

    }
}
