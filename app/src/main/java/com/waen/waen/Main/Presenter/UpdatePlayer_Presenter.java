package com.waen.waen.Main.Presenter;

import android.content.Context;

import com.waen.waen.Admin.Model.Send_Message_Response;
import com.waen.waen.Admin.View.SendMessage_View;
import com.waen.waen.Main.View.UpdatePlayerid_View;
import com.waen.waen.Retrofit.ApiCLint;
import com.waen.waen.Retrofit.Apiinterface;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePlayer_Presenter {

    UpdatePlayerid_View cart_view;

    public UpdatePlayer_Presenter(Context context, UpdatePlayerid_View cart_view)
    {
        this.cart_view=cart_view;

    }

    public void SendPlayerId(String user,String playerid) {
        Map<String, String> queryMap = new HashMap<>();

        queryMap.put("user_token", user);
        queryMap.put("player_ids", playerid);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Send_Message_Response> call = apiInterface.SendPlayerid(queryMap);
        call.enqueue(new Callback<Send_Message_Response>() {
            @Override
            public void onResponse(Call<Send_Message_Response> call, Response<Send_Message_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData().equals("success"))
                        cart_view.playerid();
                } else {
                    cart_view.Error();
                }
            }
            @Override
            public void onFailure(Call<Send_Message_Response> call, Throwable t) {

                cart_view.Error();

            }
        });
    }
}
