package com.ecommerce.onlineshopping.views.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.adapter.WishlistAdapter;

public class FavouriteFragment extends Fragment {

    public FavouriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        RecyclerView productRecycler = view.findViewById(R.id.productRecycler);
        WishlistAdapter wishlistAdapter = new WishlistAdapter(FavouriteFragment.this);
        productRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        productRecycler.setAdapter(wishlistAdapter);

        return view;
    }
}