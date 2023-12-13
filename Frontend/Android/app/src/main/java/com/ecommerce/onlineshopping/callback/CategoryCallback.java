package com.ecommerce.onlineshopping.callback;

import com.ecommerce.onlineshopping.model.CategoryRequest;
import com.ecommerce.onlineshopping.model.RegisterUser;

public interface CategoryCallback {

    public void onResponse(CategoryRequest categoryRequest);

    public void onFailure(Throwable throwable);

}
