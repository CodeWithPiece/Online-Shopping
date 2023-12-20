package com.ecommerce.onlineshopping.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ecommerce.onlineshopping.callback.AddToCartCallback;
import com.ecommerce.onlineshopping.callback.CartCallback;
import com.ecommerce.onlineshopping.model.AddToCartRequest;
import com.ecommerce.onlineshopping.model.CartRequest;
import com.ecommerce.onlineshopping.repository.ShoppingRepository;

public class CartViewModel extends AndroidViewModel {

    private final ShoppingRepository shoppingRepository;
    private final MutableLiveData<CartRequest> mutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> progressLiveData = new MutableLiveData<>();

    public CartViewModel(@NonNull Application application) {
        super(application);
        this.shoppingRepository = new ShoppingRepository(application);
    }

    public void getCart(int userId) {
        progressLiveData.setValue(View.VISIBLE);
        shoppingRepository.getCart(userId, new CartCallback() {
            @Override
            public void onResponse(CartRequest cartRequest) {
                progressLiveData.setValue(View.GONE);
                mutableLiveData.setValue(cartRequest);
            }

            @Override
            public void onFailure(Throwable throwable) {
                progressLiveData.setValue(View.GONE);
                mutableLiveData.setValue(null);
            }
        });
    }

    public LiveData<CartRequest> getCartData() {
        return mutableLiveData;
    }

    public LiveData<Integer> getProgressData() {
        return progressLiveData;
    }

}
