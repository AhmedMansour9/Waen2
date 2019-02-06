package com.waen.waen.Admin.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.waen.waen.Admin.Model.BusDetail;
import com.waen.waen.Admin.Model.GetBusesInfo;
import com.waen.waen.Admin.View.Details_Bus;
import com.waen.waen.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed on 06/12/2018.
 */

public class Buses_Adapter_admin extends RecyclerView.Adapter<Buses_Adapter_admin.MyViewHolder>{

    public static List<BusDetail> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    Details_Bus details_bus;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView T_BusName,T_Busnumber,T_Start;
        public MyViewHolder(View view) {
            super(view);
            T_BusName=view.findViewById(R.id.T_busName);
            T_Start=view.findViewById(R.id.T_Start);
            T_Busnumber=view.findViewById(R.id.T_Busnumber);

        }
    }

    public Buses_Adapter_admin(List<BusDetail> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    public void setClickListener(Details_Bus itemClickListener) {
        this.details_bus = itemClickListener;
    }
    @Override
    public Buses_Adapter_admin.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_buses_admin, parent, false);
        return new Buses_Adapter_admin.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final Buses_Adapter_admin.MyViewHolder holder, final int position) {
        holder.T_Busnumber.setText(filteredList.get(position).getBusNumber());
        holder.T_BusName.setText(filteredList.get(position).getBusName());
        holder.T_Start.setText(filteredList.get(position).getAction());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(filteredList.get(position).getAction().equals("Start")) {
                details_bus.show(filteredList.get(position).getLat(), filteredList.get(position).getLng());
            }else {
                Toast.makeText(con, "Selected Bus Not Started ..", Toast.LENGTH_SHORT).show();
            }
        }
    });
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
