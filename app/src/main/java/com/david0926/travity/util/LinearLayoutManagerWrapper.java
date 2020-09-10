package com.david0926.travity.util;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

//samsung device issue
public class LinearLayoutManagerWrapper extends LinearLayoutManager {

    public LinearLayoutManagerWrapper(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public boolean supportsPredictiveItemAnimations() {
        return false;
    }
}