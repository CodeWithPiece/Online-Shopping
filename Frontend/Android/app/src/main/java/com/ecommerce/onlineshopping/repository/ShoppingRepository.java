package com.ecommerce.onlineshopping.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.ecommerce.onlineshopping.api.ApiClient;
import com.ecommerce.onlineshopping.api.ServiceApi;
import com.ecommerce.onlineshopping.callback.AddToCartCallback;
import com.ecommerce.onlineshopping.callback.CartCallback;
import com.ecommerce.onlineshopping.callback.CategoryCallback;
import com.ecommerce.onlineshopping.callback.DeleteCartCallback;
import com.ecommerce.onlineshopping.callback.LoginCallback;
import com.ecommerce.onlineshopping.callback.PlaceOrderCallback;
import com.ecommerce.onlineshopping.callback.ProductCallback;
import com.ecommerce.onlineshopping.callback.ProductImageCallback;
import com.ecommerce.onlineshopping.callback.ProductSizeCallback;
import com.ecommerce.onlineshopping.callback.RegisterCallback;
import com.ecommerce.onlineshopping.callback.TrendingProductCallback;
import com.ecommerce.onlineshopping.callback.UpdateCartCallback;
import com.ecommerce.onlineshopping.model.AddToCartRequest;
import com.ecommerce.onlineshopping.model.CartRequest;
import com.ecommerce.onlineshopping.model.CategoryRequest;
import com.ecommerce.onlineshopping.model.DeleteCart;
import com.ecommerce.onlineshopping.model.LoginRequest;
import com.ecommerce.onlineshopping.model.PlaceOrder;
import com.ecommerce.onlineshopping.model.ProductImageRequest;
import com.ecommerce.onlineshopping.model.ProductRequest;
import com.ecommerce.onlineshopping.model.ProductSize;
import com.ecommerce.onlineshopping.model.ProductSizeRequest;
import com.ecommerce.onlineshopping.model.RegisterUser;
import com.ecommerce.onlineshopping.model.TrendingProductRequest;
import com.ecommerce.onlineshopping.model.UpdateCart;
import com.ecommerce.onlineshopping.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingRepository {
    private final Application application;

    public ShoppingRepository(Application application) {
        this.application = application;
    }

    public void doRegister(String userName, String userNumber, String userEmail
            , String userAddress, String userPassword, RegisterCallback registerCallback) {
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<RegisterUser> call = api.doRegister(userName, userNumber, userEmail, userAddress, userPassword);
        call.enqueue(new Callback<RegisterUser>() {
            @Override
            public void onResponse(Call<RegisterUser> call, Response<RegisterUser> response) {
                if (response.isSuccessful()) {
                    RegisterUser registerUser = response.body();
                    registerCallback.onResponse(registerUser);
                } else {
                    registerCallback.onFailure(new Throwable("Response Failure"));
                }
            }

            @Override
            public void onFailure(Call<RegisterUser> call, Throwable t) {
                registerCallback.onFailure(t);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }

    public void doLogin(String number, String password, LoginCallback loginCallback) {
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<LoginRequest> call = api.doLogin(number, password);
        call.enqueue(new Callback<LoginRequest>() {
            @Override
            public void onResponse(Call<LoginRequest> call, Response<LoginRequest> response) {
                if (response.isSuccessful()) {
                    LoginRequest loginRequest = response.body();
                    loginCallback.onResponse(loginRequest);
                } else {
                    loginCallback.onFailure(new Throwable("Response Failure"));
                }
            }

            @Override
            public void onFailure(Call<LoginRequest> call, Throwable t) {
                loginCallback.onFailure(t);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }

    public void getCategories(CategoryCallback categoryCallback) {
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<CategoryRequest> call = api.getCategories();
        call.enqueue(new Callback<CategoryRequest>() {
            @Override
            public void onResponse(Call<CategoryRequest> call, Response<CategoryRequest> response) {
                if (response.isSuccessful()) {
                    CategoryRequest categoryRequest = response.body();
                    categoryCallback.onResponse(categoryRequest);
                } else {
                    categoryCallback.onFailure(new Throwable("Response Failure"));
                }
            }

            @Override
            public void onFailure(Call<CategoryRequest> call, Throwable t) {
                categoryCallback.onFailure(t);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }

    public void getProductByCategory(int catId, ProductCallback productCallback) {
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<ProductRequest> call = api.getProductByCategory(catId);
        call.enqueue(new Callback<ProductRequest>() {
            @Override
            public void onResponse(Call<ProductRequest> call, Response<ProductRequest> response) {
                if (response.isSuccessful()) {
                    ProductRequest productRequest = response.body();
                    productCallback.onResponse(productRequest);
                } else {
                    productCallback.onFailure(new Throwable("Response Failure"));
                }
            }

            @Override
            public void onFailure(Call<ProductRequest> call, Throwable t) {
                productCallback.onFailure(t);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }

    public void getProductImage(int productId, ProductImageCallback productImageCallback) {
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<ProductImageRequest> call = api.getProductImage(productId);
        call.enqueue(new Callback<ProductImageRequest>() {
            @Override
            public void onResponse(Call<ProductImageRequest> call, Response<ProductImageRequest> response) {
                if (response.isSuccessful()) {
                    ProductImageRequest productImageRequest = response.body();
                    productImageCallback.onResponse(productImageRequest);
                } else {
                    productImageCallback.onFailure(new Throwable("Response Failure"));
                }
            }

            @Override
            public void onFailure(Call<ProductImageRequest> call, Throwable t) {
                productImageCallback.onFailure(t);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }

    public void getProductSize(int productId, ProductSizeCallback productSizeCallback) {
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<ProductSizeRequest> call = api.getProductSize(productId);
        call.enqueue(new Callback<ProductSizeRequest>() {
            @Override
            public void onResponse(Call<ProductSizeRequest> call, Response<ProductSizeRequest> response) {
                if (response.isSuccessful()) {
                    ProductSizeRequest productSizeRequest = response.body();
                    productSizeCallback.onResponse(productSizeRequest);
                } else {
                    productSizeCallback.onFailure(new Throwable("Response Failure"));
                }
            }

            @Override
            public void onFailure(Call<ProductSizeRequest> call, Throwable t) {
                productSizeCallback.onFailure(t);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }

    public void getTrendingProducts(TrendingProductCallback trendingProductCallback) {
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<TrendingProductRequest> call = api.getTrendingProducts();
        call.enqueue(new Callback<TrendingProductRequest>() {
            @Override
            public void onResponse(Call<TrendingProductRequest> call, Response<TrendingProductRequest> response) {
                if (response.isSuccessful()) {
                    TrendingProductRequest trendingProductRequest = response.body();
                    trendingProductCallback.onResponse(trendingProductRequest);
                } else {
                    trendingProductCallback.onFailure(new Throwable("Response Failure"));
                }
            }

            @Override
            public void onFailure(Call<TrendingProductRequest> call, Throwable t) {
                trendingProductCallback.onFailure(t);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }

    public void addToCart(int userId, int productId, int sizeId, AddToCartCallback addToCartCallback) {
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<AddToCartRequest> call = api.addToCart(userId, productId, sizeId);
        call.enqueue(new Callback<AddToCartRequest>() {
            @Override
            public void onResponse(Call<AddToCartRequest> call, Response<AddToCartRequest> response) {
                if (response.isSuccessful()) {
                    AddToCartRequest addToCartRequest = response.body();
                    addToCartCallback.onResponse(addToCartRequest);
                } else {
                    addToCartCallback.onFailure(new Throwable("Response Failure"));
                }
            }

            @Override
            public void onFailure(Call<AddToCartRequest> call, Throwable t) {
                addToCartCallback.onFailure(t);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }

    public void getCart(int userId, CartCallback cartCallback) {
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<CartRequest> call = api.getCart(userId);
        call.enqueue(new Callback<CartRequest>() {
            @Override
            public void onResponse(Call<CartRequest> call, Response<CartRequest> response) {
                if (response.isSuccessful()) {
                    CartRequest cartRequest = response.body();
                    cartCallback.onResponse(cartRequest);
                } else {
                    cartCallback.onFailure(new Throwable("Response Failure"));
                }
            }

            @Override
            public void onFailure(Call<CartRequest> call, Throwable t) {
                cartCallback.onFailure(t);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }

    public void updateCart(int productCount, int cartId, UpdateCartCallback updateCartCallback) {
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<UpdateCart> call = api.updateCart(productCount, cartId);
        call.enqueue(new Callback<UpdateCart>() {
            @Override
            public void onResponse(Call<UpdateCart> call, Response<UpdateCart> response) {
                if (response.isSuccessful()) {
                    UpdateCart updateCart = response.body();
                    updateCartCallback.onResponse(updateCart);
                } else {
                    updateCartCallback.onFailure(new Throwable("Response Failure"));
                }
            }

            @Override
            public void onFailure(Call<UpdateCart> call, Throwable t) {
                updateCartCallback.onFailure(t);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }

    public void deleteCart(int cartId, DeleteCartCallback deleteCartCallback) {
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<DeleteCart> call = api.deleteCart(cartId);
        call.enqueue(new Callback<DeleteCart>() {
            @Override
            public void onResponse(Call<DeleteCart> call, Response<DeleteCart> response) {
                if (response.isSuccessful()) {
                    DeleteCart deleteCart = response.body();
                    deleteCartCallback.onResponse(deleteCart);
                } else {
                    deleteCartCallback.onFailure(new Throwable("Response Failure"));
                }
            }

            @Override
            public void onFailure(Call<DeleteCart> call, Throwable t) {
                deleteCartCallback.onFailure(t);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }

    public void placeOrder(int userId, PlaceOrderCallback placeOrderCallback) {
        ServiceApi api = ApiClient.getClient().create(ServiceApi.class);
        Call<PlaceOrder> call = api.placeOrder(userId);
        call.enqueue(new Callback<PlaceOrder>() {
            @Override
            public void onResponse(Call<PlaceOrder> call, Response<PlaceOrder> response) {
                if (response.isSuccessful()) {
                    PlaceOrder placeOrder = response.body();
                    placeOrderCallback.onResponse(placeOrder);
                } else {
                    placeOrderCallback.onFailure(new Throwable("Response Failure"));
                }
            }

            @Override
            public void onFailure(Call<PlaceOrder> call, Throwable t) {
                placeOrderCallback.onFailure(t);
                Log.e("EXCEPTION", t.getLocalizedMessage());
            }
        });
    }

}
