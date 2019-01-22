package com.waen.waen.SuperVisor.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.waen.waen.R;
import com.waen.waen.SharedPrefManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class Choose_BackUp_Return extends Fragment {


    public Choose_BackUp_Return() {
        // Required empty public constructor
    }
    Button backup,returnn;
    View view;
    TextView T_BusName,T_BusNumber,T_Capacity,T_BusNumberStuident,T_DriverName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_choose__back_up__return, container, false);

         init();
         StartBackUp();
         StartReturn();

        return view;
    }
    public void CheckStartOrNot(){
        String  statue=SharedPrefManager.getInstance(getActivity()).getStartTrip();
        if(statue!=null){
            getFragmentManager().beginTransaction().replace(R.id.Frame_BachUpAndReturn,
                    new Maps_Bus_Supervisor()).commitAllowingStateLoss();
        }else {

        }
    }
    public void init(){
        backup=view.findViewById(R.id.backup);
        returnn=view.findViewById(R.id.returnn);
        T_BusName=view.findViewById(R.id.T_BusName);
        T_BusNumber=view.findViewById(R.id.T_BusNumber);
        T_Capacity=view.findViewById(R.id.T_Capacity);
        T_BusNumberStuident=view.findViewById(R.id.T_BusNumberStuident);
        T_DriverName=view.findViewById(R.id.T_DriverName);
        String Bus_Name=SharedPrefManager.getInstance(getActivity()).getBuseName();
       String BusNumber =SharedPrefManager.getInstance(getActivity()).getBusNumber();
       String Capacity =SharedPrefManager.getInstance(getActivity()).getBuscapcity();
       String BusNumberStuident= SharedPrefManager.getInstance(getActivity()).getBusNumberSteudent();
       String DriverName=SharedPrefManager.getInstance(getActivity()).getDriverName();
        T_BusName.setText(Bus_Name);
        T_BusNumber.setText(BusNumber);
        T_Capacity.setText(Capacity);
        T_BusNumberStuident.setText(BusNumberStuident);
        T_DriverName.setText(DriverName);
    }
    public void StartBackUp(){
        backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefManager.getInstance(getContext()).saveBackup("backup");
                Maps_Bus_Supervisor fragmen = new Maps_Bus_Supervisor();
                Bundle args = new Bundle();
                fragmen.setArguments(args);
                getFragmentManager().beginTransaction()
                        .replace(R.id.Frame_BachUpAndReturn, fragmen )
                        .addToBackStack(null)
                        .commit();

            }
        });
    }
    public void StartReturn(){
        returnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefManager.getInstance(getContext()).saveReturn("return");
                Maps_Bus_Supervisor fragmen = new Maps_Bus_Supervisor();
                Bundle args = new Bundle();
                fragmen.setArguments(args);
                getFragmentManager().beginTransaction()
                        .replace(R.id.Frame_BachUpAndReturn, fragmen )
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Do your Work
            CheckStartOrNot();
        } else {
            // Do your Work
        }

    }
}
