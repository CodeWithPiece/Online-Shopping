package com.ecommerce.onlineshopping.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.views.activity.ProductDetailsActivity;
import com.ecommerce.onlineshopping.views.fragment.CategoryFragment;
import com.ecommerce.onlineshopping.views.fragment.FavouriteFragment;
import com.google.android.material.card.MaterialCardView;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    FavouriteFragment favouriteFragment;

    public WishlistAdapter(FavouriteFragment favouriteFragment) {
        this.favouriteFragment = favouriteFragment;
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
                Intent intent = new Intent(favouriteFragment.getActivity(), ProductDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                favouriteFragment.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView cardProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardProduct = itemView.findViewById(R.id.cardProduct);
        }
    }

}
