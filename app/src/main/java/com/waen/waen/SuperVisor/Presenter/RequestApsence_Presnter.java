package com.waen.waen.SuperVisor.Presenter;

import android.content.Context;

import com.waen.waen.Retrofit.ApiCLint;
import com.waen.waen.Retrofit.Apiinterface;
import com.waen.waen.SuperVisor.Model.Request_Absence_Response;
import com.waen.waen.SuperVisor.Model.StartTrip_Response;
import com.waen.waen.SuperVisor.View.RequestAbsence_View;
import com.waen.waen.SuperVisor.View.TripKey;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestApsence_Presnter {

    RequestAbsence_View cart_view;

    public RequestApsence_Presnter(Context context, RequestAbsence_View cart_view)
    {
        this.cart_view=cart_view;

    }

    public void Request_Absence(String Studentid,String title,String message,String from,String to,String user_token_supervisor) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("student_id", Studentid);
        queryMap.put("title", title);
        queryMap.put("message", message);
        queryMap.put("from", from);
        queryMap.put("to", to);
        queryMap.put("user_token_supervisor", user_token_supervisor);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<Request_Absence_Response> call = apiInterface.RequestAbsence(queryMap);
        call.enqueue(new Callback<Request_Absence_Response>() {
            @Override
            public void onResponse(Call<Request_Absence_Response> call, Response<Request_Absence_Response> response) {

                if (response.isSuccessful()) {
                    cart_view.success();
                } else {
                    cart_view.Error();
                }
            }
            @Override
            public void onFailure(Call<Request_Absence_Response> call, Throwable t) {

                cart_view.Error();

            }
        });
    }

}
