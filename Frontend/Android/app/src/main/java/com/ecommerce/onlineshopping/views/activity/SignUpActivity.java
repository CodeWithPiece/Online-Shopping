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
import com.ecommerce.onlineshopping.model.RegisterUser;
import com.ecommerce.onlineshopping.viewmodel.RegisterViewModel;
import com.google.android.material.button.MaterialButton;

public class SignUpActivity extends AppCompatActivity {

    private RegisterViewModel registerViewModel;
    EditText edtName, edtNumber, edtEmail, edtAddress, edtPassword;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView txtLogin = findViewById(R.id.txtLogin);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        MaterialButton btnSignUp = findViewById(R.id.btnSignUp);
        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        edtEmail = findViewById(R.id.edtEmail);
        edtAddress = findViewById(R.id.edtAddress);
        edtPassword = findViewById(R.id.edtPassword);
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });

        registerViewModel.getProgressData().observe(SignUpActivity.this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBar.setVisibility(integer);
            }
        });

        registerViewModel.getRegisterData().observe(SignUpActivity.this, new Observer<RegisterUser>() {
            @Override
            public void onChanged(RegisterUser registerUser) {
                if (registerUser != null) {
                    Toast.makeText(SignUpActivity.this, "" + registerUser.getMessage(), Toast.LENGTH_SHORT).show();
                    SignUpActivity.super.onBackPressed();
                } else {
                    Toast.makeText(SignUpActivity.this, "Register Failure...!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = checkAllFields();
                if (isAllFieldsChecked) {
                    Log.e("NAME", edtName.getText().toString().trim());
                    Log.e("NUMBER", edtNumber.getText().toString().trim());
                    Log.e("EMAIL", edtEmail.getText().toString().trim());
                    Log.e("ADDRESS", edtAddress.getText().toString().trim());
                    Log.e("PASSWORD", edtPassword.getText().toString().trim());
                    registerViewModel.registerUser(edtName.getText().toString().trim()
                            , edtNumber.getText().toString().trim()
                            , edtEmail.getText().toString().trim()
                            , edtAddress.getText().toString().trim()
                            , edtPassword.getText().toString().trim());
                }
            }
        });

    }

    public boolean checkAllFields() {
        if (edtName.length() == 0) {
            edtName.setError("Please enter name...");
            edtName.requestFocus();
            return false;
        }
        if (edtNumber.length() == 0) {
            edtNumber.setError("Please enter number...");
            edtNumber.requestFocus();
            return false;
        }
        if (edtEmail.length() == 0) {
            edtEmail.setError("Please enter email...");
            edtEmail.requestFocus();
            return false;
        }
        if (edtAddress.length() == 0) {
            edtAddress.setError("Please enter address...");
            edtAddress.requestFocus();
            return false;
        }
        if (edtPassword.length() == 0) {
            edtPassword.setError("Please enter password...");
            edtPassword.requestFocus();
            return false;
        }
        return true;
    }

}