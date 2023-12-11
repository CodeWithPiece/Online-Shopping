package com.ecommerce.onlineshopping.callback;

import com.ecommerce.onlineshopping.model.LoginRequest;
import com.ecommerce.onlineshopping.model.User;

public interface LoginCallback {

    public void onResponse(LoginRequest loginRequest);

    public void onFailure(Throwable throwable);

}
