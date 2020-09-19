package com.david0926.travity.onboard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.david0926.travity.R;

public class OnBoardPagerAdapter extends FragmentStateAdapter {

    public OnBoardPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        OnBoardPagerFragment fragment;

        switch (position) {
            case 0:
                fragment = OnBoardPagerFragment.newInstance(R.layout.fragment_on_board_1);
                break;
            case 1:
                fragment = OnBoardPagerFragment.newInstance(R.layout.fragment_on_board_2);
                break;
            case 2:
                fragment = OnBoardPagerFragment.newInstance(R.layout.fragment_on_board_3);
                break;
            default:
                fragment = OnBoardPagerFragment.newInstance(R.layout.fragment_on_board_4);
                break;
        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
