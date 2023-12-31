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
import com.ecommerce.onlineshopping.utils.Constant;
import com.ecommerce.onlineshopping.views.fragment.CartFragment;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    CartFragment cartFragment;
    List<CartModel> cartModelList;
    int size = 10;

    public CartAdapter(CartFragment cartFragment, List<CartModel> cartModelList) {
        this.cartFragment = cartFragment;
        this.cartModelList = cartModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cart_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartModel cartModel = cartModelList.get(holder.getAdapterPosition());
        Glide.with(cartFragment)
                .load(Constant.IMAGE_URL + cartModel.getProductImage())
                .into(holder.imgProduct);
        holder.txtProductName.setText(cartModel.getProductName());
        holder.txtSize.setText("Size: " + cartModel.getSizeName());
        holder.txtAmount.setText("₹" + cartModel.getProductPrice());
        holder.txtCount.setText(cartModel.getProductCount() + "");
        holder.cardMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = cartModel.getProductCount();
                if (count > 1) {
                    count--;
                    cartFragment.updateCart(count, cartModel.getCartId());
                } else {
                    // delete cart
                    cartFragment.deleteCart(cartModel.getCartId());
                }
            }
        });

        holder.cardPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = cartModel.getProductCount();
                count++;
                cartFragment.updateCart(count, cartModel.getCartId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduct;
        TextView txtProductName, txtSize, txtAmount, txtCount;
        MaterialCardView cardMinus, cardPlus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtSize = itemView.findViewById(R.id.txtSize);
            txtAmount = itemView.findViewById(R.id.txtAmount);
            txtCount = itemView.findViewById(R.id.txtCount);
            cardMinus = itemView.findViewById(R.id.cardMinus);
            cardPlus = itemView.findViewById(R.id.cardPlus);

        }
    }

    public void removeItem(int position) {
        notifyItemRemoved(position);
    }

}
