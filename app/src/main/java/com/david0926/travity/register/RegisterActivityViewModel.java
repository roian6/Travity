package com.david0926.travity.register;

import android.widget.FrameLayout;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.david0926.travity.R;
import com.david0926.travity.register.email.RegisterEmailFragment;
import com.david0926.travity.register.pw.RegisterPwFragment;
import com.david0926.travity.register.terms.RegisterTermsFragment;

public class RegisterActivityViewModel extends ViewModel {

    @BindingAdapter("bindFragment")
    public static void bindFragment(FrameLayout frame, Fragment f) {
        FragmentActivity a = (FragmentActivity) frame.getContext();
        FragmentTransaction transaction = a.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.replace(frame.getId(), f);
        transaction.commit();
    }

    public MutableLiveData<Boolean> isNextEnabled = new MutableLiveData<>(false);

    public ObservableArrayList<Fragment> fragments = new ObservableArrayList<>();
    public MutableLiveData<Integer> currentPage = new MutableLiveData<>(0);

    public RegisterActivityViewModel() {
        fragments.add(RegisterTermsFragment.newInstance());
        fragments.add(RegisterEmailFragment.newInstance());
        fragments.add(RegisterPwFragment.newInstance());
    }

    public void nextPage() {
        int value = currentPage.getValue();
        if (value < fragments.size() - 1)
            currentPage.setValue(value + 1);
    }

    public void previousPage() {
        int value = currentPage.getValue();
        if (value > 0)
            currentPage.setValue(value - 1);
    }
}
