package com.ecommerce.onlineshopping.callback;

import com.ecommerce.onlineshopping.model.OrderRequest;
import com.ecommerce.onlineshopping.model.PlaceOrder;

public interface OrderCallback {

    public void onResponse(OrderRequest orderRequest);

    public void onFailure(Throwable throwable);

}
