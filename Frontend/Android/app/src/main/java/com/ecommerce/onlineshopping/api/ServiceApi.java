package com.ecommerce.onlineshopping.api;

import com.ecommerce.onlineshopping.model.LoginRequest;
import com.ecommerce.onlineshopping.model.RegisterUser;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ServiceApi {

    @FormUrlEncoded
    @POST("user/create")
    Call<RegisterUser> doRegister(@Field("userName") String name,
                                  @Field("userNumber") String number,
                                  @Field("userEmail") String email,
                                  @Field("userAddress") String address,
                                  @Field("userPassword") String password);

    @FormUrlEncoded
    @POST("user/login")
    Call<LoginRequest> doLogin(@Field("userNumber") String number,
                               @Field("userPassword") String password);

}



