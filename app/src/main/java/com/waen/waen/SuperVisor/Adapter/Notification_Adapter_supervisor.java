package com.waen.waen.SuperVisor.Adapter;

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

public class Notification_Adapter_supervisor extends RecyclerView.Adapter<Notification_Adapter_supervisor.MyViewHolder>{

    //    public static List<Filter_Places> filteredList=new ArrayList<>();
    View itemView;
    Context con;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView T_Notificationtime,T_NotificationTitle,T_Time;
        public MyViewHolder(View view) {
            super(view);
            T_Time=view.findViewById(R.id.T_Time);
            T_NotificationTitle=view.findViewById(R.id.T_NotificationTitle);
            T_Notificationtime=view.findViewById(R.id.T_Notificationtime);

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
    public Notification_Adapter_supervisor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_backupandreturn_supervisor, parent, false);
        return new Notification_Adapter_supervisor.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final Notification_Adapter_supervisor.MyViewHolder holder, final int position) {
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
