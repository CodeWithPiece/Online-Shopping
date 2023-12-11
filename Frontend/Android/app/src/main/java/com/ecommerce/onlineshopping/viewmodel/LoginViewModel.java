package com.ecommerce.onlineshopping.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ecommerce.onlineshopping.callback.LoginCallback;
import com.ecommerce.onlineshopping.callback.RegisterCallback;
import com.ecommerce.onlineshopping.model.LoginRequest;
import com.ecommerce.onlineshopping.model.RegisterUser;
import com.ecommerce.onlineshopping.model.User;
import com.ecommerce.onlineshopping.repository.ShoppingRepository;

public class LoginViewModel extends AndroidViewModel {

    private final ShoppingRepository shoppingRepository;
    private final MutableLiveData<LoginRequest> mutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> progressLiveData = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.shoppingRepository = new ShoppingRepository(application);
    }

    public void doLogin(String userNumber, String userPassword) {
        progressLiveData.setValue(View.VISIBLE);
        shoppingRepository.doLogin(userNumber, userPassword, new LoginCallback() {
            @Override
            public void onResponse(LoginRequest loginRequest) {
                progressLiveData.setValue(View.GONE);
                mutableLiveData.setValue(loginRequest);
            }

            @Override
            public void onFailure(Throwable throwable) {
                progressLiveData.setValue(View.GONE);
                mutableLiveData.setValue(null);
            }
        });
    }

    public LiveData<LoginRequest> getUserData() {
        return mutableLiveData;
    }

    public LiveData<Integer> getProgressData() {
        return progressLiveData;
    }

}
