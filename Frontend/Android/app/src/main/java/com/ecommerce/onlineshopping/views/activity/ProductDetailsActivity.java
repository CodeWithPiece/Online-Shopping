package com.ecommerce.onlineshopping.views.activity;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.adapter.ImageSliderAdapter;
import com.ecommerce.onlineshopping.adapter.SizeAdapter;
import com.ecommerce.onlineshopping.model.Product;
import com.google.android.material.appbar.MaterialToolbar;

public class ProductDetailsActivity extends AppCompatActivity {

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            product = (Product) bundle.getSerializable("product");
        }

        MaterialToolbar toolBar = findViewById(R.id.toolBar);
        RecyclerView imageRecycler = findViewById(R.id.imageRecycler);
        RecyclerView sizeRecycler = findViewById(R.id.sizeRecycler);
        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(ProductDetailsActivity.this);
        SizeAdapter sizeAdapter = new SizeAdapter(ProductDetailsActivity.this);
        imageRecycler.setLayoutManager(new LinearLayoutManager(ProductDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
        sizeRecycler.setLayoutManager(new LinearLayoutManager(ProductDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
        imageRecycler.setAdapter(imageSliderAdapter);
        sizeRecycler.setAdapter(sizeAdapter);

        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDetailsActivity.super.onBackPressed();
            }
        });
    }

}