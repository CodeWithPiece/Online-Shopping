package com.ecommerce.onlineshopping.callback;

import com.ecommerce.onlineshopping.model.ProductImageRequest;
import com.ecommerce.onlineshopping.model.ProductRequest;

public interface ProductImageCallback {

    public void onResponse(ProductImageRequest productImageRequest);

    public void onFailure(Throwable throwable);

}
