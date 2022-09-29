package com.example.dochere.network;

import com.example.dochere.model.ModelUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("createAccount.php")
    Call<ModelUser> createAccount(@Body ModelUser modelUser);

    @POST("login.php")
    Call<ModelUser> login(@Body ModelUser modelUser);

}
