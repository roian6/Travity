package com.david0926.travity.util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.david0926.travity.model.UserModel;
import com.google.gson.Gson;

public class UserCache {

    private static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setUser(Context context, UserModel model) {
        Gson gson = new Gson();
        String json = gson.toJson(model);
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString("user_json", json).apply();
    }

    public static UserModel getUser(Context context) {
        Gson gson = new Gson();
        return gson.fromJson(getSharedPreferences(context).getString("user_json", ""), UserModel.class);
    }
}
