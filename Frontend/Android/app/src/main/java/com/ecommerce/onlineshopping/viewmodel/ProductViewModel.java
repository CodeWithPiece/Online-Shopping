package com.ecommerce.onlineshopping.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ecommerce.onlineshopping.callback.CategoryCallback;
import com.ecommerce.onlineshopping.callback.ProductCallback;
import com.ecommerce.onlineshopping.model.CategoryRequest;
import com.ecommerce.onlineshopping.model.ProductRequest;
import com.ecommerce.onlineshopping.repository.ShoppingRepository;

public class ProductViewModel extends AndroidViewModel {

    private final ShoppingRepository shoppingRepository;
    private final MutableLiveData<ProductRequest> mutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> progressLiveData = new MutableLiveData<>();

    public ProductViewModel(@NonNull Application application) {
        super(application);
        this.shoppingRepository = new ShoppingRepository(application);
    }

    public void getProductByCategory(int catId) {
        progressLiveData.setValue(View.VISIBLE);
        shoppingRepository.getProductByCategory(catId, new ProductCallback() {
            @Override
            public void onResponse(ProductRequest productRequest) {
                progressLiveData.setValue(View.GONE);
                mutableLiveData.setValue(productRequest);
            }

            @Override
            public void onFailure(Throwable throwable) {
                progressLiveData.setValue(View.GONE);
                mutableLiveData.setValue(null);
            }
        });
    }

    public LiveData<ProductRequest> getProductData() {
        return mutableLiveData;
    }

    public LiveData<Integer> getProgressData() {
        return progressLiveData;
    }

}
