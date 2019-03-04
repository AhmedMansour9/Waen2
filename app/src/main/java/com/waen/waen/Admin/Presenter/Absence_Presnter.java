package com.waen.waen.Admin.Presenter;

import android.content.Context;

import com.waen.waen.Admin.Model.Absence_Response;
import com.waen.waen.Admin.Model.Notifications_Response;
import com.waen.waen.Admin.View.GetAbsences_View;
import com.waen.waen.Admin.View.GetNotifications_View;
import com.waen.waen.Retrofit.ApiCLint;
import com.waen.waen.Retrofit.Apiinterface;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Absence_Presnter {

    GetAbsences_View BusesInfo;

    public Absence_Presnter(Context context, GetAbsences_View cart_view) {
        this.BusesInfo = cart_view;

    }

    public void GetAbsences(String user,String role) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("role", role);
        queryMap.put("user_token", user);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Absence_Response> call = apiInterface.GetAbsence(queryMap);
        call.enqueue(new Callback<Absence_Response>() {
            @Override
            public void onResponse(Call<Absence_Response> call, Response<Absence_Response> response) {

                if (response.isSuccessful()) {
                    BusesInfo.ListAbseces(response.body().getData());
                } else {
                    BusesInfo.Error();
                }
            }

            @Override
            public void onFailure(Call<Absence_Response> call, Throwable t) {

                BusesInfo.Error();

            }
        });
    }

}

