package com.waen.waen.Main;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.waen.waen.Admin.View.SendMessage_View;
import com.waen.waen.Main.Presenter.Send_Message_Presenter;
import com.waen.waen.R;
import com.waen.waen.SharedPrefManager;

public class SendMessage extends AppCompatActivity implements SendMessage_View {

    EditText E_Message,E_Title;
    Button Btn_SendMessage;
    Send_Message_Presenter send_message_presenter;
    String UserToken;
    ProgressDialog progressdialog;
    String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_send_message);
        send_message_presenter=new Send_Message_Presenter(this,this);
        UserToken= SharedPrefManager.getInstance(this).getUserToken();
        progressdialog=new ProgressDialog(this);
        E_Title=findViewById(R.id.E_Title);
        E_Message=findViewById(R.id.E_Message);
        Btn_SendMessage=findViewById(R.id.Btn_SendMessage);
          role=getIntent().getStringExtra("type");
        Btn_SendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!E_Message.equals("")&&!E_Title.equals("")){
                    progressdialog.setMessage("Please Wait While Sending Message....");
                    progressdialog.show();
                    progressdialog.setCancelable(false);
                 if(role.equals("supervisor")) {
                     send_message_presenter.Send_Message(UserToken, E_Message.getText().toString(), E_Title.getText().toString());
                 }else {
                     send_message_presenter.Send_MessageParent(UserToken, E_Message.getText().toString(), E_Title.getText().toString());

                 }
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
}
