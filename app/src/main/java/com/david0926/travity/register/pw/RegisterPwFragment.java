package com.david0926.travity.register.pw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.david0926.travity.R;
import com.david0926.travity.databinding.FragmentRegisterPwBinding;
import com.david0926.travity.register.RegisterActivityViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPwFragment extends Fragment {

    public static RegisterPwFragment newInstance() {
        return new RegisterPwFragment();
    }

    private FragmentRegisterPwBinding binding;
    private RegisterPwFragmentViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_pw, container, false);
        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(requireActivity()).get(RegisterPwFragmentViewModel.class);
        binding.setViewModel(viewModel);

        viewModel.pw.observe(getViewLifecycleOwner(), s -> setIsNextPage());

        viewModel.pwCheck.observe(getViewLifecycleOwner(), s -> setIsNextPage());


        return binding.getRoot();
    }

    private void setIsNextPage() {
        String pw = viewModel.pw.getValue(), pwCheck = viewModel.pwCheck.getValue();
        ViewModelProviders
                .of(requireActivity())
                .get(RegisterActivityViewModel.class)
                .isNextEnabled.setValue(pw.equals(pwCheck)&&isValidPw(pw));
    }

    private boolean isValidPw(String target) {
        //6~24 letters, 0~9 + A-z
        Pattern p = Pattern.compile("(^.*(?=.{6,24})(?=.*[0-9])(?=.*[A-z]).*$)");
        Matcher m = p.matcher(target);
        //except korean letters
        return m.find() && !target.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*");
    }
}
