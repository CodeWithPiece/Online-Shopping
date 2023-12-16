package com.ecommerce.onlineshopping.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ecommerce.onlineshopping.callback.ProductCallback;
import com.ecommerce.onlineshopping.callback.TrendingProductCallback;
import com.ecommerce.onlineshopping.model.ProductRequest;
import com.ecommerce.onlineshopping.model.TrendingProductRequest;
import com.ecommerce.onlineshopping.repository.ShoppingRepository;

public class TrendingProductViewModel extends AndroidViewModel {

    private final ShoppingRepository shoppingRepository;
    private final MutableLiveData<TrendingProductRequest> mutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> progressLiveData = new MutableLiveData<>();

    public TrendingProductViewModel(@NonNull Application application) {
        super(application);
        this.shoppingRepository = new ShoppingRepository(application);
    }

    public void getTrendingProduct() {
        progressLiveData.setValue(View.VISIBLE);
        shoppingRepository.getTrendingProducts(new TrendingProductCallback() {
            @Override
            public void onResponse(TrendingProductRequest trendingProductRequest) {
                progressLiveData.setValue(View.GONE);
                mutableLiveData.setValue(trendingProductRequest);
            }

            @Override
            public void onFailure(Throwable throwable) {
                progressLiveData.setValue(View.GONE);
                mutableLiveData.setValue(null);
            }
        });
    }

    public LiveData<TrendingProductRequest> getTrendingProductData() {
        return mutableLiveData;
    }

    public LiveData<Integer> getProgressData() {
        return progressLiveData;
    }

}
