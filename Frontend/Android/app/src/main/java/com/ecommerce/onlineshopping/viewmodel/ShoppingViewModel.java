package com.ecommerce.onlineshopping.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ecommerce.onlineshopping.model.RegisterUser;
import com.ecommerce.onlineshopping.repository.ShoppingRepository;

public class ShoppingViewModel extends AndroidViewModel {

    private final ShoppingRepository shoppingRepository;

    public ShoppingViewModel(@NonNull Application application) {
        super(application);
        this.shoppingRepository = new ShoppingRepository();
    }

    public RegisterUser registerUser(String userName, String userNumber, String userEmail
            , String userAddress, String userPassword) {
        return shoppingRepository.doRegister(userName, userNumber, userEmail, userAddress, userPassword);
    }

}
