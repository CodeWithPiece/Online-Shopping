package com.ecommerce.onlineshopping.views.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.ecommerce.onlineshopping.R;
import com.google.android.material.appbar.MaterialToolbar;

public class CheckOutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        MaterialToolbar toolBar = findViewById(R.id.toolBar);

        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckOutActivity.super.onBackPressed();
            }
        });

    }
}