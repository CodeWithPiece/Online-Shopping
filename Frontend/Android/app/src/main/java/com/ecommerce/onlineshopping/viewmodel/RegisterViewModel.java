package com.ecommerce.onlineshopping.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ecommerce.onlineshopping.callback.RegisterCallback;
import com.ecommerce.onlineshopping.model.RegisterUser;
import com.ecommerce.onlineshopping.repository.ShoppingRepository;

public class RegisterViewModel extends AndroidViewModel {

    private final ShoppingRepository shoppingRepository;
    private final MutableLiveData<RegisterUser> mutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> progressLiveData = new MutableLiveData<>();

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        this.shoppingRepository = new ShoppingRepository(application);
    }

    public void registerUser(String userName, String userNumber, String userEmail
            , String userAddress, String userPassword) {
        progressLiveData.setValue(View.VISIBLE);
        shoppingRepository.doRegister(userName, userNumber, userEmail, userAddress, userPassword, new RegisterCallback() {
            @Override
            public void onResponse(RegisterUser registerUser) {
                progressLiveData.setValue(View.GONE);
                mutableLiveData.setValue(registerUser);
            }

            @Override
            public void onFailure(Throwable throwable) {
                progressLiveData.setValue(View.GONE);
                mutableLiveData.setValue(null);
            }
        });
    }

    public LiveData<RegisterUser> getRegisterData() {
        return mutableLiveData;
    }

    public LiveData<Integer> getProgressData() {
        return progressLiveData;
    }

}
