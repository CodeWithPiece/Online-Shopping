package com.ecommerce.onlineshopping.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.model.CartModel;
import com.ecommerce.onlineshopping.model.Order;
import com.ecommerce.onlineshopping.utils.Constant;
import com.ecommerce.onlineshopping.views.activity.MyOrderActivity;
import com.ecommerce.onlineshopping.views.fragment.CartFragment;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    MyOrderActivity myOrderActivity;
    List<Order> orderList;

    public OrderAdapter(MyOrderActivity myOrderActivity, List<Order> orderList) {
        this.myOrderActivity = myOrderActivity;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_order_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Order order = orderList.get(holder.getAdapterPosition());
        Glide.with(myOrderActivity)
                .load(Constant.IMAGE_URL + order.getProductImage())
                .into(holder.imgProduct);
        holder.txtProductName.setText(order.getProductName());
        holder.txtSizeQty.setText("Size: "+order.getSizeName()+" | Qty: "+order.getProductCount()+" pcs");
        holder.txtAmount.setText("₹" + order.getProductPrice());
        holder.cardReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOrderActivity.getReviewDialog();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView cardReview;
        ImageView imgProduct;
        TextView txtProductName, txtSizeQty, txtAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardReview = itemView.findViewById(R.id.cardReview);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtSizeQty = itemView.findViewById(R.id.txtSizeQty);
            txtAmount = itemView.findViewById(R.id.txtAmount);

        }
    }

}
