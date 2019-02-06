package com.waen.waen.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fourhcode.forhutils.FUtilsValidation;
import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;
import com.waen.waen.Admin.Activities.Navigation_Admin;
import com.waen.waen.Main.Model.LoginInFo;
import com.waen.waen.Main.Presenter.LoginPresenter;
import com.waen.waen.Main.Presenter.UpdatePlayer_Presenter;
import com.waen.waen.Main.View.LoginView;
import com.waen.waen.Main.View.UpdatePlayerid_View;
import com.waen.waen.NetworikConntection;
import com.waen.waen.Parent.Navigation_Parent;
import com.waen.waen.R;
import com.waen.waen.SharedPrefManager;
import com.waen.waen.SuperVisor.Home_Supervisor;

public class Login extends AppCompatActivity  implements LoginView, UpdatePlayerid_View {
    LoginPresenter login;
    Button Login;
    NetworikConntection Network;
    EditText E_Email,E_Password;
    ProgressBar progressBar;
    String Role;
    UpdatePlayer_Presenter updatePlayer_presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        String Usertoken=SharedPrefManager.getInstance(this).getUserToken();
        Role=SharedPrefManager.getInstance(getApplicationContext()).getRole();
        updatePlayer_presenter=new UpdatePlayer_Presenter(this,this);
        checkRole();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        init();
        Sigin();


    }
    public void checkRole(){
        if(Role!=null){
            if( Role.equals("admin")) {
                startActivity(new Intent(Login.this, Navigation_Admin.class));
                finish();
            }else if ( Role.equals("supervisor")){
                startActivity(new Intent(Login.this,Home_Supervisor.class));
                finish();
            }
            else if ( Role.equals("parent")){
                startActivity(new Intent(Login.this, Navigation_Parent.class));
                finish();
            }

        }
    }
    public void init(){
        E_Email=findViewById(R.id.E_Email);
        E_Password=findViewById(R.id.E_Password);
        login=new LoginPresenter(this,this);
        Network=new NetworikConntection(this);
        Login=findViewById(R.id.Login);
        progressBar=findViewById(R.id.Progrosspar);

    }
    public void Sigin(){
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Network.isNetworkAvailable(getApplicationContext())){
                    FUtilsValidation.isEmpty(E_Email,getResources().getString(R.string.insertemail));
                    FUtilsValidation.isEmpty(E_Password, getResources().getString(R.string.insertpassword));

                    if (!E_Email.getText().toString().equals("") && !E_Password.getText().toString().equals("")) {
                        progressBar.setVisibility(View.VISIBLE);

                        login.Login(E_Email.getText().toString(),E_Password.getText().toString());
                    }
                }else {
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.internet), Toast.LENGTH_LONG).show();
                }


            }
        });
    }


    @Override
    public void OpenRole(LoginInFo loginInFo) {
        SharedPrefManager.getInstance(getApplicationContext()).saveUserToken(loginInFo.getUser_token());
        SharedPrefManager.getInstance(getApplicationContext()).saveRole(loginInFo.getRole());
        SharedPrefManager.getInstance(getApplicationContext()).saveId(loginInFo.getId());

//        String email = Usertoken;
//        OneSignal.setEmail("bassem");
        OSPermissionSubscriptionState status = OneSignal.getPermissionSubscriptionState();
//        status.getPermissionStatus().getEnabled();
//        status.getSubscriptionStatus().getSubscribed();
//        status.getSubscriptionStatus().getUserSubscriptionSetting();
       String playerid= status.getSubscriptionStatus().getUserId();

        updatePlayer_presenter.SendPlayerId(loginInFo.getUser_token(),playerid);
        progressBar.setVisibility(View.GONE);
     if(loginInFo.getRole().equals("parent")){
         SharedPrefManager.getInstance(getApplicationContext()).saveUserTokenAdmin(loginInFo.getUsertokenadmin());
         startActivity(new Intent(Login.this,Navigation_Parent.class));
         finish();


     }
     else if(loginInFo.getRole().equals("supervisor")) {
         SharedPrefManager.getInstance(getApplicationContext()).saveBusName(loginInFo.getBusname());
         SharedPrefManager.getInstance(getApplicationContext()).saveBusNumber(loginInFo.getBusnumber());
         SharedPrefManager.getInstance(getApplicationContext()).saveBuscapcity(loginInFo.getBuscapcity());
         SharedPrefManager.getInstance(getApplicationContext()).saveBusNumberStudent(loginInFo.getBusnumberstudent());
         SharedPrefManager.getInstance(getApplicationContext()).saveDriverName(loginInFo.getDrivername());
         SharedPrefManager.getInstance(getApplicationContext()).saveUserTokenAdmin(loginInFo.getUsertokenadmin());
         startActivity(new Intent(Login.this,Home_Supervisor.class));
         finish();
     }
     else if(loginInFo.getRole().equals("admin")){
         startActivity(new Intent(Login.this,Navigation_Admin.class));
         finish();

     }


    }

    @Override
    public void showError(String error) {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void Invalidemail(String password) {
        Toast.makeText(getApplicationContext(), ""+getResources().getString(R.string.invalidemail), Toast.LENGTH_SHORT).show();

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void playerid() {

    }

    @Override
    public void Error() {

    }
}
