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
import com.ecommerce.onlineshopping.model.ProductImage;
import com.ecommerce.onlineshopping.utils.Constant;
import com.ecommerce.onlineshopping.views.activity.ProductDetailsActivity;
import com.ecommerce.onlineshopping.views.fragment.CategoryFragment;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ViewHolder> {

    ProductDetailsActivity productDetailsActivity;
    List<ProductImage> productImageList;
    int rowIndex = -1;

    public ImageSliderAdapter(ProductDetailsActivity productDetailsActivity, List<ProductImage> productImageList) {
        this.productDetailsActivity = productDetailsActivity;
        this.productImageList = productImageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_image_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductImage productImage = productImageList.get(holder.getAdapterPosition());
        Glide.with(productDetailsActivity)
                .load(Constant.IMAGE_URL + productImage.getImageUrl())
                .into(holder.productImage);
        holder.imgCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowIndex = holder.getAdapterPosition();
                notifyDataSetChanged();
            }
        });

        if (rowIndex == holder.getAdapterPosition()) {
            Glide.with(productDetailsActivity)
                    .load(Constant.IMAGE_URL + productImage.getImageUrl())
                    .into(productDetailsActivity.imgProduct);
        }

    }

    @Override
    public int getItemCount() {
        return productImageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        MaterialCardView imgCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.productImage);
            imgCard = itemView.findViewById(R.id.imgCard);

        }
    }

}
