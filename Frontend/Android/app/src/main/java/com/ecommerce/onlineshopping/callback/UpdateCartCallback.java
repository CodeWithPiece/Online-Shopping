package com.ecommerce.onlineshopping.callback;

import com.ecommerce.onlineshopping.model.AddToCartRequest;
import com.ecommerce.onlineshopping.model.UpdateCart;

public interface UpdateCartCallback {

    public void onResponse(UpdateCart updateCart);

    public void onFailure(Throwable throwable);

}
