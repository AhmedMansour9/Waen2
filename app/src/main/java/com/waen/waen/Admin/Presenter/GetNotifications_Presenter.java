package com.waen.waen.Admin.Presenter;

import android.content.Context;

import com.waen.waen.Admin.Model.GetBusesInfo_Response;
import com.waen.waen.Admin.Model.Notifications_Response;
import com.waen.waen.Admin.View.GetBusesMap_View;
import com.waen.waen.Admin.View.GetNotifications_View;
import com.waen.waen.Retrofit.ApiCLint;
import com.waen.waen.Retrofit.Apiinterface;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetNotifications_Presenter {

    GetNotifications_View BusesInfo;

    public GetNotifications_Presenter(Context context, GetNotifications_View cart_view) {
        this.BusesInfo = cart_view;

    }

    public void GetNotifications(String user,String role) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("role", role);
        queryMap.put("user_token", user);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Notifications_Response> call = apiInterface.GetNotifications(queryMap);
        call.enqueue(new Callback<Notifications_Response>() {
            @Override
            public void onResponse(Call<Notifications_Response> call, Response<Notifications_Response> response) {

                if (response.isSuccessful()) {
                    BusesInfo.getnotifcations(response.body().getData());
                } else {
                    BusesInfo.Error();
                }
            }

            @Override
            public void onFailure(Call<Notifications_Response> call, Throwable t) {

                BusesInfo.Error();

            }
        });
    }

}

