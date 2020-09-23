package com.david0926.travity.onboard;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class OnBoardViewModel extends ViewModel {

    @BindingAdapter("bindPagerCurrentItem")
    public static void bindPagerCurrentItem(ViewPager2 pager, int position) {
        pager.setCurrentItem(position);
    }

    @BindingAdapter("bindPagerCallback")
    public static void bindPagerCallback(ViewPager2 pager, ViewPager2.OnPageChangeCallback c){
        pager.registerOnPageChangeCallback(c);
    }

    @BindingAdapter("bindTabMediator")
    public static void bindTabMediator(TabLayout tab, ViewPager2 pager) {
        new TabLayoutMediator(tab, pager,
                (t, position) -> t.view.setClickable(false)).attach();
    }

    public MutableLiveData<Integer> currentPage = new MutableLiveData<>(0);
    public MutableLiveData<Integer> pagePositionData = new MutableLiveData<>(0);

    public ViewPager2.OnPageChangeCallback pagerCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            currentPage.setValue(position);
        }
    };

    public void nextPage() {
        int value = currentPage.getValue();
        if (currentPage.getValue() < 3) {
            value++;
            currentPage.setValue(value);
            pagePositionData.setValue(value);
        }
    }

    public void previousPage() {
        int value = currentPage.getValue();
        if (currentPage.getValue() > 0) {
            value--;
            currentPage.setValue(value);
            pagePositionData.setValue(value);
        }
    }
}
