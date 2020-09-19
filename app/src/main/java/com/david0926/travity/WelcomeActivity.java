package com.david0926.travity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.david0926.travity.databinding.ActivityWelcomeBinding;
import com.david0926.travity.onboard.OnBoardActivity;
import com.david0926.travity.util.SharedPreferenceUtil;

public class WelcomeActivity extends AppCompatActivity {

    private ActivityWelcomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        binding.setActivity(this);
    }

    public void startBtnClick() {
        SharedPreferenceUtil.put(this, "user_progress", "progress_on_board");
        startActivity(new Intent(WelcomeActivity.this, OnBoardActivity.class));
        finish();
    }
}