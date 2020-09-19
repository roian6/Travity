package com.david0926.travity.util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class SharedPreferenceUtil {

    public static void put(Context context, String key, Object value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        //wow
        if(value instanceof Boolean) editor.putBoolean(key, (Boolean) value);
        else if(value instanceof Integer) editor.putInt(key, (Integer) value);
        else if(value instanceof Float) editor.putFloat(key, (Float) value);
        else if(value instanceof Long) editor.putLong(key, (Long) value);
        else if(value instanceof String) editor.putString(key, (String) value);

        editor.apply();
    }

    public static Boolean getBoolean(Context context, String key, Boolean s1) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean(key, s1);
    }

    public static int getInt(Context context, String key, int i) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt(key, i);
    }

    public static String getString(Context context, String key, String s1) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(key, s1);
    }
}