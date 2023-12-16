package com.ecommerce.onlineshopping.callback;

import com.ecommerce.onlineshopping.model.ProductRequest;
import com.ecommerce.onlineshopping.model.TrendingProductRequest;

public interface TrendingProductCallback {

    public void onResponse(TrendingProductRequest trendingProductRequest);

    public void onFailure(Throwable throwable);

}
