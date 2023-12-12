package com.ecommerce.onlineshopping.views.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.utils.MyPreferences;

public class MainActivity extends AppCompatActivity {

    MyPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPreferences = MyPreferences.getInstance(MainActivity.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (myPreferences.getUserId() != 0) {
                    startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                    finishAffinity();
                } else {
                    startActivity(new Intent(MainActivity.this, OnBoardingActivity.class));
                    finishAffinity();
                }
            }
        }, 2500);

    }
}