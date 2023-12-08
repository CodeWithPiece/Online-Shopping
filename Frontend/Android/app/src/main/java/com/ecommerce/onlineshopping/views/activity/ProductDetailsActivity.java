package com.ecommerce.onlineshopping.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.adapter.ImageSliderAdapter;
import com.ecommerce.onlineshopping.adapter.SizeAdapter;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        RecyclerView imageRecycler = findViewById(R.id.imageRecycler);
        RecyclerView sizeRecycler = findViewById(R.id.sizeRecycler);
        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(ProductDetailsActivity.this);
        SizeAdapter sizeAdapter = new SizeAdapter(ProductDetailsActivity.this);
        imageRecycler.setLayoutManager(new LinearLayoutManager(ProductDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
        sizeRecycler.setLayoutManager(new LinearLayoutManager(ProductDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
        imageRecycler.setAdapter(imageSliderAdapter);
        sizeRecycler.setAdapter(sizeAdapter);

    }
}