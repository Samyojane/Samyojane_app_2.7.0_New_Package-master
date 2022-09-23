package com.nadakacheri.samyojane_app.api;

import com.nadakacheri.samyojane_app.GetDocRequestClass;
import com.nadakacheri.samyojane_app.Set_and_Get_AckForData;
import com.nadakacheri.samyojane_app.UpdateStatusCLASS;
import com.nadakacheri.samyojane_app.UpdateVillageTownWardCLASS;
import com.google.gson.JsonObject;
import com.nadakacheri.samyojane_app.model.GenLogin.LoginRequest;
import com.nadakacheri.samyojane_app.model.GenLogin.LoginResponse;
import com.nadakacheri.samyojane_app.model.GenResult.genderRequest;
import com.nadakacheri.samyojane_app.model.GenResult.genderResponse;
import com.nadakacheri.samyojane_app.model.document.DocumentRequest;
import com.nadakacheri.samyojane_app.model.document.DocumentResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Nischitha on 08,November,2021
 **/
public interface APIInterface_NIC {
    @GET("GetListForFieldVerification/{Flag1}/{Flag2}/{District}/{Taluk}/{Hobli}/{loginid}/{Desicode}/{VACircleCode}/{Mobile}")
    Call<JsonObject> GetListForFieldVerification(
            @Path("Flag1") String Flag1,
            @Path("Flag2") String Flag2,
            @Path("District") int District,
            @Path("Taluk") int Taluk,
            @Path("Hobli") int Hobli,
            @Path("loginid") String loginid,
            @Path("Desicode") int Desicode,
            @Path("VACircleCode") int VACircleCode,
            @Path("Mobile") String Mobile
            );

    @POST("AckForData/")
    Call<JsonObject> AckForData(
            @Body Set_and_Get_AckForData ackForData
            );

    @Headers("Content-Type: application/json")
    @POST("GetDocs/")
    Call<JsonObject> GetDocs(
            @Body GetDocRequestClass getDocRequestClass
            );

    @POST("UpdateVillageTownWard/")
    Call<JsonObject> UpdateVillageTownWard(
            @Body UpdateVillageTownWardCLASS updateVillageTownWardCLASS
            );

    @POST("UpdateStatus/")
    Call<JsonObject> UpdateStatus(
            @Body UpdateStatusCLASS updateStatusCLASS
            );

    /*Below 2 APIs are to check the Gender of the person after capturing the image.*/
    @POST("login/")
    Call<LoginResponse> getAuthentication(@Body LoginRequest logReq);


    @POST("http://genage_api.kar.nic.in/api/upload_and_get/")
    Call<genderResponse> getGender(@Header("Authorization") String access, @Body genderRequest genReq);

    @POST("UploadDocs")
    Call<JsonObject> UploadDocument(@Body DocumentRequest documentRequest);
}
