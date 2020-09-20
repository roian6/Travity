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

    @BindingAdapter("bindTabMediator")
    public static void bindTabMediator(TabLayout tab, ViewPager2 pager) {
        new TabLayoutMediator(tab, pager,
                (t, position) -> t.view.setClickable(false)).attach();
    }

    public MutableLiveData<Integer> pagePosition = new MutableLiveData<>(0);
    public MutableLiveData<Integer> pagePositionData = new MutableLiveData<>(0);

    public void increasePagePosition() {
        int value = pagePosition.getValue();
        if (pagePosition.getValue() < 3) {
            value++;
            pagePosition.setValue(value);
            pagePositionData.setValue(value);
        }
    }

    public void decreasePagePosition() {
        int value = pagePosition.getValue();
        if (pagePosition.getValue() > 0) {
            value--;
            pagePosition.setValue(value);
            pagePositionData.setValue(value);
        }
    }
}
