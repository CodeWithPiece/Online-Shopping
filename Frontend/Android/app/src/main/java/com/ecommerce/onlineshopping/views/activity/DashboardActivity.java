package com.ecommerce.onlineshopping.views.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import com.ecommerce.onlineshopping.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.setItemActiveIndicatorColor(ColorStateList.valueOf(Color.TRANSPARENT));
        bottomNavigation.setItemRippleColor(ColorStateList.valueOf(Color.TRANSPARENT));

    }
}