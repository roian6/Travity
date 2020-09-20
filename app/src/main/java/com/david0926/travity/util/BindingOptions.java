package com.david0926.travity.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingConversion;

public class BindingOptions {

    @BindingConversion
    public static int convertBooleanToVisibility(boolean visible) {
        return visible ? View.VISIBLE : View.GONE;
    }

    @BindingAdapter("bindInvisibility")
    public static void bindInvisibility(View v, Boolean b) {
        v.setVisibility(b ? View.VISIBLE : View.INVISIBLE);
    }

    @BindingAdapter("bindImageSrc")
    public static void bindImageSrc(ImageView v, Drawable d) {
        v.setImageDrawable(d);
    }

    @BindingAdapter("bindFocusTo")
    public static void bindFocusTo(View v, View v1) {
        v.setOnClickListener(view -> v1.post(() -> {
            v1.setFocusableInTouchMode(true);
            v1.requestFocus();
            InputMethodManager imm = (InputMethodManager)
                    v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(v1, 0);
        }));
    }

}
