package com.waen.waen.SuperVisor.Presenter;

import android.content.Context;

import com.waen.waen.Admin.View.SendMessage_View;
import com.waen.waen.Retrofit.ApiCLint;
import com.waen.waen.Retrofit.Apiinterface;
import com.waen.waen.SuperVisor.Model.InFo;
import com.waen.waen.SuperVisor.Model.Routes_Details;
import com.waen.waen.SuperVisor.Model.Routes_Info_Response;
import com.waen.waen.SuperVisor.View.RouteInFo_View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 23/12/2018.
 */

public class GetStudentRouteInFo_Presenter {
    RouteInFo_View Routes_view;

    public GetStudentRouteInFo_Presenter(Context context, RouteInFo_View cart_view) {
        this.Routes_view = cart_view;

    }

    public void GetRoutes(String user, String trip) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);
        queryMap.put("trip", trip);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Routes_Info_Response> call = apiInterface.GetRoutesinfo(queryMap);
        call.enqueue(new Callback<Routes_Info_Response>() {
            @Override
            public void onResponse(Call<Routes_Info_Response> call, Response<Routes_Info_Response> response) {

                if (response.isSuccessful()) {
                    Routes_view.getlist(response.body().getData());
                    Routes_view.getlistInFo(response.body().getData().getData());
                } else {
                    Routes_view.Error();
                }
            }

            @Override
            public void onFailure(Call<Routes_Info_Response> call, Throwable t) {

                Routes_view.Error();

            }
        });
    }
}
