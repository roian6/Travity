package com.david0926.travity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.david0926.travity.R;
import com.david0926.travity.databinding.FragmentMain2Binding;
import com.david0926.travity.util.ViewpagerAdapater;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

public class MainFragment2 extends Fragment {

    public static MainFragment2 newInstance() {
        return new MainFragment2();
    }

    private Context mContext;
    private FragmentMain2Binding binding;

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main2, container, false);

        binding.viewpager.setAdapter(new ViewpagerAdapater(getActivity()));
        new TabLayoutMediator(binding.tabLayout, binding.viewpager, (tab, position) -> {tab.setText(position == 0 ? "할일" : "준비물");}).attach();

        return binding.getRoot();
    }
}
