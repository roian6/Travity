package com.david0926.travity.register.pw;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterPwFragmentViewModel extends ViewModel {

    public MutableLiveData<String> pw = new MutableLiveData<>("");
    public MutableLiveData<String> pwCheck = new MutableLiveData<>("");

}
