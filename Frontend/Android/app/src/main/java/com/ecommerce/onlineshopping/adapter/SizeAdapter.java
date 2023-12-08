package com.ecommerce.onlineshopping.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.views.activity.ProductDetailsActivity;
import com.google.android.material.card.MaterialCardView;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.ViewHolder> {

    ProductDetailsActivity productDetailsActivity;
    int rowIndex = 0;

    public SizeAdapter(ProductDetailsActivity productDetailsActivity) {
        this.productDetailsActivity = productDetailsActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_size_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowIndex = holder.getAdapterPosition();
                notifyDataSetChanged();
            }
        });
        if (rowIndex == holder.getAdapterPosition()) {
            holder.cardSize.setBackgroundColor(productDetailsActivity.getColor(R.color.seed));
            holder.txtSize.setTextColor(productDetailsActivity.getColor(R.color.white));
        } else {
            holder.cardSize.setBackgroundColor(productDetailsActivity.getColor(R.color.white));
            holder.txtSize.setTextColor(productDetailsActivity.getColor(R.color.black));
        }

    }

    @Override
    public int getItemCount() {
        return 5;
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
