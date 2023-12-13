package com.ecommerce.onlineshopping.views.fragment;

import android.content.Intent;
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
import android.widget.Toast;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.adapter.CategoryAdapter;
import com.ecommerce.onlineshopping.adapter.ProductAdapter;
import com.ecommerce.onlineshopping.model.Category;
import com.ecommerce.onlineshopping.model.CategoryRequest;
import com.ecommerce.onlineshopping.model.Product;
import com.ecommerce.onlineshopping.model.ProductRequest;
import com.ecommerce.onlineshopping.model.User;
import com.ecommerce.onlineshopping.viewmodel.CategoryViewModel;
import com.ecommerce.onlineshopping.viewmodel.LoginViewModel;
import com.ecommerce.onlineshopping.viewmodel.ProductViewModel;
import com.ecommerce.onlineshopping.views.activity.DashboardActivity;
import com.ecommerce.onlineshopping.views.activity.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    private CategoryViewModel categoryViewModel;
    public ProductViewModel productViewModel;
    List<Category> categoryList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        RecyclerView categoryRecycler = view.findViewById(R.id.categoryRecycler);
        RecyclerView productRecycler = view.findViewById(R.id.productRecycler);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        CategoryAdapter categoryAdapter = new CategoryAdapter(CategoryFragment.this, categoryList);
        ProductAdapter productAdapter = new ProductAdapter(CategoryFragment.this, productList);
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

        productViewModel.getProductData().observe(getViewLifecycleOwner(), new Observer<ProductRequest>() {
            @Override
            public void onChanged(ProductRequest productRequest) {
                if (productRequest != null) {
                    productList.clear();
                    productList.addAll(productRequest.getProduct());
                    productAdapter.notifyDataSetChanged();
                } else {
                    productList.clear();
                    productAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Products not found...!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        categoryViewModel.getCategoryData().observe(getViewLifecycleOwner(), new Observer<CategoryRequest>() {
            @Override
            public void onChanged(CategoryRequest categoryRequest) {
                if (categoryRequest != null) {
                    categoryList.addAll(categoryRequest.getCategory());
                    categoryAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Categories not found...!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        categoryViewModel.getCategories();

        return view;
    }

}