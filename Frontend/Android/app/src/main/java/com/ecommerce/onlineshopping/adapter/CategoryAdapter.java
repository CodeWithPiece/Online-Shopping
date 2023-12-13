package com.ecommerce.onlineshopping.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.model.Category;
import com.ecommerce.onlineshopping.views.fragment.CategoryFragment;
import com.ecommerce.onlineshopping.views.fragment.HomeFragment;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    CategoryFragment categoryFragment;
    List<Category> categoryList;
    int rowIndex = 0;

    public CategoryAdapter(CategoryFragment categoryFragment, List<Category> categoryList) {
        this.categoryFragment = categoryFragment;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryList.get(holder.getAdapterPosition());
        holder.txtCategory.setText(category.getCatName());
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
            categoryFragment.productViewModel.getProductByCategory(category.getCatId());
        } else {
            holder.txtCategory.setTextColor(categoryFragment.getActivity().getColor(R.color.seed));
            holder.txtCategory.setBackgroundResource(R.drawable.category_light_bg);
        }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCategory = itemView.findViewById(R.id.txtCategory);

        }
    }

}
