package com.ecommerce.onlineshopping.views.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.adapter.BannerAdapter;
import com.ecommerce.onlineshopping.adapter.TrendingClothesAdapter;
import com.ecommerce.onlineshopping.model.Product;
import com.ecommerce.onlineshopping.model.TrendingProductRequest;
import com.ecommerce.onlineshopping.viewmodel.CategoryViewModel;
import com.ecommerce.onlineshopping.viewmodel.TrendingProductViewModel;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    TrendingProductViewModel trendingProductViewModel;
    List<Product> productList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        trendingProductViewModel = new ViewModelProvider(this).get(TrendingProductViewModel.class);
        SliderView slider = view.findViewById(R.id.slider);
        ProgressBar homeProgress = view.findViewById(R.id.homeProgress);
        RecyclerView trendingRecycler = view.findViewById(R.id.trendingRecycler);
        BannerAdapter bannerAdapter = new BannerAdapter(HomeFragment.this);
        TrendingClothesAdapter trendingClothesAdapter = new TrendingClothesAdapter(HomeFragment.this, productList);
        trendingRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        trendingRecycler.setAdapter(trendingClothesAdapter);
        slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        slider.setSliderAdapter(bannerAdapter);
        slider.setScrollTimeInSec(2);
        slider.setAutoCycle(true);
        slider.startAutoCycle();

        trendingProductViewModel.getTrendingProduct();
        trendingProductViewModel.getProgressData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                homeProgress.setVisibility(integer);
            }
        });

        trendingProductViewModel.getTrendingProductData().observe(getViewLifecycleOwner(), new Observer<TrendingProductRequest>() {
            @Override
            public void onChanged(TrendingProductRequest trendingProductRequest) {
                if (trendingProductRequest != null) {
                    productList.addAll(trendingProductRequest.getProduct());
                    trendingClothesAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Products not found...!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}