package com.ecommerce.onlineshopping.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.model.Product;
import com.ecommerce.onlineshopping.utils.Constant;
import com.ecommerce.onlineshopping.views.activity.ProductDetailsActivity;
import com.ecommerce.onlineshopping.views.fragment.HomeFragment;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class TrendingClothesAdapter extends RecyclerView.Adapter<TrendingClothesAdapter.ViewHolder> {

    HomeFragment homeFragment;
    List<Product> productList;

    public TrendingClothesAdapter(HomeFragment homeFragment, List<Product> productList) {
        this.homeFragment = homeFragment;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(holder.getAdapterPosition());
        Glide.with(homeFragment)
                .load(Constant.IMAGE_URL + product.getProductImage())
                .into(holder.imgProduct);
        holder.txtProductName.setText(product.getProductName());
        holder.txtProductPrice.setText("₹ " + product.getProductPrice());
        holder.txtRating.setText(product.getProductRating());
        holder.cardProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homeFragment.getActivity(), ProductDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("product", product);
                homeFragment.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView cardProduct;
        ImageView imgProduct;
        TextView txtProductName, txtProductPrice, txtRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardProduct = itemView.findViewById(R.id.cardProduct);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtProductPrice = itemView.findViewById(R.id.txtProductPrice);
            txtRating = itemView.findViewById(R.id.txtRating);
        }
    }

}
