package com.ecommerce.onlineshopping.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ecommerce.onlineshopping.callback.CategoryCallback;
import com.ecommerce.onlineshopping.callback.RegisterCallback;
import com.ecommerce.onlineshopping.model.CategoryRequest;
import com.ecommerce.onlineshopping.model.RegisterUser;
import com.ecommerce.onlineshopping.repository.ShoppingRepository;

public class CategoryViewModel extends AndroidViewModel {

    private final ShoppingRepository shoppingRepository;
    private final MutableLiveData<CategoryRequest> mutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> progressLiveData = new MutableLiveData<>();

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        this.shoppingRepository = new ShoppingRepository(application);
    }

    public void getCategories() {
        progressLiveData.setValue(View.VISIBLE);
        shoppingRepository.getCategories(new CategoryCallback() {
            @Override
            public void onResponse(CategoryRequest categoryRequest) {
                progressLiveData.setValue(View.GONE);
                mutableLiveData.setValue(categoryRequest);
            }

            @Override
            public void onFailure(Throwable throwable) {
                progressLiveData.setValue(View.GONE);
                mutableLiveData.setValue(null);
            }
        });
    }

    public LiveData<CategoryRequest> getCategoryData() {
        return mutableLiveData;
    }

    public LiveData<Integer> getProgressData() {
        return progressLiveData;
    }

}
