package com.ilh.alpro_telkom.rest;

import com.ilh.alpro_telkom.model.PelaporModel;
import com.ilh.alpro_telkom.model.ResponseErrorModel;

import java.util.ArrayList;

import okhttp3.ResponseBody;
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
    Call<ArrayList<PelaporModel>> getAllData(
            @Query("change") String change
    );
    @GET("api_get.php")
    Call<ArrayList<PelaporModel>> getHistory(
            @Query("change") String change,
            @Query("id_user_validator") String idUser
    );
    @GET("api_get.php")
    Call<ArrayList<PelaporModel>> getHistoryTeknisi(
            @Query("change") String change,
            @Query("id_user_teknisi") String idUser
    );

    @GET("api_get.php")
    Call<ArrayList<PelaporModel>> getAllDataDisetujui(
            @Query("change") String change
    );
    @GET("api_get.php")
    Call<ResponseErrorModel> getRegID(
            @Query("change") String change,
            @Query("id_user") String idAKun
    );

    @FormUrlEncoded
    @POST("api_tambah_pelapor.php")
    Call<ResponseErrorModel> postDataPelapor(
            @Field("url_image") String url_image,
            @Field("deskripsi") String deskripsi,
                                   @Field("alamat") String alamat,
                                   @Field("id_user_akun") String idAKun

    );

    @FormUrlEncoded
    @POST("api_update_validator.php")
    Call<ResponseErrorModel> updateStatusValidator(
            @Field("id_pelapor") String id_pelapor,
            @Field("id_user_validator") String id_user_validator,
                                   @Field("status") String status);

    @FormUrlEncoded
    @POST("api_update_teknisi.php")
    Call<ResponseErrorModel> updateStatusTeknisi(
            @Field("id_pelapor") String id_pelapor,
            @Field("id_user_teknisi") String id_user_teknisi,
                                   @Field("status") String status);
    @FormUrlEncoded
    @POST("api_register.php")
    Call<ResponseErrorModel> register(
            @Field("nama_lengkap") String nama_lengkap,
            @Field("username") String username,
            @Field("password") String password,
            @Field("reg_id") String reg_id,
            @Field("rule") String rule);



    @GET("firebase")
    Call<ResponseBody> postDataNotif(
            @Query("title") String title,
            @Query("message") String message,
            @Query("push_type") String push_type,
            @Query("regId") String regId

    );


}
