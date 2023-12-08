package com.ecommerce.onlineshopping.views.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.adapter.BannerAdapter;
import com.ecommerce.onlineshopping.adapter.TrendingClothesAdapter;
import com.smarteist.autoimageslider.SliderView;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        SliderView slider = view.findViewById(R.id.slider);
        RecyclerView trendingRecycler = view.findViewById(R.id.trendingRecycler);
        BannerAdapter bannerAdapter = new BannerAdapter(HomeFragment.this);
        TrendingClothesAdapter trendingClothesAdapter = new TrendingClothesAdapter(HomeFragment.this);
        trendingRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        trendingRecycler.setAdapter(trendingClothesAdapter);
        slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        slider.setSliderAdapter(bannerAdapter);
        slider.setScrollTimeInSec(3);
        slider.setAutoCycle(true);
        slider.startAutoCycle();

        return view;
    }
}