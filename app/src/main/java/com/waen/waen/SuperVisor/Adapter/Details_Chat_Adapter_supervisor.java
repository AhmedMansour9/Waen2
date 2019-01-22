package com.waen.waen.SuperVisor.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.waen.waen.R;

/**
 * Created by Ahmed on 06/12/2018.
 */

public class Details_Chat_Adapter_supervisor extends RecyclerView.Adapter<Details_Chat_Adapter_supervisor.MyViewHolder>{

    //    public static List<Filter_Places> filteredList=new ArrayList<>();
    View itemView;
    Context con;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView User_one,User_Two;
        public MyViewHolder(View view) {
            super(view);
            User_Two=view.findViewById(R.id.User_Two);
            User_one=view.findViewById(R.id.User_one);

        }
    }
//     public Details_Chat_Adapter_supervisor(List<message> list, Context context){
//        this.filteredList=list;
//        this.con=context;
//    }

    @Override
    public Details_Chat_Adapter_supervisor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_details_message_admin, parent, false);
        return new Details_Chat_Adapter_supervisor.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final Details_Chat_Adapter_supervisor.MyViewHolder holder, final int position) {
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
