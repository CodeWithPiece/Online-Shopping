package com.ecommerce.onlineshopping.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ecommerce.onlineshopping.callback.ProductImageCallback;
import com.ecommerce.onlineshopping.callback.ProductSizeCallback;
import com.ecommerce.onlineshopping.model.ProductImageRequest;
import com.ecommerce.onlineshopping.model.ProductSizeRequest;
import com.ecommerce.onlineshopping.repository.ShoppingRepository;

public class ProductSizeViewModel extends AndroidViewModel {

    private final ShoppingRepository shoppingRepository;
    private final MutableLiveData<ProductSizeRequest> mutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> progressLiveData = new MutableLiveData<>();

    public ProductSizeViewModel(@NonNull Application application) {
        super(application);
        this.shoppingRepository = new ShoppingRepository(application);
    }

    public void getProductSize(int productId) {
        progressLiveData.setValue(View.VISIBLE);
        shoppingRepository.getProductSize(productId, new ProductSizeCallback() {
            @Override
            public void onResponse(ProductSizeRequest productSizeRequest) {
                progressLiveData.setValue(View.GONE);
                mutableLiveData.setValue(productSizeRequest);
            }

            @Override
            public void onFailure(Throwable throwable) {
                progressLiveData.setValue(View.GONE);
                mutableLiveData.setValue(null);
            }
        });
    }

    public LiveData<ProductSizeRequest> getProductSizeData() {
        return mutableLiveData;
    }

    public LiveData<Integer> getProgressData() {
        return progressLiveData;
    }

}
