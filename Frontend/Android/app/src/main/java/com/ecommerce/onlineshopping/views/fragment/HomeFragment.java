package com.ecommerce.onlineshopping.views.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.adapter.BannerAdapter;
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
        BannerAdapter bannerAdapter = new BannerAdapter(HomeFragment.this);
        slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        slider.setSliderAdapter(bannerAdapter);
        slider.setScrollTimeInSec(3);
        slider.setAutoCycle(true);
        slider.startAutoCycle();

        return view;
    }
}