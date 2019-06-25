package com.waen.waen.Main.Presenter;

import android.content.Context;

import com.waen.waen.Main.Model.LoginInFo;
import com.waen.waen.Main.Model.UserLoginResponse;
import com.waen.waen.Retrofit.ApiCLint;
import com.waen.waen.Retrofit.Apiinterface;
import com.waen.waen.Main.View.LoginView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 17/12/2018.
 */

public class LoginPresenter {
    LoginView loginvieew;

    public LoginPresenter(Context context, LoginView Loginview)
    {
        this.loginvieew=Loginview;

    }

    public void Login(String UserEmai,String UserPassword) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("email",UserEmai);
        queryMap.put("password", UserPassword);
        queryMap.put("api_token", "100");

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<UserLoginResponse> call = apiInterface.Login(queryMap);
        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData().getMessage().equals("success")){
                        LoginInFo loginIn=new LoginInFo();
                        loginIn.setRole(response.body().getData().getRole());
                        loginIn.setUser_token(response.body().getData().getUserToken());
                        loginIn.setId(String.valueOf(response.body().getData().getId()));
                        loginIn.setBusname(response.body().getData().getBusName());
                        loginIn.setBusnumber(response.body().getData().getBusNumberBus());
                        loginIn.setBuscapcity(response.body().getData().getBusCapacity());
                        loginIn.setDrivername(response.body().getData().getDriverName());
                        loginIn.setBusnumberstudent(response.body().getData().getBusNumberStudent());
                        loginIn.setRole(response.body().getData().getRole());
                        loginIn.setUsertokenadmin(response.body().getData().getUserTokenAdmin());
                        loginIn.setUsertokenParent(response.body().getData().getUserTokenParent());
                        loginvieew.OpenRole(loginIn);
                    } else if(response.body().getData().getMessage().equals("login filed try again")){
                        loginvieew.Invalidemail("");
                    }
            }else {
                    loginvieew.showError("");
                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                loginvieew.showError("");

            }
        });
        }

}
