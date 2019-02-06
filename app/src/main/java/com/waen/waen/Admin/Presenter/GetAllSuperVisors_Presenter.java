package com.waen.waen.Admin.Presenter;

import android.content.Context;

import com.waen.waen.Admin.Model.All_SuperVisora_Response;
import com.waen.waen.Admin.Model.GetBusesInfo_Response;
import com.waen.waen.Admin.View.GetAllSuperVisors_View;
import com.waen.waen.Admin.View.GetBusesMap_View;
import com.waen.waen.Retrofit.ApiCLint;
import com.waen.waen.Retrofit.Apiinterface;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllSuperVisors_Presenter {

    GetAllSuperVisors_View BusesInfo;

    public GetAllSuperVisors_Presenter(Context context, GetAllSuperVisors_View cart_view) {
        this.BusesInfo = cart_view;

    }

    public void GetSuperrvisors(String user,String type) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("type", type);
        queryMap.put("user_token", user);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<All_SuperVisora_Response> call = apiInterface.GetALlSupervisors(queryMap);
        call.enqueue(new Callback<All_SuperVisora_Response>() {
            @Override
            public void onResponse(Call<All_SuperVisora_Response> call, Response<All_SuperVisora_Response> response) {

                if (response.isSuccessful()) {
                    BusesInfo.GetAllSuperVisors(response.body().getData());
                } else {
                    BusesInfo.Error();
                }
            }

            @Override
            public void onFailure(Call<All_SuperVisora_Response> call, Throwable t) {

                BusesInfo.Error();

            }
        });
    }

}
