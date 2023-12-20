package com.ecommerce.onlineshopping.callback;

import com.ecommerce.onlineshopping.model.AddToCartRequest;
import com.ecommerce.onlineshopping.model.CartRequest;

public interface CartCallback {

    public void onResponse(CartRequest cartRequest);

    public void onFailure(Throwable throwable);

}
