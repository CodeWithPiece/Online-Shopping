package com.ecommerce.onlineshopping.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.views.fragment.HomeFragment;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class BannerAdapter extends SliderViewAdapter<BannerAdapter.ViewHolder> {

    HomeFragment homeFragment;

    public BannerAdapter(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_banner_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Glide.with(viewHolder.itemView)
                .load(R.drawable.banner)
                .fitCenter()
                .into(viewHolder.bannerImage);
    }

    @Override
    public int getCount() {
        return 5;
    }

    public class ViewHolder extends SliderViewAdapter.ViewHolder {
        ImageView bannerImage;
        public ViewHolder(View itemView) {
            super(itemView);
            bannerImage = itemView.findViewById(R.id.bannerImage);
        }
    }

}
