package com.david0926.travity.register;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.david0926.travity.R;
import com.david0926.travity.databinding.ActivityRegisterBinding;


public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private RegisterActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setLifecycleOwner(this);
        binding.setActivity(this);

        viewModel = ViewModelProviders.of(this).get(RegisterActivityViewModel.class);
        binding.setViewModel(viewModel);
    }

    public void btnNextPressed() {
        if (viewModel.currentPage.getValue() < 2) viewModel.nextPage();
        else {
            Toast.makeText(this, "회원가입이 완료되었습니다!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (viewModel.currentPage.getValue() != 0) viewModel.previousPage();
        else super.onBackPressed();
    }

}
