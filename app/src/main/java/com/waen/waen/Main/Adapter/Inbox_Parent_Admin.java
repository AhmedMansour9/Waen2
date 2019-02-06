package com.waen.waen.Main.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.waen.waen.R;
import com.waen.waen.SuperVisor.Model.Inbox_details;

import java.util.ArrayList;
import java.util.List;

public class Inbox_Parent_Admin extends RecyclerView.Adapter<Inbox_Parent_Admin.MyViewHolder>{

    public static List<Inbox_details> filteredList=new ArrayList<>();
    View itemView;
    Context con;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView T_Title,T_LastMessage,T_Time;
        public MyViewHolder(View view) {
            super(view);
            T_Time=view.findViewById(R.id.T_Time);
            T_LastMessage=view.findViewById(R.id.T_LastMessage);
            T_Title=view.findViewById(R.id.T_Title);

        }
    }

    public Inbox_Parent_Admin(List<Inbox_details> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    //    public void setClickListener(Details_Service itemClickListener) {
//        this.details_service = itemClickListener;
//    }
    @Override
    public Inbox_Parent_Admin.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_chat_admin, parent, false);
        return new Inbox_Parent_Admin.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final Inbox_Parent_Admin.MyViewHolder holder, final int position) {
        holder.T_Time.setText(filteredList.get(position).getDate());
        holder.T_LastMessage.setText(filteredList.get(position).getBody());
        holder.T_Title.setText(filteredList.get(position).getTitle());





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
