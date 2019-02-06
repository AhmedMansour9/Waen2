package com.waen.waen.Main.Presenter;

import android.content.Context;

import com.waen.waen.Admin.Model.Send_Message_Response;
import com.waen.waen.Retrofit.ApiCLint;
import com.waen.waen.Retrofit.Apiinterface;
import com.waen.waen.Admin.View.SendMessage_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 13/12/2018.
 */

public class Send_Message_Presenter {
    SendMessage_View cart_view;

    public Send_Message_Presenter(Context context, SendMessage_View cart_view)
    {
        this.cart_view=cart_view;

    }

    public void Send_Message(String user,String Message,String title) {
        Map<String, String> queryMap = new HashMap<>();

        queryMap.put("user_token", user);
        queryMap.put("title", title);
        queryMap.put("body", Message);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Send_Message_Response> call = apiInterface.SendMessage(queryMap);
        call.enqueue(new Callback<Send_Message_Response>() {
            @Override
            public void onResponse(Call<Send_Message_Response> call, Response<Send_Message_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData().equals("done send message success"))
                    cart_view.Success();
                } else {
                    cart_view.Failed();
                }
            }
            @Override
            public void onFailure(Call<Send_Message_Response> call, Throwable t) {

                cart_view.Failed();

            }
        });
    }


    public void Send_MessageParent(String user,String title,String Message) {
        Map<String, String> queryMap = new HashMap<>();

        queryMap.put("user_token", user);
        queryMap.put("title", title);
        queryMap.put("body", Message);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Send_Message_Response> call = apiInterface.SendMessageParent(queryMap);
        call.enqueue(new Callback<Send_Message_Response>() {
            @Override
            public void onResponse(Call<Send_Message_Response> call, Response<Send_Message_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData().equals("done send message success"))
                        cart_view.Success();
                } else {
                    cart_view.Failed();
                }
            }
            @Override
            public void onFailure(Call<Send_Message_Response> call, Throwable t) {

                cart_view.Failed();

            }
        });
    }

    public void Send_MessageAdmin(String user,String title,String Message,String id,String type) {
        Map<String, String> queryMap = new HashMap<>();

        queryMap.put("user_token", user);
        queryMap.put("title", title);
        queryMap.put("body", Message);
        queryMap.put("type", type);
        queryMap.put("id", id);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Send_Message_Response> call = apiInterface.SendMsg(queryMap);
        call.enqueue(new Callback<Send_Message_Response>() {
            @Override
            public void onResponse(Call<Send_Message_Response> call, Response<Send_Message_Response> response) {

                if (response.isSuccessful()) {
                    if(response.body().getData().equals("done send message success"))
                        cart_view.Success();
                } else {
                    cart_view.Failed();
                }
            }
            @Override
            public void onFailure(Call<Send_Message_Response> call, Throwable t) {

                cart_view.Failed();

            }
        });
    }
}
