package com.ecommerce.onlineshopping.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.adapter.OrderAdapter;
import com.ecommerce.onlineshopping.model.Order;
import com.ecommerce.onlineshopping.model.OrderRequest;
import com.ecommerce.onlineshopping.model.PlaceOrder;
import com.ecommerce.onlineshopping.utils.MyPreferences;
import com.ecommerce.onlineshopping.viewmodel.CartViewModel;
import com.ecommerce.onlineshopping.viewmodel.OrderViewModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends AppCompatActivity {

    OrderViewModel orderViewModel;
    List<Order> orderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        MaterialToolbar toolBar = findViewById(R.id.toolBar);
        RecyclerView orderRecycler = findViewById(R.id.orderRecycler);
        ProgressBar progress = findViewById(R.id.progress);
        MyPreferences myPreferences = MyPreferences.getInstance(MyOrderActivity.this);
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        OrderAdapter orderAdapter = new OrderAdapter(MyOrderActivity.this, orderList);
        orderRecycler.setLayoutManager(new LinearLayoutManager(MyOrderActivity.this, LinearLayoutManager.VERTICAL, false));
        orderRecycler.setAdapter(orderAdapter);
        orderViewModel.getOrder(myPreferences.getUserId());

        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        orderViewModel.getProgressData().observe(MyOrderActivity.this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progress.setVisibility(integer);
            }
        });

        orderViewModel.getOrderLiveData().observe(MyOrderActivity.this, new Observer<OrderRequest>() {
            @Override
            public void onChanged(OrderRequest orderRequest) {
                if (orderRequest != null) {
                    orderList.addAll(orderRequest.getOrder());
                    orderAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MyOrderActivity.this, "Looks like you don't placed any order yet...!!", Toast.LENGTH_SHORT).show();
                }
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