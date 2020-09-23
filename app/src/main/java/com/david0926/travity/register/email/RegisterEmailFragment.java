package com.david0926.travity.register.email;

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
import com.david0926.travity.databinding.FragmentRegisterEmailBinding;
import com.david0926.travity.register.RegisterActivityViewModel;

public class RegisterEmailFragment extends Fragment {

    public static RegisterEmailFragment newInstance() {
        return new RegisterEmailFragment();
    }

    private FragmentRegisterEmailBinding binding;
    private RegisterEmailFragmentViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_email, container, false);
        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(requireActivity()).get(RegisterEmailFragmentViewModel.class);
        binding.setViewModel(viewModel);

        viewModel.email.observe(getViewLifecycleOwner(), s -> {
            ViewModelProviders
                    .of(requireActivity())
                    .get(RegisterActivityViewModel.class)
                    .isNextEnabled.setValue(android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches());
        });

        return binding.getRoot();
    }
}
