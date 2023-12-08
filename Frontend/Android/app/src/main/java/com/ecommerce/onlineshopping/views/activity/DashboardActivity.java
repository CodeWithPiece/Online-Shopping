package com.ecommerce.onlineshopping.views.activity;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.views.fragment.CartFragment;
import com.ecommerce.onlineshopping.views.fragment.CategoryFragment;
import com.ecommerce.onlineshopping.views.fragment.FavouriteFragment;
import com.ecommerce.onlineshopping.views.fragment.HomeFragment;
import com.ecommerce.onlineshopping.views.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DashboardActivity extends AppCompatActivity {

    String currentFrag = "HOME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.setItemActiveIndicatorColor(ColorStateList.valueOf(Color.TRANSPARENT));
        bottomNavigation.setItemRippleColor(ColorStateList.valueOf(Color.TRANSPARENT));
        loadFragment(new HomeFragment());
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    currentFrag = "HOME";
                    loadFragment(new HomeFragment());
                } else if (item.getItemId() == R.id.category) {
                    currentFrag = "CATEGORY";
                    loadFragment(new CategoryFragment());
                } else if (item.getItemId() == R.id.favourite) {
                    currentFrag = "FAVOURITE";
                    loadFragment(new FavouriteFragment());
                } else if (item.getItemId() == R.id.cart) {
                    currentFrag = "CART";
                    loadFragment(new CartFragment());
                } else {
                    currentFrag = "PROFILE";
                    loadFragment(new ProfileFragment());
                }
                return true;
            }
        });

        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (currentFrag.equals("HOME")) {
                    finish();
                } else {
                    currentFrag = "HOME";
                    loadFragment(new HomeFragment());
                    bottomNavigation.setSelectedItemId(R.id.home);
                    bottomNavigation.setSelected(true);
                }
            }
        });

    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}