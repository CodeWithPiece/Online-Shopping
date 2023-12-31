package com.ecommerce.onlineshopping.views.activity;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.adapter.ImageSliderAdapter;
import com.ecommerce.onlineshopping.adapter.SizeAdapter;
import com.ecommerce.onlineshopping.model.AddToCartRequest;
import com.ecommerce.onlineshopping.model.Product;
import com.ecommerce.onlineshopping.model.ProductImage;
import com.ecommerce.onlineshopping.model.ProductImageRequest;
import com.ecommerce.onlineshopping.model.ProductSize;
import com.ecommerce.onlineshopping.model.ProductSizeRequest;
import com.ecommerce.onlineshopping.utils.Constant;
import com.ecommerce.onlineshopping.utils.MyPreferences;
import com.ecommerce.onlineshopping.viewmodel.AddToCartViewModel;
import com.ecommerce.onlineshopping.viewmodel.ProductImageViewModel;
import com.ecommerce.onlineshopping.viewmodel.ProductSizeViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

    ProductImageViewModel productImageViewModel;
    ProductSizeViewModel productSizeViewModel;
    AddToCartViewModel addToCartViewModel;
    public ImageView imgProduct;
    Product product;
    public int sizeId;
    List<ProductImage> productImageList = new ArrayList<>();
    List<ProductSize> productSizeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        MaterialToolbar toolBar = findViewById(R.id.toolBar);
        TextView txtProductName = findViewById(R.id.txtProductName);
        imgProduct = findViewById(R.id.imgProduct);
        RatingBar rating = findViewById(R.id.rating);
        TextView txtRating = findViewById(R.id.txtRating);
        TextView txtProductDesc = findViewById(R.id.txtProductDesc);
        TextView txtProductPrice = findViewById(R.id.txtProductPrice);
        RecyclerView imageRecycler = findViewById(R.id.imageRecycler);
        RecyclerView sizeRecycler = findViewById(R.id.sizeRecycler);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        MaterialCardView cardAdd = findViewById(R.id.cardAdd);
        productImageViewModel = new ViewModelProvider(this).get(ProductImageViewModel.class);
        productSizeViewModel = new ViewModelProvider(this).get(ProductSizeViewModel.class);
        addToCartViewModel = new ViewModelProvider(this).get(AddToCartViewModel.class);
        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(ProductDetailsActivity.this, productImageList);
        SizeAdapter sizeAdapter = new SizeAdapter(ProductDetailsActivity.this, productSizeList);
        imageRecycler.setLayoutManager(new LinearLayoutManager(ProductDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
        sizeRecycler.setLayoutManager(new LinearLayoutManager(ProductDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
        imageRecycler.setAdapter(imageSliderAdapter);
        sizeRecycler.setAdapter(sizeAdapter);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            product = (Product) bundle.getSerializable("product");
            Glide.with(ProductDetailsActivity.this).load(Constant.IMAGE_URL + product.getProductImage()).into(imgProduct);
            txtProductName.setText(product.getProductName());
            txtProductDesc.setText(product.getProductDesc());
            txtRating.setText(product.getProductRating() + " rating");
            rating.setRating((float) Double.parseDouble(product.getProductRating()));
            txtProductPrice.setText("₹ " + product.getProductPrice());
            productImageViewModel.getProductImage(product.getProductId());
            productSizeViewModel.getProductSize(product.getProductId());
        }

        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDetailsActivity.super.onBackPressed();
            }
        });

        productImageViewModel.getProgressData().observe(ProductDetailsActivity.this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBar.setVisibility(integer);
            }
        });

        productSizeViewModel.getProgressData().observe(ProductDetailsActivity.this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBar.setVisibility(integer);
            }
        });

        addToCartViewModel.getProgressData().observe(ProductDetailsActivity.this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBar.setVisibility(integer);
            }
        });

        productImageViewModel.getProductImageData().observe(ProductDetailsActivity.this, new Observer<ProductImageRequest>() {
            @Override
            public void onChanged(ProductImageRequest productImageRequest) {
                if (productImageRequest != null) {
                    productImageList.addAll(productImageRequest.getProductImage());
                    imageSliderAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ProductDetailsActivity.this, "Product image not found...!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        productSizeViewModel.getProductSizeData().observe(ProductDetailsActivity.this, new Observer<ProductSizeRequest>() {
            @Override
            public void onChanged(ProductSizeRequest productSizeRequest) {
                if (productSizeRequest != null) {
                    productSizeList.addAll(productSizeRequest.getProductSize());
                    sizeAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ProductDetailsActivity.this, "Product size not found...!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addToCartViewModel.getAddToCartData().observe(ProductDetailsActivity.this, new Observer<AddToCartRequest>() {
            @Override
            public void onChanged(AddToCartRequest addToCartRequest) {
                if (addToCartRequest != null) {
                    Toast.makeText(ProductDetailsActivity.this, "" + addToCartRequest.getMessage(), Toast.LENGTH_SHORT).show();
                    getOnBackPressedDispatcher().onBackPressed();
                } else {
                    Toast.makeText(ProductDetailsActivity.this, "Something went wrong...!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cardAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = MyPreferences.getInstance(ProductDetailsActivity.this).getUserId();
                int productId = product.getProductId();
                Log.e("USER ID", userId + "");
                Log.e("PRODUCT ID", productId + "");
                Log.e("SIZE ID", sizeId + "");
                addToCartViewModel.addToCart(userId, productId, sizeId);
            }
        });

    }

}