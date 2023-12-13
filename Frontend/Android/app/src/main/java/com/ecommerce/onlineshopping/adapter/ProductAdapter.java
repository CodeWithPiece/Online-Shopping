package com.ecommerce.onlineshopping.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.model.Product;
import com.ecommerce.onlineshopping.views.activity.ProductDetailsActivity;
import com.ecommerce.onlineshopping.views.fragment.CategoryFragment;
import com.ecommerce.onlineshopping.views.fragment.HomeFragment;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    CategoryFragment categoryFragment;
    List<Product> productList;

    public ProductAdapter(CategoryFragment categoryFragment, List<Product> productList) {
        this.categoryFragment = categoryFragment;
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
        holder.cardProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(categoryFragment.getActivity(), ProductDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                categoryFragment.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView cardProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardProduct = itemView.findViewById(R.id.cardProduct);
        }
    }

}
