package com.waen.waen.Retrofit;

import com.waen.waen.Admin.Model.All_SuperVisora_Response;
import com.waen.waen.Admin.Model.GetBusesInfo_Response;
import com.waen.waen.Admin.Model.Notifications;
import com.waen.waen.Admin.Model.Notifications_Response;
import com.waen.waen.Admin.Model.Send_Message_Response;
import com.waen.waen.Main.Model.UserLoginResponse;
import com.waen.waen.Parent.Model.Buses_Students_Response;
import com.waen.waen.SuperVisor.Model.Inbox_Response;
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

    @POST("messageSupervisor")
    Call<Send_Message_Response> SendMessage(@QueryMap Map<String, String> queryMab);

    @POST("playerIds")
    Call<Send_Message_Response> SendPlayerid(@QueryMap Map<String, String> queryMab);


    @POST("messageParent")
    Call<Send_Message_Response> SendMessageParent(@QueryMap Map<String, String> queryMab);


    @POST("infoStudents")
    Call<Routes_Info_Response> GetRoutesinfo(@QueryMap Map<String, String> queryMab);

    @POST("BusDetails")
    Call<StartTrip_Response> StartTrip(@QueryMap Map<String, String> queryMab);

    @POST("backUpAndReturn")
    Call<StudentInFo_Response> GetStudentinfo(@QueryMap Map<String, String> queryMab);

    @POST("infoSchool")
    Call<GetBusesInfo_Response> GetBusesinfo(@QueryMap Map<String, String> queryMab);

    @POST("studentParent")
    Call<Buses_Students_Response> GetBusesStudents(@QueryMap Map<String, String> queryMab);

    @POST("notification")
    Call<Notifications_Response> GetNotifications(@QueryMap Map<String, String> queryMab);


    @POST("inboxMessageSupervisor")
    Call<Inbox_Response> Inbox_SuperVisor(@QueryMap Map<String, String> queryMab);

    @POST("inboxMessageAdmin")
    Call<Inbox_Response> Inbox_SuperVisor_Admin(@QueryMap Map<String, String> queryMab);

    @POST("sendMessageAdmin")
    Call<Inbox_Response> Sent_SuperVisor_Admin(@QueryMap Map<String, String> queryMab);


    @POST("inboxMessageParent")
    Call<Inbox_Response> Inbox_Parent(@QueryMap Map<String, String> queryMab);

    @POST("sendMessageParent")
    Call<Inbox_Response> Send_Parent(@QueryMap Map<String, String> queryMab);


    @POST("sendMessageSupervisor")
    Call<Inbox_Response> Recive_SuperVisor(@QueryMap Map<String, String> queryMab);

    @POST("typeMessage")
    Call<All_SuperVisora_Response> GetALlSupervisors(@QueryMap Map<String, String> queryMab);

    @POST("messageAdmin")
    Call<Send_Message_Response> SendMsg(@QueryMap Map<String, String> queryMab);

}
