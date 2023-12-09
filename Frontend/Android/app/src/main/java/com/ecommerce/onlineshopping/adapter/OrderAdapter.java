package com.ecommerce.onlineshopping.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.views.activity.MyOrderActivity;
import com.ecommerce.onlineshopping.views.fragment.CartFragment;
import com.google.android.material.card.MaterialCardView;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    MyOrderActivity myOrderActivity;

    public OrderAdapter(MyOrderActivity myOrderActivity) {
        this.myOrderActivity = myOrderActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_order_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOrderActivity.getReviewDialog();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView cardReview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardReview = itemView.findViewById(R.id.cardReview);

        }
    }

}
