package com.waen.waen.Parent.Presenter;

import android.content.Context;

import com.waen.waen.Admin.Model.GetBusesInfo_Response;
import com.waen.waen.Admin.View.GetBusesMap_View;
import com.waen.waen.Parent.Model.Buses_Students_Response;
import com.waen.waen.Parent.View.Buses_Students_View;
import com.waen.waen.Retrofit.ApiCLint;
import com.waen.waen.Retrofit.Apiinterface;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Buses_Students_Presnter {

    Buses_Students_View BusesInfo;

    public Buses_Students_Presnter(Context context, Buses_Students_View cart_view) {
        this.BusesInfo = cart_view;

    }

    public void GetBuses(String user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("user_token", user);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Buses_Students_Response> call = apiInterface.GetBusesStudents(queryMap);
        call.enqueue(new Callback<Buses_Students_Response>() {
            @Override
            public void onResponse(Call<Buses_Students_Response> call, Response<Buses_Students_Response> response) {

                if (response.isSuccessful()) {
                    BusesInfo.list(response.body().getData());
                } else {
                    BusesInfo.Error();
                }
            }

            @Override
            public void onFailure(Call<Buses_Students_Response> call, Throwable t) {

                BusesInfo.Error();

            }
        });
    }

}

