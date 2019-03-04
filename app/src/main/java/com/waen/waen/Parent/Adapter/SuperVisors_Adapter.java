package com.waen.waen.Parent.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.waen.waen.Admin.Adapter.Super_Visors_Adapter_Admin;
import com.waen.waen.Admin.Model.GetBusesInfo;
import com.waen.waen.Parent.Model.Buses_Students;
import com.waen.waen.R;

import java.util.ArrayList;
import java.util.List;

public class SuperVisors_Adapter extends RecyclerView.Adapter<SuperVisors_Adapter.MyViewHolder>{

    public static List<Buses_Students> filteredList=new ArrayList<>();
    View itemView;
    Context con;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView T_SuperVisorName,T_SuperVisorPhone,T_SuperVisorAddress,T_BusName,T_BusNumber,T_DriverName,T_DriverPhone;
        public MyViewHolder(View view) {
            super(view);
            T_SuperVisorPhone=view.findViewById(R.id.T_SuperVisorPhone);
            T_SuperVisorAddress=view.findViewById(R.id.T_StudentName);
            T_SuperVisorName=view.findViewById(R.id.T_SuperVisorName);
            T_BusName=view.findViewById(R.id.T_BusName);
            T_BusNumber=view.findViewById(R.id.T_BusNumber);
            T_DriverName=view.findViewById(R.id.T_DriverName);
            T_DriverPhone=view.findViewById(R.id.T_DriverPhone);

        }
    }

    public SuperVisors_Adapter(List<Buses_Students> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    //    public void setClickListener(Details_Service itemClickListener) {
//        this.details_service = itemClickListener;
//    }
    @Override
    public SuperVisors_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_supervisors_parent, parent, false);
        return new SuperVisors_Adapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final SuperVisors_Adapter.MyViewHolder holder, final int position) {
        holder.T_SuperVisorPhone.setText(filteredList.get(position).getSupervisorsPhone());
        holder.T_SuperVisorName.setText(filteredList.get(position).getSupervisorsName());
        holder.T_SuperVisorAddress.setText(filteredList.get(position).getStudentsName());
        holder.T_BusName.setText(filteredList.get(position).getBusesName());
        holder.T_BusNumber.setText(filteredList.get(position).getNumberBus());
        holder.T_DriverName.setText(filteredList.get(position).getDriversName());
        holder.T_DriverPhone.setText(filteredList.get(position).getDriversPhone());

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
