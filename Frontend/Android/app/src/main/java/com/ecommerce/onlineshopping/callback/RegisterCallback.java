package com.ecommerce.onlineshopping.callback;

import com.ecommerce.onlineshopping.model.RegisterUser;

public interface RegisterCallback {

    public void onResponse(RegisterUser registerUser);

    public void onFailure(Throwable throwable);

}
