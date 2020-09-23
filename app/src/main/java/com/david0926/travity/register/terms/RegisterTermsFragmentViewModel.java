package com.david0926.travity.register.terms;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;

public class RegisterTermsFragmentViewModel extends ViewModel {

    public ObservableArrayList<Boolean> checkedState = new ObservableArrayList<>();

    public RegisterTermsFragmentViewModel() {
        checkedState.addAll(Arrays.asList(false, false, false, false));
    }

    public void checkAll() {
        if (checkedState.contains(false)) {
            for (int i = 0; i < checkedState.size(); i++)
                checkedState.set(i, true);
        } else {
            for (int i = 0; i < checkedState.size(); i++)
                checkedState.set(i, false);
        }
    }

    public void check(int index) {
        checkedState.set(index, !checkedState.get(index));
    }


}
