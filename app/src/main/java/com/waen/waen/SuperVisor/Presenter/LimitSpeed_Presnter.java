package com.waen.waen.SuperVisor.Presenter;

import android.content.Context;

import com.waen.waen.Retrofit.ApiCLint;
import com.waen.waen.Retrofit.Apiinterface;
import com.waen.waen.SuperVisor.Model.SpeedLimit_Response;
import com.waen.waen.SuperVisor.Model.StudentInFo_Response;
import com.waen.waen.SuperVisor.View.LimitSpeed_View;
import com.waen.waen.SuperVisor.View.StudenInfo_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LimitSpeed_Presnter {

    LimitSpeed_View Routes_view;

    public LimitSpeed_Presnter(Context context) {
//        this.Routes_view = cart_view;

    }

    public void Speedlimit(String user,String Speed) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("speed", Speed);
        queryMap.put("user_token", user);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<SpeedLimit_Response> call = apiInterface.LimitSpeed(queryMap);
        call.enqueue(new Callback<SpeedLimit_Response>() {
            @Override
            public void onResponse(Call<SpeedLimit_Response> call, Response<SpeedLimit_Response> response) {
                if (response.isSuccessful()) {
//                    Routes_view.success();
                } else {
//                    Routes_view.Error();
                }
            }
            @Override
            public void onFailure(Call<SpeedLimit_Response> call, Throwable t) {

//                Routes_view.Error();

            }
        });
    }
}

