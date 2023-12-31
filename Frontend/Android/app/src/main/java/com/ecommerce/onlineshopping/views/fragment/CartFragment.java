package com.ecommerce.onlineshopping.views.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.adapter.CartAdapter;
import com.ecommerce.onlineshopping.model.CartModel;
import com.ecommerce.onlineshopping.model.CartRequest;
import com.ecommerce.onlineshopping.model.DeleteCart;
import com.ecommerce.onlineshopping.model.PlaceOrder;
import com.ecommerce.onlineshopping.model.UpdateCart;
import com.ecommerce.onlineshopping.utils.MyPreferences;
import com.ecommerce.onlineshopping.utils.SwipeToDeleteCallback;
import com.ecommerce.onlineshopping.viewmodel.CartViewModel;
import com.ecommerce.onlineshopping.viewmodel.OrderViewModel;
import com.ecommerce.onlineshopping.views.activity.CheckOutActivity;
import com.ecommerce.onlineshopping.views.activity.MyOrderActivity;
import com.ecommerce.onlineshopping.views.activity.OrderPlacedActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    CartViewModel cartViewModel;
    OrderViewModel orderViewModel;
    List<CartModel> cartModelList = new ArrayList<>();

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        RecyclerView cartRecycler = view.findViewById(R.id.cartRecycler);
        MaterialButton btnProceed = view.findViewById(R.id.btnProceed);
        TextView txtTotal = view.findViewById(R.id.txtTotal);
        TextView txtSubTotal = view.findViewById(R.id.txtSubTotal);
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        CartAdapter cartAdapter = new CartAdapter(CartFragment.this, cartModelList);
        cartRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        cartRecycler.setAdapter(cartAdapter);
        enableSwipeToDeleteAndUndo(cartAdapter, cartRecycler);
        MyPreferences myPreferences = MyPreferences.getInstance(getContext());
        cartViewModel.getCart(myPreferences.getUserId());

        cartViewModel.getProgressData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBar.setVisibility(integer);
            }
        });

        cartViewModel.getCartData().observe(getViewLifecycleOwner(), new Observer<CartRequest>() {
            @Override
            public void onChanged(CartRequest cartRequest) {
                if (cartRequest != null) {
                    cartModelList.clear();
                    cartModelList.addAll(cartRequest.getCart());
                    cartAdapter.notifyDataSetChanged();
                    double amount = 0.0;
                    for (CartModel cartModel : cartModelList) {
                        amount = amount + Double.parseDouble(cartModel.getProductPrice());
                    }
                    txtSubTotal.setText("₹ " + amount);
                    txtTotal.setText("₹ " + amount);
                } else {
                    cartModelList.clear();
                    cartAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Cart is empty...!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cartViewModel.updateCartData().observe(getViewLifecycleOwner(), new Observer<UpdateCart>() {
            @Override
            public void onChanged(UpdateCart updateCart) {
                if (updateCart != null) {
                    Toast.makeText(getContext(), "" + updateCart.getMessage(), Toast.LENGTH_SHORT).show();
                    cartViewModel.getCart(myPreferences.getUserId());
                } else {
                    Toast.makeText(getContext(), "Something went wrong...!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cartViewModel.deleteCartData().observe(getViewLifecycleOwner(), new Observer<DeleteCart>() {
            @Override
            public void onChanged(DeleteCart deleteCart) {
                if (deleteCart != null) {
                    Toast.makeText(getContext(), "" + deleteCart.getMessage(), Toast.LENGTH_SHORT).show();
                    cartViewModel.getCart(myPreferences.getUserId());
                } else {
                    Toast.makeText(getContext(), "Something went wrong...!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        orderViewModel.getProgressData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBar.setVisibility(integer);
            }
        });

        orderViewModel.getOrderData().observe(getViewLifecycleOwner(), new Observer<PlaceOrder>() {
            @Override
            public void onChanged(PlaceOrder placeOrder) {
                if (placeOrder != null) {
                    Toast.makeText(getContext(), "" + placeOrder.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext().getApplicationContext(), OrderPlacedActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getContext().startActivity(intent);
                    getActivity().finishAffinity();
                } else {
                    Toast.makeText(getContext(), "Something went wrong...!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cartModelList.isEmpty()) {
                    orderViewModel.placeOrder(myPreferences.getUserId());
                } else {
                    Toast.makeText(getContext(), "Cart is empty...!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    private void enableSwipeToDeleteAndUndo(CartAdapter mAdapter, RecyclerView cartRecycler) {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                CartModel cartModel = cartModelList.get(viewHolder.getAdapterPosition());
                deleteCart(cartModel.getCartId());
            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(cartRecycler);
    }

    public void updateCart(int productCount, int cartId) {
        cartViewModel.updateCart(productCount, cartId);
    }

    public void deleteCart(int cartId) {
        cartViewModel.deleteCart(cartId);
    }

}