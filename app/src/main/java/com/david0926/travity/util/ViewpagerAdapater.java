package com.david0926.travity.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.david0926.travity.fragment.ChecklistFragment;

import java.util.ArrayList;

public class ViewpagerAdapater extends FragmentStateAdapter {

    public ViewpagerAdapater(FragmentActivity manager) {
        super(manager);
    }

    @Override
    public Fragment createFragment(int position) {
        return new ChecklistFragment(position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
