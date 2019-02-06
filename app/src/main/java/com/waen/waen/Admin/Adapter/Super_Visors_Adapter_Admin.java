package com.waen.waen.Admin.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.waen.waen.Admin.Model.GetBusesInfo;
import com.waen.waen.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed on 09/12/2018.
 */

public class Super_Visors_Adapter_Admin extends RecyclerView.Adapter<Super_Visors_Adapter_Admin.MyViewHolder>{

        public static List<GetBusesInfo> filteredList=new ArrayList<>();
    View itemView;
    Context con;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView T_SuperVisorName,T_SuperVisorPhone,T_SuperVisorAddress,T_BusName,T_BusNumber;
        public MyViewHolder(View view) {
            super(view);
            T_SuperVisorPhone=view.findViewById(R.id.T_SuperVisorPhone);
            T_SuperVisorAddress=view.findViewById(R.id.T_SuperVisorAddress);
            T_SuperVisorName=view.findViewById(R.id.T_SuperVisorName);
            T_BusName=view.findViewById(R.id.T_BusName);
            T_BusName=view.findViewById(R.id.T_BusNumber);

        }
    }

        public Super_Visors_Adapter_Admin(List<GetBusesInfo> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
//    public void setClickListener(Details_Service itemClickListener) {
//        this.details_service = itemClickListener;
//    }
    @Override
    public Super_Visors_Adapter_Admin.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_bus_details, parent, false);
        return new Super_Visors_Adapter_Admin.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final Super_Visors_Adapter_Admin.MyViewHolder holder, final int position) {
        holder.T_SuperVisorPhone.setText(filteredList.get(position).getSupervisorsPhone());
        holder.T_SuperVisorName.setText(filteredList.get(position).getSupervisorsName());
        holder.T_SuperVisorAddress.setText(filteredList.get(position).getSupervisorsAddress());
        holder.T_BusName.setText(filteredList.get(position).getBusesName());
        holder.T_BusName.setText(filteredList.get(position).getBusesNumberBus());




    }

    @Override
    public int getItemCount() {

        return filteredList.size()  ;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
