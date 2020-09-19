package com.david0926.travity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.david0926.travity.util.SharedPreferenceUtil;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            String userProgress = (String) SharedPreferenceUtil
                    .getString(this, "user_progress", "progress_welcome");

            switch (userProgress) {
                case "progress_welcome":
                    startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
                    break;

                case "progress_on_board":
                    startActivity(new Intent(SplashActivity.this, LandingActivity.class));
                    break;

                default:
                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    startActivity(new Intent(SplashActivity.this,
                            firebaseAuth.getCurrentUser() == null ?
                                    LoginActivity.class : MainActivity.class));
                    break;
            }

            finish();
        }, 2000);
    }
}
