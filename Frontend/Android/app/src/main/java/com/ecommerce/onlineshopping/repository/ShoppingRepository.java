package com.ecommerce.onlineshopping.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.ecommerce.onlineshopping.api.ApiClient;
import com.ecommerce.onlineshopping.api.ServiceApi;
import com.ecommerce.onlineshopping.callback.RegisterCallback;
import com.ecommerce.onlineshopping.model.RegisterUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingRepository {
    private final Application application;

    public ShoppingRepository(Application application) {
        this.application = application;
    }

    public void doRegister(String userName, String userNumber, String userEmail
            , String userAddress, String userPassword, RegisterCallback registerCallback) {
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<RegisterUser> call = api.doRegister(userName, userNumber, userEmail, userAddress, userPassword);
        call.enqueue(new Callback<RegisterUser>() {
            @Override
            public void onResponse(Call<RegisterUser> call, Response<RegisterUser> response) {
                if (response.isSuccessful()) {
                    RegisterUser registerUser = response.body();
                    registerCallback.onResponse(registerUser);
                } else {
                    registerCallback.onFailure(new Throwable("Response Failure"));
                }
            }

            @Override
            public void onFailure(Call<RegisterUser> call, Throwable t) {
                registerCallback.onFailure(t);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }

}
