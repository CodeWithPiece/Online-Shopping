package com.ecommerce.onlineshopping.callback;

import com.ecommerce.onlineshopping.model.DeleteCart;
import com.ecommerce.onlineshopping.model.UpdateCart;

public interface DeleteCartCallback {

    public void onResponse(DeleteCart deleteCart);

    public void onFailure(Throwable throwable);

}
