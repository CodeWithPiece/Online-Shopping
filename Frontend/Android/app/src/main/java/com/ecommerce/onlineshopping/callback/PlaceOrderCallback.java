package com.ecommerce.onlineshopping.callback;

import com.ecommerce.onlineshopping.model.CartRequest;
import com.ecommerce.onlineshopping.model.PlaceOrder;

public interface PlaceOrderCallback {

    public void onResponse(PlaceOrder placeOrder);

    public void onFailure(Throwable throwable);

}
