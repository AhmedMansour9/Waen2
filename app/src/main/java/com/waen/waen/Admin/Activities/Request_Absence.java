package com.waen.waen.Admin.Activities;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.waen.waen.Parent.Model.Buses_Students;
import com.waen.waen.Parent.Presenter.Buses_Students_Presnter;
import com.waen.waen.Parent.View.Buses_Students_View;
import com.waen.waen.R;
import com.waen.waen.SharedPrefManager;
import com.waen.waen.SuperVisor.Presenter.RequestApsence_Presnter;
import com.waen.waen.SuperVisor.View.RequestAbsence_View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.security.AccessController.getContext;

public class Request_Absence extends AppCompatActivity implements RequestAbsence_View,Buses_Students_View {
    Spinner Spin_Students;
    Buses_Students_Presnter buses_students_presnter;
     ArrayAdapter<Buses_Students>list_student;
     String StudentName,Student_Id,date,User_token,usetokenSupervisor;
    DatePickerDialog.OnDateSetListener dateSetListener,dateSetListe;
    TextView Date_From,Date_To;
    Button SendMessage;
    EditText E_Title,E_Message;
    RequestApsence_Presnter requestApsence_presnter;
    ProgressBar progross;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_request__absence);
        Spin_Students=findViewById(R.id.Spin_Students);
        SendMessage=findViewById(R.id.SendMessage);
        progross=findViewById(R.id.progross);
        requestApsence_presnter=new RequestApsence_Presnter(this,this) ;
        init();

    }

    public void init(){
        Date_From=findViewById(R.id.Date_From);
        Date_To=findViewById(R.id.Date_To);
        E_Title=findViewById(R.id.E_Title);
        E_Message=findViewById(R.id.E_Message);
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());
        User_token = SharedPrefManager.getInstance(this).getUserTokenParent();

        buses_students_presnter=new Buses_Students_Presnter(this,this);
        buses_students_presnter.GetBuses(User_token);
        Date_From.setText(date);
        Date_To.setText(date);
        Date_From();
        Date_to();
        Send_Message();
    }
    public void Send_Message(){
        SendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!E_Title.getText().toString().equals("") || !E_Message.getText().toString().equals("")) {
                    progross.setVisibility(View.VISIBLE);
                    requestApsence_presnter.Request_Absence(Student_Id, E_Title.getText().toString(), E_Message.getText().toString(),
                            Date_From.getText().toString(), Date_To.getText().toString(), usetokenSupervisor);

                }
            }
        });
    }
    public void Date_From(){
        Date_From.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(Request_Absence.this ,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        dateSetListener,
                        year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Date_From.setText(""+i+"-"+i1+"-"+i2);
            }
        };
    }

    public void Date_to(){

        Date_To.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(Request_Absence.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        dateSetListe,
                        year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        dateSetListe=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Date_To.setText(""+i+"-"+i1+"-"+i2);
            }
        };
    }
    @Override
    public void list(final List<Buses_Students> list) {
        list_student = new ArrayAdapter<Buses_Students>(getApplicationContext(), R.layout.textcolorspinner,list) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };
        list_student.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spin_Students.setAdapter(list_student);
        Spin_Students.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                StudentName= Spin_Students.getSelectedItem().toString();

                for(i = 0; i<list.size(); i++){
                    if(list.get(i).getStudentsName().equals(StudentName)){
                        Student_Id=String.valueOf(list.get(i).getStudentsId());
                        usetokenSupervisor=String.valueOf(list.get(i).getSupervisorsUserToken());
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void success() {
        Toast.makeText(this, getResources().getString(R.string.requestabsence), Toast.LENGTH_SHORT).show();
        progross.setVisibility(View.GONE);
        finish();
    }

    @Override
    public void Error() {

        progross.setVisibility(View.GONE);
    }
}
