package com.david0926.travity;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.david0926.travity.fragment.LandingFragment;
import com.david0926.travity.util.SharedPreferenceUtil;
import com.github.paolorotolo.appintro.AppIntro;

public class LandingActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(LandingFragment.newInstance(R.layout.activity_landing1));
        addSlide(LandingFragment.newInstance(R.layout.activity_landing2));
        addSlide(LandingFragment.newInstance(R.layout.activity_landing3));

        showSkipButton(false);
        setProgressButtonEnabled(true);
        showSeparator(false);
        setIndicatorColor(getColor(R.color.colorPrimary), getColor(R.color.materialGray5));
        setImageNextButton(ContextCompat.getDrawable(this, R.drawable.ic_navigate_next_primary_24dp));
        setColorTransitionsEnabled(true);
        setSkipText("Skip");
        setColorSkipButton(getColor(R.color.colorPrimary));
        setSkipTextTypeface(R.font.productb);
        setDoneText("Get Started");
        setColorDoneText(getColor(R.color.colorPrimary));
        setDoneTextTypeface(R.font.productb);

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        finishLanding();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        finishLanding();
    }


    private void finishLanding() {
        SharedPreferenceUtil.put(this, "user_progress", "progress_login");
        startActivity(new Intent(LandingActivity.this, LoginActivity.class));
        finish();
    }
}
