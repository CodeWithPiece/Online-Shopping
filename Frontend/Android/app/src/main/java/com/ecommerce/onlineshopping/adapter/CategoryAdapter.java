package com.ecommerce.onlineshopping.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.views.fragment.CategoryFragment;
import com.ecommerce.onlineshopping.views.fragment.HomeFragment;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    CategoryFragment categoryFragment;
    int rowIndex = -1;

    public CategoryAdapter(CategoryFragment categoryFragment) {
        this.categoryFragment = categoryFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowIndex = holder.getAdapterPosition();
                notifyDataSetChanged();
            }
        });
        if (rowIndex == holder.getAdapterPosition()) {
            holder.txtCategory.setTextColor(categoryFragment.getActivity().getColor(R.color.white));
            holder.txtCategory.setBackgroundResource(R.drawable.category_dark_bg);
        } else {
            holder.txtCategory.setTextColor(categoryFragment.getActivity().getColor(R.color.seed));
            holder.txtCategory.setBackgroundResource(R.drawable.category_light_bg);
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCategory = itemView.findViewById(R.id.txtCategory);

        }
    }

}
