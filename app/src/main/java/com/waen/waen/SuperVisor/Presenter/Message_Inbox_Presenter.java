package com.waen.waen.SuperVisor.Presenter;

import android.content.Context;

import com.waen.waen.Admin.Model.Send_Message_Response;
import com.waen.waen.Retrofit.ApiCLint;
import com.waen.waen.Retrofit.Apiinterface;
import com.waen.waen.SuperVisor.Model.Inbox_Response;
import com.waen.waen.SuperVisor.Model.Routes_Info_Response;
import com.waen.waen.SuperVisor.View.Messages_Inbox_View;
import com.waen.waen.SuperVisor.View.RouteInFo_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Message_Inbox_Presenter {

    Messages_Inbox_View Routes_view;

    public Message_Inbox_Presenter(Context context, Messages_Inbox_View cart_view) {
        this.Routes_view = cart_view;
    }

    public void Inbox_SuperVisor(String user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("user_token", user);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Inbox_Response> call = apiInterface.Inbox_SuperVisor(queryMap);
        call.enqueue(new Callback<Inbox_Response>() {
            @Override
            public void onResponse(Call<Inbox_Response> call, Response<Inbox_Response> response) {

                if (response.code()==200) {
                    Routes_view.GetMessagesParent(response.body().getData());
                } else {
                    Routes_view.Error();
                }
            }

            @Override
            public void onFailure(Call<Inbox_Response> call, Throwable t) {

                Routes_view.Error();

            }
        });
    }

    public void Inbox_Parent(String user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("user_token", user);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Inbox_Response> call = apiInterface.Inbox_Parent(queryMap);
        call.enqueue(new Callback<Inbox_Response>() {
            @Override
            public void onResponse(Call<Inbox_Response> call, Response<Inbox_Response> response) {

                if (response.isSuccessful()) {
                    Routes_view.GetMessagesParent(response.body().getData());
                } else {
                    Routes_view.Error();
                }
            }

            @Override
            public void onFailure(Call<Inbox_Response> call, Throwable t) {

                Routes_view.Error();

            }
        });
    }

    public void Sent_SuperVisor(String user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("user_token", user);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Inbox_Response> call = apiInterface.Recive_SuperVisor(queryMap);
        call.enqueue(new Callback<Inbox_Response>() {
            @Override
            public void onResponse(Call<Inbox_Response> call, Response<Inbox_Response> response) {

                if (response.isSuccessful()) {
                    Routes_view.SendMessages_SuperVisor(response.body().getData());
                } else {
                    Routes_view.Error();
                }
            }

            @Override
            public void onFailure(Call<Inbox_Response> call, Throwable t) {

                Routes_view.Error();

            }
        });
    }

    public void Send_Parent(String user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("user_token", user);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Inbox_Response> call = apiInterface.Send_Parent(queryMap);
        call.enqueue(new Callback<Inbox_Response>() {
            @Override
            public void onResponse(Call<Inbox_Response> call, Response<Inbox_Response> response) {

                if (response.isSuccessful()) {
                    Routes_view.SendMessages_SuperVisor(response.body().getData());
                } else {
                    Routes_view.Error();
                }
            }

            @Override
            public void onFailure(Call<Inbox_Response> call, Throwable t) {

                Routes_view.Error();

            }
        });
    }

    public void Inbox_SuperVisor_Admin(String user,String type) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("user_token", user);
        queryMap.put("type", type);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Inbox_Response> call = apiInterface.Inbox_SuperVisor_Admin(queryMap);
        call.enqueue(new Callback<Inbox_Response>() {
            @Override
            public void onResponse(Call<Inbox_Response> call, Response<Inbox_Response> response) {

                if (response.code()==200) {
                    Routes_view.Inbox_SuperVisor_Admin(response.body().getData());
                } else {
                    Routes_view.Error();
                }
            }

            @Override
            public void onFailure(Call<Inbox_Response> call, Throwable t) {

                Routes_view.Error();

            }
        });
    }
    public void Inbox_Parent_Admin(String user,String type) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("user_token", user);
        queryMap.put("type", type);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Inbox_Response> call = apiInterface.Inbox_SuperVisor_Admin(queryMap);
        call.enqueue(new Callback<Inbox_Response>() {
            @Override
            public void onResponse(Call<Inbox_Response> call, Response<Inbox_Response> response) {

                if (response.code()==200) {
                    Routes_view.Inbox_Parent_Admin(response.body().getData());
                } else {
                    Routes_view.Error();
                }
            }

            @Override
            public void onFailure(Call<Inbox_Response> call, Throwable t) {

                Routes_view.Error();

            }
        });
    }
    public void Sent_Parent_Admin(String user,String type) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("user_token", user);
        queryMap.put("type", type);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Inbox_Response> call = apiInterface.Sent_SuperVisor_Admin(queryMap);
        call.enqueue(new Callback<Inbox_Response>() {
            @Override
            public void onResponse(Call<Inbox_Response> call, Response<Inbox_Response> response) {

                if (response.code()==200) {
                    Routes_view.Sent_Parent_Admin(response.body().getData());
                } else {
                    Routes_view.Error();
                }
            }

            @Override
            public void onFailure(Call<Inbox_Response> call, Throwable t) {

                Routes_view.Error();

            }
        });
    }
    public void Sent_SuperVisor_Admin(String user,String type) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("user_token", user);
        queryMap.put("type", type);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Inbox_Response> call = apiInterface.Sent_SuperVisor_Admin(queryMap);
        call.enqueue(new Callback<Inbox_Response>() {
            @Override
            public void onResponse(Call<Inbox_Response> call, Response<Inbox_Response> response) {

                if (response.code()==200) {
                    Routes_view.Sent_SuperVisor_Admin(response.body().getData());
                } else {
                    Routes_view.Error();
                }
            }

            @Override
            public void onFailure(Call<Inbox_Response> call, Throwable t) {

                Routes_view.Error();

            }
        });
    }
}
