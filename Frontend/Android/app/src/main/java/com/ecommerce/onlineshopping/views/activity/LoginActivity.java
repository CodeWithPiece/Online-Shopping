package com.ecommerce.onlineshopping.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.onlineshopping.R;
import com.ecommerce.onlineshopping.model.LoginRequest;
import com.ecommerce.onlineshopping.model.User;
import com.ecommerce.onlineshopping.viewmodel.LoginViewModel;
import com.ecommerce.onlineshopping.viewmodel.RegisterViewModel;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    EditText edtNumber, edtPassword;
    boolean isAllFieldsChecked = false;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView txtSignUp = findViewById(R.id.txtSignUp);
        MaterialButton btnLogin = findViewById(R.id.btnLogin);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        edtNumber = findViewById(R.id.edtNumber);
        edtPassword = findViewById(R.id.edtPassword);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
            }
        });

        loginViewModel.getProgressData().observe(LoginActivity.this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBar.setVisibility(integer);
            }
        });

        loginViewModel.getUserData().observe(LoginActivity.this, new Observer<LoginRequest>() {
            @Override
            public void onChanged(LoginRequest loginRequest) {
                if (loginRequest != null) {
                    Toast.makeText(LoginActivity.this, "" + loginRequest.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failure...!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = checkAllFields();
                if (isAllFieldsChecked) {
                    Log.e("NUMBER", edtNumber.getText().toString().trim());
                    Log.e("PASSWORD", edtPassword.getText().toString().trim());
                    loginViewModel.doLogin(edtNumber.getText().toString().trim()
                            , edtPassword.getText().toString().trim());
                }
            }
        });

    }

    public boolean checkAllFields() {
        if (edtNumber.length() == 0) {
            edtNumber.setError("Please enter number");
            edtNumber.requestFocus();
            return false;
        }
        if (edtPassword.length() == 0) {
            edtPassword.setError("Please enter password");
            edtPassword.requestFocus();
            return false;
        }
        return true;
    }

}