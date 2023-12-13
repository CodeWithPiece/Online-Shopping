package com.ecommerce.onlineshopping.callback;

import com.ecommerce.onlineshopping.model.CategoryRequest;
import com.ecommerce.onlineshopping.model.ProductRequest;

public interface ProductCallback {

    public void onResponse(ProductRequest productRequest);

    public void onFailure(Throwable throwable);

}
