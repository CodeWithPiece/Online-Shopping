package com.ecommerce.onlineshopping.api;

import com.ecommerce.onlineshopping.model.AddToCartRequest;
import com.ecommerce.onlineshopping.model.CartRequest;
import com.ecommerce.onlineshopping.model.CategoryRequest;
import com.ecommerce.onlineshopping.model.DeleteCart;
import com.ecommerce.onlineshopping.model.LoginRequest;
import com.ecommerce.onlineshopping.model.OrderRequest;
import com.ecommerce.onlineshopping.model.PlaceOrder;
import com.ecommerce.onlineshopping.model.ProductImageRequest;
import com.ecommerce.onlineshopping.model.ProductRequest;
import com.ecommerce.onlineshopping.model.ProductSizeRequest;
import com.ecommerce.onlineshopping.model.RegisterUser;
import com.ecommerce.onlineshopping.model.TrendingProductRequest;
import com.ecommerce.onlineshopping.model.UpdateCart;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ServiceApi {

    @FormUrlEncoded
    @POST("user/create")
    Call<RegisterUser> doRegister(@Field("userName") String name,
                                  @Field("userNumber") String number,
                                  @Field("userEmail") String email,
                                  @Field("userAddress") String address,
                                  @Field("userPassword") String password);

    @FormUrlEncoded
    @POST("user/login")
    Call<LoginRequest> doLogin(@Field("userNumber") String number,
                               @Field("userPassword") String password);

    @GET("category/all")
    Call<CategoryRequest> getCategories();

    @FormUrlEncoded
    @POST("product/category")
    Call<ProductRequest> getProductByCategory(@Field("catId") int catId);

    @FormUrlEncoded
    @POST("image/get")
    Call<ProductImageRequest> getProductImage(@Field("productId") int productId);

    @FormUrlEncoded
    @POST("size/get")
    Call<ProductSizeRequest> getProductSize(@Field("productId") int productId);

    @GET("product/random")
    Call<TrendingProductRequest> getTrendingProducts();

    @FormUrlEncoded
    @POST("cart/add")
    Call<AddToCartRequest> addToCart(@Field("userId") int userId,
                                     @Field("productId") int productId,
                                     @Field("sizeId") int sizeId);

    @FormUrlEncoded
    @POST("cart/get")
    Call<CartRequest> getCart(@Field("userId") int userId);

    @FormUrlEncoded
    @POST("cart/update")
    Call<UpdateCart> updateCart(@Field("productCount") int productCount,
                                @Field("cartId") int cartId);

    @FormUrlEncoded
    @POST("cart/delete")
    Call<DeleteCart> deleteCart(@Field("cartId") int cartId);

    @FormUrlEncoded
    @POST("order/create")
    Call<PlaceOrder> placeOrder(@Field("userId") int userId);

    @FormUrlEncoded
    @POST("order/get")
    Call<OrderRequest> getOrder(@Field("userId") int userId);

}



