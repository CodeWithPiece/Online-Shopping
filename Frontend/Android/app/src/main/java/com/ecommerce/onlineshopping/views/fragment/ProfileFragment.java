package com.ecommerce.onlineshopping.views.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.utils.MyPreferences;
import com.ecommerce.onlineshopping.views.activity.MainActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView txtCustomerName = view.findViewById(R.id.txtCustomerName);
        TextView txtCustomerEmail = view.findViewById(R.id.txtCustomerEmail);
        RelativeLayout logoutLayout = view.findViewById(R.id.logoutLayout);
        MyPreferences myPreferences = MyPreferences.getInstance(getContext());
        txtCustomerName.setText(myPreferences.getUserName());
        txtCustomerEmail.setText(myPreferences.getUserEmail());

        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutDialog(myPreferences);
            }
        });

        return view;
    }

    private void logoutDialog(MyPreferences myPreferences) {
        new MaterialAlertDialogBuilder(getContext())
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout this session?")
                .setPositiveButton("LOGOUT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        myPreferences.logout();
                        startActivity(new Intent(getContext().getApplicationContext(), MainActivity.class));
                        getActivity().finishAffinity();
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                }).show();

    }

}