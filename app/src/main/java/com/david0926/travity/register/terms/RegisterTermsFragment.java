package com.david0926.travity.register.terms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.david0926.travity.R;
import com.david0926.travity.databinding.FragmentRegisterTermsBinding;
import com.david0926.travity.register.RegisterActivityViewModel;

public class RegisterTermsFragment extends Fragment {

    public static RegisterTermsFragment newInstance() {
        return new RegisterTermsFragment();
    }

    private FragmentRegisterTermsBinding binding;
    private RegisterTermsFragmentViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_terms, container, false);
        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(requireActivity())
                .get(RegisterTermsFragmentViewModel.class);

        binding.setViewModel(viewModel);

        setIsNextPage(viewModel.checkedState);

        viewModel.checkedState.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<Boolean>>() {
            @Override
            public void onChanged(ObservableList<Boolean> sender) {

            }

            @Override
            public void onItemRangeChanged(ObservableList<Boolean> sender, int positionStart, int itemCount) {
                setIsNextPage((ObservableArrayList<Boolean>) sender);
            }

            @Override
            public void onItemRangeInserted(ObservableList<Boolean> sender, int positionStart, int itemCount) {

            }

            @Override
            public void onItemRangeMoved(ObservableList<Boolean> sender, int fromPosition, int toPosition, int itemCount) {

            }

            @Override
            public void onItemRangeRemoved(ObservableList<Boolean> sender, int positionStart, int itemCount) {

            }
        });

        return binding.getRoot();
    }

    private void setIsNextPage(ObservableArrayList<Boolean> l) {
        ViewModelProviders
                .of(requireActivity())
                .get(RegisterActivityViewModel.class)
                .isNextEnabled.setValue(l.get(1) && l.get(2) && l.get(3));
    }
}
