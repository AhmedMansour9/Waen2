package com.waen.waen.SuperVisor.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.waen.waen.R;
import com.waen.waen.SuperVisor.Model.StudentDetails;
import com.waen.waen.SuperVisor.Model.StudentsInfo;
import com.waen.waen.SuperVisor.View.Student_Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed on 09/12/2018.
 */

public class Backup_Supervisor_Adapter extends RecyclerView.Adapter<Backup_Supervisor_Adapter.MyViewHolder>{

     public  List<StudentDetails> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    public static String Trip;
    Student_Id student_id;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private Button Btn_inhome,Btn_inschool;
        TextView T_Name,T_Address;

        public MyViewHolder(View view) {
            super(view);
            T_Name=view.findViewById(R.id.T_Name);
            T_Address=view.findViewById(R.id.T_Address);
            Btn_inhome=view.findViewById(R.id.Btn_inhome);
            Btn_inschool=view.findViewById(R.id.Btn_inschool);

        }
    }

        public Backup_Supervisor_Adapter(List<StudentDetails> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    public void setClickListener(Student_Id itemClickListener) {
        this.student_id = itemClickListener;
    }
    @Override
    public Backup_Supervisor_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_backupandreturn_supervisor, parent, false);
        return new Backup_Supervisor_Adapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final Backup_Supervisor_Adapter.MyViewHolder holder, final int position) {
        holder.T_Name.setText(filteredList.get(position).getStudentName());
        holder.T_Address.setText(filteredList.get(position).getStudentAddress());

       if(Trip.equals("backup")){
           holder.Btn_inschool.setVisibility(View.VISIBLE);
           holder.Btn_inhome.setVisibility(View.GONE);
       }else if(Trip.equals("return")){
           holder.Btn_inhome.setVisibility(View.VISIBLE);
           holder.Btn_inschool.setVisibility(View.GONE);
       }

       holder.Btn_inhome.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               student_id.Student_Id(filteredList.get(position).getStudentId());

           }
       });
       holder.Btn_inschool.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               student_id.Student_Id(filteredList.get(position).getStudentId());
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

