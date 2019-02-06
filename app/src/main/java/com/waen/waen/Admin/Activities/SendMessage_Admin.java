package com.waen.waen.Admin.Activities;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.waen.waen.Admin.Model.AllSuperVisors;
import com.waen.waen.Admin.Presenter.GetAllSuperVisors_Presenter;
import com.waen.waen.Admin.View.GetAllSuperVisors_View;
import com.waen.waen.Admin.View.SendMessage_View;
import com.waen.waen.Main.Presenter.Send_Message_Presenter;
import com.waen.waen.R;
import com.waen.waen.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

public class SendMessage_Admin extends AppCompatActivity implements SendMessage_View, GetAllSuperVisors_View {
    EditText E_Message,E_Title;
    Button Btn_SendMessage;
    Send_Message_Presenter send_message_presenter;
    String UserToken;
    ProgressDialog progressdialog;
    String role,id,type;
    Spinner allsupervisors;
    ArrayAdapter<String> ListServices;
    Spinner Selecttype;
    GetAllSuperVisors_Presenter getSuperrvisors;
    ArrayAdapter<AllSuperVisors> List;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_send_message__admin);
        getSuperrvisors=new GetAllSuperVisors_Presenter(this,this);
        send_message_presenter=new Send_Message_Presenter(this,this);
        UserToken= SharedPrefManager.getInstance(this).getUserToken();
        allsupervisors=findViewById(R.id.allsupervisors);
        progressdialog=new ProgressDialog(this);
        E_Title=findViewById(R.id.E_Title);
        E_Message=findViewById(R.id.E_Message);
        Btn_SendMessage=findViewById(R.id.Btn_SendMessage);
        Selecttype=findViewById(R.id.Selecttype);

        Spin_Service();
        Btn_SendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!E_Message.getText().toString().equals("")&&!E_Title.getText().toString().equals("")
                &&!id.equals("")){
                    progressdialog.setMessage("Please Wait While Sending Message....");
                    progressdialog.show();
                    progressdialog.setCancelable(false);
                        send_message_presenter.Send_MessageAdmin(UserToken, E_Title.getText().toString(), E_Message.getText().toString()

                        ,id,type);
                }

            }
        });
    }

    @Override
    public void Success() {
        Toast.makeText(this, "Message Sent Success", Toast.LENGTH_SHORT).show();
        progressdialog.dismiss();
        finish();
    }

    @Override
    public void Failed() {

        progressdialog.dismiss();
    }

    public void Spin_Service(){

        List<String> categories = new ArrayList<String>();
        categories.add("Parent");
        categories.add("Super Visor");

        ListServices = new ArrayAdapter<String>(getApplicationContext(), R.layout.textcolorspinner, categories) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };
        ListServices.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Selecttype.setAdapter(ListServices);
        Selecttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(Selecttype.getSelectedItem().toString().equals("Super Visor")){
                    type="supervisor";
                }else if(Selecttype.getSelectedItem().toString().equals("Parent")){
                    type="parent";
                }
              getSuperrvisors.GetSuperrvisors(UserToken,type);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void GetAllSuperVisors(final List<AllSuperVisors> lists) {

        List = new ArrayAdapter<AllSuperVisors>(getApplicationContext(), R.layout.textcolorspinner,lists) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        };
        List.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        allsupervisors.setAdapter(List);
        allsupervisors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
           String supers=allsupervisors.getSelectedItem().toString();
                for(i = 0; i<lists.size(); i++){
                    if(lists.get(i).getName().equals(supers)){
                        id=String.valueOf(lists.get(i).getId());
                    }
                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//       }
    }

    @Override
    public void Error() {
        progressdialog.dismiss();
        finish();
    }
}
