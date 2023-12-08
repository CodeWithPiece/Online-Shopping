package com.ecommerce.onlineshopping.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.views.fragment.HomeFragment;

public class TrendingClothesAdapter extends RecyclerView.Adapter<TrendingClothesAdapter.ViewHolder> {

    HomeFragment homeFragment;

    public TrendingClothesAdapter(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
