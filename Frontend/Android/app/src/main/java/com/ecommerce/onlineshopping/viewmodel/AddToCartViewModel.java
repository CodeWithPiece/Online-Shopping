package com.ecommerce.onlineshopping.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ecommerce.onlineshopping.callback.AddToCartCallback;
import com.ecommerce.onlineshopping.callback.ProductSizeCallback;
import com.ecommerce.onlineshopping.model.AddToCartRequest;
import com.ecommerce.onlineshopping.model.ProductSizeRequest;
import com.ecommerce.onlineshopping.repository.ShoppingRepository;

public class AddToCartViewModel extends AndroidViewModel {

    private final ShoppingRepository shoppingRepository;
    private final MutableLiveData<AddToCartRequest> mutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> progressLiveData = new MutableLiveData<>();

    public AddToCartViewModel(@NonNull Application application) {
        super(application);
        this.shoppingRepository = new ShoppingRepository(application);
    }

    public void addToCart(int userId, int productId, int sizeId) {
        progressLiveData.setValue(View.VISIBLE);
        shoppingRepository.addToCart(userId, productId, sizeId, new AddToCartCallback() {
            @Override
            public void onResponse(AddToCartRequest addToCartRequest) {
                progressLiveData.setValue(View.GONE);
                mutableLiveData.setValue(addToCartRequest);
            }

            @Override
            public void onFailure(Throwable throwable) {
                progressLiveData.setValue(View.GONE);
                mutableLiveData.setValue(null);
            }
        });
    }

    public LiveData<AddToCartRequest> getAddToCartData() {
        return mutableLiveData;
    }

    public LiveData<Integer> getProgressData() {
        return progressLiveData;
    }

}
