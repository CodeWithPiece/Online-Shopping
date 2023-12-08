package com.ecommerce.onlineshopping.views.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.adapter.CategoryAdapter;
import com.ecommerce.onlineshopping.adapter.ProductAdapter;

public class CategoryFragment extends Fragment {

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        RecyclerView categoryRecycler = view.findViewById(R.id.categoryRecycler);
        RecyclerView productRecycler = view.findViewById(R.id.productRecycler);
        CategoryAdapter categoryAdapter = new CategoryAdapter(CategoryFragment.this);
        ProductAdapter productAdapter = new ProductAdapter(CategoryFragment.this);
        categoryRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        productRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        categoryRecycler.setAdapter(categoryAdapter);
        productRecycler.setAdapter(productAdapter);

        return view;
    }
}