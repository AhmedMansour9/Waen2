package com.waen.waen.Admin.Presenter;

import android.content.Context;

import com.waen.waen.Admin.Model.GetBusesInfo_Response;
import com.waen.waen.Admin.View.GetBusesMap_View;
import com.waen.waen.Retrofit.ApiCLint;
import com.waen.waen.Retrofit.Apiinterface;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetBuses_Presenter {

    GetBusesMap_View BusesInfo;

    public GetBuses_Presenter(Context context, GetBusesMap_View cart_view) {
        this.BusesInfo = cart_view;

    }

    public void GetBuses(String user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<GetBusesInfo_Response> call = apiInterface.GetBusesinfo(queryMap);
        call.enqueue(new Callback<GetBusesInfo_Response>() {
            @Override
            public void onResponse(Call<GetBusesInfo_Response> call, Response<GetBusesInfo_Response> response) {

                if (response.isSuccessful()) {
                    BusesInfo.ListBuses(response.body().getData());
                } else {
                    BusesInfo.Error();
                }
            }

            @Override
            public void onFailure(Call<GetBusesInfo_Response> call, Throwable t) {

                BusesInfo.Error();

            }
        });
    }

}
