package com.ecommerce.onlineshopping.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ecommerce.onlineshopping.callback.CartCallback;
import com.ecommerce.onlineshopping.callback.DeleteCartCallback;
import com.ecommerce.onlineshopping.callback.OrderCallback;
import com.ecommerce.onlineshopping.callback.PlaceOrderCallback;
import com.ecommerce.onlineshopping.callback.UpdateCartCallback;
import com.ecommerce.onlineshopping.model.CartRequest;
import com.ecommerce.onlineshopping.model.DeleteCart;
import com.ecommerce.onlineshopping.model.OrderRequest;
import com.ecommerce.onlineshopping.model.PlaceOrder;
import com.ecommerce.onlineshopping.model.UpdateCart;
import com.ecommerce.onlineshopping.repository.ShoppingRepository;

public class OrderViewModel extends AndroidViewModel {

    private final ShoppingRepository shoppingRepository;
    private final MutableLiveData<PlaceOrder> mutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<OrderRequest> orderLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> progressLiveData = new MutableLiveData<>();

    public OrderViewModel(@NonNull Application application) {
        super(application);
        this.shoppingRepository = new ShoppingRepository(application);
    }

    public void placeOrder(int userId) {
        progressLiveData.setValue(View.VISIBLE);
        shoppingRepository.placeOrder(userId, new PlaceOrderCallback() {
            @Override
            public void onResponse(PlaceOrder placeOrder) {
                progressLiveData.setValue(View.GONE);
                mutableLiveData.setValue(placeOrder);
            }

            @Override
            public void onFailure(Throwable throwable) {
                progressLiveData.setValue(View.GONE);
                mutableLiveData.setValue(null);
            }
        });
    }

    public void getOrder(int userId) {
        progressLiveData.setValue(View.VISIBLE);
        shoppingRepository.getOrder(userId, new OrderCallback() {
            @Override
            public void onResponse(OrderRequest orderRequest) {
                progressLiveData.setValue(View.GONE);
                orderLiveData.setValue(orderRequest);
            }

            @Override
            public void onFailure(Throwable throwable) {
                progressLiveData.setValue(View.GONE);
                orderLiveData.setValue(null);
            }
        });
    }

    public LiveData<PlaceOrder> getOrderData() {
        return mutableLiveData;
    }

    public LiveData<OrderRequest> getOrderLiveData() {
        return orderLiveData;
    }

    public LiveData<Integer> getProgressData() {
        return progressLiveData;
    }

}
