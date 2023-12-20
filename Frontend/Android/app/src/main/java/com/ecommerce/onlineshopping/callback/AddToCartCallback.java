package com.ecommerce.onlineshopping.callback;

import com.ecommerce.onlineshopping.model.AddToCartRequest;
import com.ecommerce.onlineshopping.model.TrendingProductRequest;

public interface AddToCartCallback {

    public void onResponse(AddToCartRequest addToCartRequest);

    public void onFailure(Throwable throwable);

}
