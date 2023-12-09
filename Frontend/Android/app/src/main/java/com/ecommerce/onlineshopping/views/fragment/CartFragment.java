package com.ecommerce.onlineshopping.views.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.adapter.CartAdapter;
import com.ecommerce.onlineshopping.utils.SwipeToDeleteCallback;
import com.ecommerce.onlineshopping.views.activity.CheckOutActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

public class CartFragment extends Fragment {

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        RecyclerView cartRecycler = view.findViewById(R.id.cartRecycler);
        MaterialButton btnProceed = view.findViewById(R.id.btnProceed);
        CartAdapter cartAdapter = new CartAdapter(CartFragment.this);
        cartRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        cartRecycler.setAdapter(cartAdapter);
        enableSwipeToDeleteAndUndo(cartAdapter, cartRecycler);

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext().getApplicationContext(), CheckOutActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });

        return view;
    }

    private void enableSwipeToDeleteAndUndo(CartAdapter mAdapter, RecyclerView cartRecycler) {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final int position = viewHolder.getAdapterPosition();
                mAdapter.removeItem(position);
            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(cartRecycler);
    }

}