package com.ecommerce.onlineshopping.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ecommerce.onlineshopping.api.ApiClient;
import com.ecommerce.onlineshopping.api.ServiceApi;
import com.ecommerce.onlineshopping.model.RegisterUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingRepository {

    private RegisterUser registerUser;

    public RegisterUser doRegister(String userName, String userNumber, String userEmail
            , String userAddress, String userPassword) {
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<RegisterUser> call = api.doRegister(userName, userNumber, userEmail, userAddress, userPassword);
        call.enqueue(new Callback<RegisterUser>() {
            @Override
            public void onResponse(Call<RegisterUser> call, Response<RegisterUser> response) {
                RegisterUser user = response.body();
                if (user != null && user.getStatus()) {
                    registerUser = response.body();
                    Log.e("USER", registerUser.getMessage() + "");
                }
            }

            @Override
            public void onFailure(Call<RegisterUser> call, Throwable t) {
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
        return registerUser;
    }

}
