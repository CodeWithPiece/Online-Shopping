package com.ecommerce.onlineshopping.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferences {

    private static MyPreferences mInstance;
    private static Context mCtx;
    public static final String SHARED_PREF_NAME = "fashion app";
    public static final String userId = "user_id";
    public static final String userName = "user_name";
    public static final String userNumber = "user_number";
    public static final String userEmail = "user_email";
    public static final String userImage = "user_image";
    public static final String userAddress = "user_address";

    public MyPreferences(Context context) {
        mCtx = context;
    }

    public static synchronized MyPreferences getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MyPreferences(context);
        }
        return mInstance;
    }

    public boolean logout() {
        SharedPreferences settings = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public void saveUserDetails(int id, String name, String number, String email, String image,
                                String address) {
        SharedPreferences settings = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(userId, id);
        editor.putString(userName, name);
        editor.putString(userNumber, number);
        editor.putString(userEmail, email);
        editor.putString(userImage, image);
        editor.putString(userAddress, address);
        editor.apply();
    }

    public void saveUserImage(String image) {
        SharedPreferences settings = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(userImage, image);
        editor.apply();
    }

    public int getUserId() {
        SharedPreferences settings = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return settings.getInt(userId, 0);
    }

    public String getUserName() {
        SharedPreferences settings = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return settings.getString(userName, "");
    }

    public String getUserNumber() {
        SharedPreferences settings = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return settings.getString(userNumber, "");
    }

    public String getUserEmail() {
        SharedPreferences settings = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return settings.getString(userEmail, "");
    }

    public String getUserAddress() {
        SharedPreferences settings = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return settings.getString(userAddress, "");
    }

    public String getUserImage() {
        SharedPreferences settings = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return settings.getString(userImage, "");
    }

}
