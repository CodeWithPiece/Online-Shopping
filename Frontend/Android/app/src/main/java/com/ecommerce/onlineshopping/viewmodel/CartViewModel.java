package com.ecommerce.onlineshopping.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ecommerce.onlineshopping.callback.AddToCartCallback;
import com.ecommerce.onlineshopping.callback.CartCallback;
import com.ecommerce.onlineshopping.callback.DeleteCartCallback;
import com.ecommerce.onlineshopping.callback.UpdateCartCallback;
import com.ecommerce.onlineshopping.model.AddToCartRequest;
import com.ecommerce.onlineshopping.model.CartRequest;
import com.ecommerce.onlineshopping.model.DeleteCart;
import com.ecommerce.onlineshopping.model.UpdateCart;
import com.ecommerce.onlineshopping.repository.ShoppingRepository;

public class CartViewModel extends AndroidViewModel {

    private final ShoppingRepository shoppingRepository;
    private final MutableLiveData<CartRequest> mutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<UpdateCart> updateCartLiveData = new MutableLiveData<>();
    private final MutableLiveData<DeleteCart> deleteCartLiveData = new MutableLiveData<>();
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

    public void updateCart(int productCount, int cartId) {
        progressLiveData.setValue(View.VISIBLE);
        shoppingRepository.updateCart(productCount, cartId, new UpdateCartCallback() {
            @Override
            public void onResponse(UpdateCart updateCart) {
                progressLiveData.setValue(View.GONE);
                updateCartLiveData.setValue(updateCart);
            }

            @Override
            public void onFailure(Throwable throwable) {
                progressLiveData.setValue(View.GONE);
                updateCartLiveData.setValue(null);
            }
        });
    }

    public void deleteCart(int cartId) {
        progressLiveData.setValue(View.VISIBLE);
        shoppingRepository.deleteCart(cartId, new DeleteCartCallback() {
            @Override
            public void onResponse(DeleteCart deleteCart) {
                progressLiveData.setValue(View.GONE);
                deleteCartLiveData.setValue(deleteCart);
            }

            @Override
            public void onFailure(Throwable throwable) {
                progressLiveData.setValue(View.GONE);
                deleteCartLiveData.setValue(null);
            }
        });
    }

    public LiveData<CartRequest> getCartData() {
        return mutableLiveData;
    }

    public LiveData<UpdateCart> updateCartData() {
        return updateCartLiveData;
    }

    public LiveData<DeleteCart> deleteCartData() {
        return deleteCartLiveData;
    }

    public LiveData<Integer> getProgressData() {
        return progressLiveData;
    }

}
