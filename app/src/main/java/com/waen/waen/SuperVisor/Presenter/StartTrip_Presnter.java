package com.waen.waen.SuperVisor.Presenter;

import android.content.Context;

import com.waen.waen.Admin.View.SendMessage_View;
import com.waen.waen.Retrofit.ApiCLint;
import com.waen.waen.Retrofit.Apiinterface;
import com.waen.waen.SuperVisor.Model.StartTrip_Response;
import com.waen.waen.SuperVisor.View.TripKey;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 24/12/2018.
 */

public class StartTrip_Presnter {
    TripKey cart_view;

    public StartTrip_Presnter(Context context, TripKey cart_view)
    {
        this.cart_view=cart_view;

    }

    public void StartTrip(String user,String UserAdmin,String Action,String Startlat,String Startlon,String Trip) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);
        queryMap.put("user_token_admin", UserAdmin);
        queryMap.put("action", Action);
        queryMap.put("trip", Trip);
        queryMap.put("start_move_lat", Startlat);
        queryMap.put("start_move_lng", Startlon);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<StartTrip_Response> call = apiInterface.StartTrip(queryMap);
        call.enqueue(new Callback<StartTrip_Response>() {
            @Override
            public void onResponse(Call<StartTrip_Response> call, Response<StartTrip_Response> response) {

                if (response.isSuccessful()) {
                    cart_view.success(response.body().getData().getKey());
                } else {
                    cart_view.Error();
                }
            }
            @Override
            public void onFailure(Call<StartTrip_Response> call, Throwable t) {

                cart_view.Error();

            }
        });
    }

    public void EndTrip(String user,String UserAdmin,String Action,String Startlat,String Startlon,String Trip) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);
        queryMap.put("action", Action);
        queryMap.put("trip", Trip);
        queryMap.put("user_token_admin", UserAdmin);
        queryMap.put("start_move_lat", Startlat);
        queryMap.put("start_move_lng", Startlon);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<StartTrip_Response> call = apiInterface.StartTrip(queryMap);
        call.enqueue(new Callback<StartTrip_Response>() {
            @Override
            public void onResponse(Call<StartTrip_Response> call, Response<StartTrip_Response> response) {

                if (response.isSuccessful()) {
                    cart_view.EndTrip(response.body().getData().getKey());
                } else {
                    cart_view.Error();
                }
            }
            @Override
            public void onFailure(Call<StartTrip_Response> call, Throwable t) {

                cart_view.Error();

            }
        });
    }


}
