package com.waen.waen.Admin.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.waen.waen.R;

/**
 * Created by Ahmed on 09/12/2018.
 */

public class Super_Visors_Adapter_Admin extends RecyclerView.Adapter<Super_Visors_Adapter_Admin.MyViewHolder>{

    //    public static List<Filter_Places> filteredList=new ArrayList<>();
    View itemView;
    Context con;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView T_Address,T_Started,T_Speed;
        public MyViewHolder(View view) {
            super(view);
            T_Started=view.findViewById(R.id.T_Started);
            T_Speed=view.findViewById(R.id.T_Speed);
            T_Address=view.findViewById(R.id.T_Address);

        }
    }

    //    public Buses_Adapter_supervisor(List<Filter_Places> list, Context context){
//        this.filteredList=list;
//        this.con=context;
//    }
//    public void setClickListener(Details_Service itemClickListener) {
//        this.details_service = itemClickListener;
//    }
    @Override
    public Super_Visors_Adapter_Admin.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_buses_admin, parent, false);
        return new Super_Visors_Adapter_Admin.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final Super_Visors_Adapter_Admin.MyViewHolder holder, final int position) {
//        holder.VendorName.setText(filteredList.get(position).getVendorName());
//        double a=filteredList.get(position).getDistance()/1000;





    }

    @Override
    public int getItemCount() {
        return 0;
//        return filteredList.size()  ;
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
