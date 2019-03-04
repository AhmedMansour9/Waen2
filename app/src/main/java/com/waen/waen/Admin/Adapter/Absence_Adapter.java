package com.waen.waen.Admin.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.waen.waen.Admin.Model.Absences_Details;
import com.waen.waen.Admin.Model.Notifications;
import com.waen.waen.R;
import com.waen.waen.SuperVisor.Adapter.Notification_Adapter_supervisor;

import java.util.ArrayList;
import java.util.List;

public class Absence_Adapter extends RecyclerView.Adapter<Absence_Adapter.MyViewHolder>{

    public static List<Absences_Details> filteredList=new ArrayList<>();
    View itemView;
    Context con;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView T_Notificationtime,T_NotificationTitle,T_NotificationMessage,T_NotificationStudent,Date_From,Date_To;
        public MyViewHolder(View view) {
            super(view);
            T_NotificationMessage=view.findViewById(R.id.T_NotificationMessage);
            T_NotificationTitle=view.findViewById(R.id.T_NotificationTitle);
            T_Notificationtime=view.findViewById(R.id.T_Notificationtime);
            T_NotificationStudent=view.findViewById(R.id.T_NotificationStudent);
            Date_From=view.findViewById(R.id.Date_From);
            Date_To=view.findViewById(R.id.Date_To);

        }
    }

    public Absence_Adapter(List<Absences_Details> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    //    public void setClickListener(Details_Service itemClickListener) {
//        this.details_service = itemClickListener;
//    }
    @Override
    public Absence_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_absence, parent, false);
        return new Absence_Adapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final Absence_Adapter.MyViewHolder holder, final int position) {
        holder.T_Notificationtime.setText(filteredList.get(position).getData());
        holder.T_NotificationMessage.setText(filteredList.get(position).getMessage());
        holder.T_NotificationTitle.setText(filteredList.get(position).getTitle());
        holder.T_NotificationStudent.setText(filteredList.get(position).getStudentName());
        holder.Date_From.setText(filteredList.get(position).getFrom());
        holder.Date_To.setText(filteredList.get(position).getTo());




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
