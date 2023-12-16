package com.ecommerce.onlineshopping.callback;

import com.ecommerce.onlineshopping.model.ProductImageRequest;
import com.ecommerce.onlineshopping.model.ProductSizeRequest;

public interface ProductSizeCallback {

    public void onResponse(ProductSizeRequest productSizeRequest);

    public void onFailure(Throwable throwable);

}
