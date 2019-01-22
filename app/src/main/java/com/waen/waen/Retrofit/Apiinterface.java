package com.waen.waen.Retrofit;

import com.waen.waen.Admin.Model.GetBusesInfo_Response;
import com.waen.waen.Main.Model.UserLoginResponse;
import com.waen.waen.SuperVisor.Model.Routes_Info_Response;
import com.waen.waen.SuperVisor.Model.StartTrip_Response;
import com.waen.waen.SuperVisor.Model.StudentInFo_Response;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Ahmed on 13/12/2018.
 */

public interface Apiinterface {

//    @POST("signupMobile")
//    Call<RegisterResponse> register(@QueryMap Map<String, String> queryMab);
//
    @POST("login")
    Call<UserLoginResponse> Login(@QueryMap Map<String, String> queryMab);

    @POST("infoStudents")
    Call<Routes_Info_Response> GetRoutesinfo(@QueryMap Map<String, String> queryMab);

    @POST("BusDetails")
    Call<StartTrip_Response> StartTrip(@QueryMap Map<String, String> queryMab);

    @POST("backUpAndReturn")
    Call<StudentInFo_Response> GetStudentinfo(@QueryMap Map<String, String> queryMab);

    @POST("infoSchool")
    Call<GetBusesInfo_Response> GetBusesinfo(@QueryMap Map<String, String> queryMab);


//    @POST("login")
//    Call<Send_Message_Response> Send_Message(@QueryMap Map<String, String> queryMab);

}
