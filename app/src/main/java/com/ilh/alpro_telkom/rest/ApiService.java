package com.ilh.alpro_telkom.rest;

import com.ilh.alpro_telkom.model.PelaporModel;
import com.ilh.alpro_telkom.model.ResponseErrorModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api_login.php")
    Call<ResponseErrorModel> login(@Query("username") String username,
                                   @Query("password") String password);
    @GET("api_get.php")
    Call<ArrayList<PelaporModel>> getAllData();

    @FormUrlEncoded
    @POST("api_tambah_pelapor.php")
    Call<ResponseErrorModel> postDataPelapor(
            @Field("url_image") String url_image,
            @Field("deskripsi") String deskripsi,
                                   @Field("alamat") String alamat);

    @FormUrlEncoded
    @POST("api_update_validator.php")
    Call<ResponseErrorModel> updateStatusValidator(
            @Field("id_pelapor") String id_pelapor,
                                   @Field("status") String status);



//    @GET("firebase")
//    Call<ResponseBody> postData(
//            @Query("title") String title,
//            @Query("message") String message,
//            @Query("push_type") String push_type,
//            @Query("regId") String regId
//
//    );


}
