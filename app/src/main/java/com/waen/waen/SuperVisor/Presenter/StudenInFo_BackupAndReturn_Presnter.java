package com.waen.waen.SuperVisor.Presenter;

import android.content.Context;

import com.waen.waen.Retrofit.ApiCLint;
import com.waen.waen.Retrofit.Apiinterface;
import com.waen.waen.SuperVisor.Model.Routes_Info_Response;
import com.waen.waen.SuperVisor.Model.StudentInFo_Response;
import com.waen.waen.SuperVisor.View.RouteInFo_View;
import com.waen.waen.SuperVisor.View.StudenInfo_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed on 25/12/2018.
 */

public class StudenInFo_BackupAndReturn_Presnter {

    StudenInfo_View Routes_view;

    public StudenInFo_BackupAndReturn_Presnter(Context context, StudenInfo_View cart_view) {
        this.Routes_view = cart_view;

    }

    public void GetStudentinfo(String user, String trip,String id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);
        queryMap.put("student_id", id);
        queryMap.put("trip", trip);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<StudentInFo_Response> call = apiInterface.GetStudentinfo(queryMap);
        call.enqueue(new Callback<StudentInFo_Response>() {
            @Override
            public void onResponse(Call<StudentInFo_Response> call, Response<StudentInFo_Response> response) {
                if (response.isSuccessful()) {
                    Routes_view.GetStudents(response.body().getData());
                } else {
                    Routes_view.Error();
                }
            }
            @Override
            public void onFailure(Call<StudentInFo_Response> call, Throwable t) {

                Routes_view.Error();

            }
        });
    }
}
