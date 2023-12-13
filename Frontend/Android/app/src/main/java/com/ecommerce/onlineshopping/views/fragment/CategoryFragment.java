package com.ecommerce.onlineshopping.views.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.adapter.CategoryAdapter;
import com.ecommerce.onlineshopping.adapter.ProductAdapter;
import com.ecommerce.onlineshopping.model.Category;
import com.ecommerce.onlineshopping.model.CategoryRequest;
import com.ecommerce.onlineshopping.viewmodel.CategoryViewModel;
import com.ecommerce.onlineshopping.viewmodel.LoginViewModel;

public class CategoryFragment extends Fragment {

    private CategoryViewModel categoryViewModel;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        RecyclerView categoryRecycler = view.findViewById(R.id.categoryRecycler);
        RecyclerView productRecycler = view.findViewById(R.id.productRecycler);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        CategoryAdapter categoryAdapter = new CategoryAdapter(CategoryFragment.this);
        ProductAdapter productAdapter = new ProductAdapter(CategoryFragment.this);
        categoryRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        productRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        categoryRecycler.setAdapter(categoryAdapter);
        productRecycler.setAdapter(productAdapter);

        categoryViewModel.getProgressData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBar.setVisibility(integer);
            }
        });

        categoryViewModel.getCategoryData().observe(getViewLifecycleOwner(), new Observer<CategoryRequest>() {
            @Override
            public void onChanged(CategoryRequest categoryRequest) {
                for (Category category : categoryRequest.getCategory()) {
                    Log.e("CATEGORY", category.getCatName());
                }
            }
        });
        
        categoryViewModel.getCategories();

        return view;
    }
}