package com.nadakacheri.samyojane_app.api;

import com.nadakacheri.samyojane_app.model.TownWard.TownWardResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Nischitha on 08,November,2021
 **/
public interface APIInterface_SamyojaneAPI {
    @POST("Get_Version/{flag1}/{flag2}")
    Call<String> doGet_Version(
            @Path("flag1") String flag1,
            @Path("flag2") String flag2
    );
    @POST("Fn_Validate/{flag1}/{flag2}/{MobNum}/{IMEI}")
    Call<String> doFn_Validate(
            @Path("flag1") String flag1,
            @Path("flag2") String flag2,
            @Path("MobNum") String MobNum,
            @Path("IMEI") String IMEI
    );

    @POST("Fn_Get_Facility_Services/{flag1}/{flag2}")
    Call<String> doFn_Get_Facility_Services(
            @Path("flag1") String flag1,
            @Path("flag2") String flag2
    );

    @POST("Fn_Get_Caste/{flag1}/{flag2}")
    Call<String> doFn_Get_Caste(
            @Path("flag1") String flag1,
            @Path("flag2") String flag2
    );

    @POST("Fn_Get_OBC_Caste/{flag1}/{flag2}")
    Call<String> doFn_Get_OBC_Caste(
            @Path("flag1") String flag1,
            @Path("flag2") String flag2
    );

    @POST("Fn_Get_Category/{flag1}/{flag2}")
    Call<String> doFn_Get_Category(
            @Path("flag1") String flag1,
            @Path("flag2") String flag2
    );

    @POST("Fn_Get_Village_Name/{flag1}/{flag2}/{District_Code}/{Taluk_Code}/{Hobli_Code}/{VA_Cicle_Code}")
    Call<String> doFn_Get_Village_Name(
            @Path("flag1") String flag1,
            @Path("flag2") String flag2,
            @Path("District_Code") int District_Code,
            @Path("Taluk_Code") int Taluk_Code,
            @Path("Hobli_Code") int Hobli_Code,
            @Path("VA_Cicle_Code") int VA_Cicle_Code
    );

    @POST("Fn_Get_Village_Name_For_RI/{flag1}/{flag2}/{District_Code}/{Taluk_Code}/{Hobli_Code}/{IMEI}")
    Call<String> doFn_Get_Village_Name_For_RI(
            @Path("flag1") String flag1,
            @Path("flag2") String flag2,
            @Path("District_Code") int District_Code,
            @Path("Taluk_Code") int Taluk_Code,
            @Path("Hobli_Code") int Hobli_Code,
            @Path("IMEI") String IMEI
    );

    @POST("Get_Village_name_RuralUrban/{flag1}/{flag2}/{District_Code}/{Taluk_Code}/{Hobli_Code}")
    Call<String> doGet_Village_name_RuralUrban(
            @Path("flag1") String flag1,
            @Path("flag2") String flag2,
            @Path("District_Code") int District_Code,
            @Path("Taluk_Code") int Taluk_Code,
            @Path("Hobli_Code") int Hobli_Code
    );

    @GET("GetTownMobile/{Flag1}/{Flag2}/{LoginId}/{DCode}/{TCode}/{HCode}/{VACirCode}/{Designation}/{Mobile}")
    Call<TownWardResponse> doFn_Get_Town_Name(
            @Path("Flag1") String flag1,
            @Path("Flag2") String flag2,
            @Path("LoginId") String loginid,
            @Path("DCode") String District_Code,
            @Path("TCode") String Taluk_Code,
            @Path("HCode") String Hobli_Code,
            @Path("VACirCode") String Va_Circle_Cod,
            @Path("Designation") String Designation,
            @Path("Mobile") String Mobile
    );
    /*@GET("GetTown/{flag1}/{flag2}/{District_Code}/{Taluk_Code}/{loginid}")
    Call<TownWardResponse> doFn_Get_Town_Name_HK(
            @Path("flag1") String flag1,
            @Path("flag2") String flag2,
            @Path("District_Code") String District_Code,
            @Path("Taluk_Code") String Taluk_Code,
            @Path("loginid") String loginid
    );*/



    @GET("GetWard/{flag1}/{flag2}/{District_Code}/{Taluk_Code}/{Hobli_Code}/{Town_Code}/{loginid}")
    Call<TownWardResponse> doFn_Get_Ward_Name(
            @Path("flag1") String flag1,
            @Path("flag2") String flag2,
            @Path("District_Code") String District_Code,
            @Path("Taluk_Code") String Taluk_Code,
            @Path("Hobli_Code") String Hobli_Code,
            @Path("Town_Code") String Town_Code,
            @Path("loginid") String loginid
    );
}
