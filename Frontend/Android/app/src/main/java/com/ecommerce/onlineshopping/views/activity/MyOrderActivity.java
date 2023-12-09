package com.ecommerce.onlineshopping.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.adapter.OrderAdapter;
import com.google.android.material.appbar.MaterialToolbar;

public class MyOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        MaterialToolbar toolBar = findViewById(R.id.toolBar);
        RecyclerView orderRecycler = findViewById(R.id.orderRecycler);
        OrderAdapter orderAdapter = new OrderAdapter(MyOrderActivity.this);
        orderRecycler.setLayoutManager(new LinearLayoutManager(MyOrderActivity.this, LinearLayoutManager.VERTICAL, false));
        orderRecycler.setAdapter(orderAdapter);

        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOrderActivity.super.onBackPressed();
            }
        });

    }

    Dialog dialog;

    public void getReviewDialog() {
        dialog = new Dialog(MyOrderActivity.this);
        dialog.setContentView(R.layout.custom_review_dialog_layout);
        dialog.setCancelable(true);

        TextView txtNo = dialog.findViewById(R.id.txtNo);

        txtNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

}