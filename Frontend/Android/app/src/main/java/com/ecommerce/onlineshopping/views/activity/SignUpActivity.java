package com.ecommerce.onlineshopping.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.model.RegisterUser;
import com.ecommerce.onlineshopping.viewmodel.ShoppingViewModel;
import com.google.android.material.button.MaterialButton;

public class SignUpActivity extends AppCompatActivity {

    private ShoppingViewModel shoppingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView txtLogin = findViewById(R.id.txtLogin);
        MaterialButton btnSignUp = findViewById(R.id.btnSignUp);
        shoppingViewModel = new ViewModelProvider(this).get(ShoppingViewModel.class);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingViewModel.registerUser("Nirmal Kumar"
                                , "543534534535"
                                , "rajiv@gmail.com"
                                , "Dhanbad"
                                , "12345")
                        .observe(SignUpActivity.this, new Observer<RegisterUser>() {
                            @Override
                            public void onChanged(RegisterUser registerUser) {
                                if (registerUser != null) {
                                    Toast.makeText(SignUpActivity.this, "" + registerUser.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

}