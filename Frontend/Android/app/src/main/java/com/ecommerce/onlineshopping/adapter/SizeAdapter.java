package com.ecommerce.onlineshopping.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.model.ProductSize;
import com.ecommerce.onlineshopping.views.activity.ProductDetailsActivity;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.ViewHolder> {

    ProductDetailsActivity productDetailsActivity;
    List<ProductSize> productSizeList;
    int rowIndex = 0;

    public SizeAdapter(ProductDetailsActivity productDetailsActivity, List<ProductSize> productSizeList) {
        this.productDetailsActivity = productDetailsActivity;
        this.productSizeList = productSizeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_size_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductSize productSize = productSizeList.get(holder.getAdapterPosition());
        holder.txtSize.setText(productSize.getSizeName());
        holder.cardSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowIndex = holder.getAdapterPosition();
                notifyDataSetChanged();
            }
        });
        if (rowIndex == holder.getAdapterPosition()) {
            productDetailsActivity.sizeId = productSize.getSizeId();
            holder.cardSize.setBackgroundColor(productDetailsActivity.getColor(R.color.seed));
            holder.txtSize.setTextColor(productDetailsActivity.getColor(R.color.white));
        } else {
            holder.cardSize.setBackgroundColor(productDetailsActivity.getColor(R.color.white));
            holder.txtSize.setTextColor(productDetailsActivity.getColor(R.color.black));
        }

    }

    @Override
    public int getItemCount() {
        return productSizeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtSize;
        MaterialCardView cardSize;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSize = itemView.findViewById(R.id.txtSize);
            cardSize = itemView.findViewById(R.id.cardSize);

        }
    }

}
