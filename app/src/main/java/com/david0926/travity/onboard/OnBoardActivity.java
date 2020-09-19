package com.david0926.travity.onboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import com.david0926.travity.LoginActivity;
import com.david0926.travity.R;
import com.david0926.travity.databinding.ActivityOnBoardBinding;

public class OnBoardActivity extends AppCompatActivity {

    private ActivityOnBoardBinding binding;
    private OnBoardViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_on_board);
        binding.setLifecycleOwner(this);
        binding.setActivity(this);

        viewModel = ViewModelProviders.of(this).get(OnBoardViewModel.class);
        binding.setViewModel(viewModel);

        OnBoardPagerAdapter adapter = new OnBoardPagerAdapter(this);
        binding.pagerOnBoard.setAdapter(adapter);
        binding.pagerOnBoard.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                viewModel.pagePosition.setValue(position);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (viewModel.pagePosition.getValue() != 0) viewModel.decreasePagePosition();
        else super.onBackPressed();
    }

    public void finishOnBoard() {
        startActivity(new Intent(OnBoardActivity.this, LoginActivity.class));
        finish();
    }
}