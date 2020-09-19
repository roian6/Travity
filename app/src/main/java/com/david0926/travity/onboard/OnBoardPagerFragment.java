package com.david0926.travity.onboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OnBoardPagerFragment extends Fragment {

    private static String KEY_LAYOUT_ID = "layoutID";
    private int layoutId;

    public static OnBoardPagerFragment newInstance(int layoutId) {
        OnBoardPagerFragment fragment = new OnBoardPagerFragment();

        //put layout id as parameter
        Bundle args = new Bundle();
        args.putInt(KEY_LAYOUT_ID, layoutId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null && args.containsKey(KEY_LAYOUT_ID))
            layoutId = args.getInt(KEY_LAYOUT_ID);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(layoutId, container, false);
    }
}
