package com.david0926.travity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.david0926.travity.database.AccountManagerKt;
import com.david0926.travity.databinding.ActivityLoginBinding;
import com.david0926.travity.model.UserModel;
import com.david0926.travity.register.RegisterActivity;
import com.david0926.travity.util.UserCache;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import gun0912.tedkeyboardobserver.TedKeyboardObserver;

public class LoginActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;
    private int GOOGLE_LOGIN_INTENT_CODE = 5001;

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setActivity(this);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //scroll to bottom when keyboard up
        new TedKeyboardObserver(this).listen(isShow -> {
            binding.scrollLogin.smoothScrollTo(0, binding.scrollLogin.getBottom());
        });

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }


        //sign in button clicked
        binding.btnLoginSignin.setOnClickListener(view -> {

            binding.setOnProgress(true);
            hideKeyboard(this);

            String id = binding.getEmail(), pw = binding.getPw();

            if (TextUtils.isEmpty(id) || TextUtils.isEmpty(pw)) //empty field
                showErrorMsg(getString(R.string.error_empty_field));
            else signIn(id, pw);

        });

        //sign up button clicked
        binding.btnLoginRegi.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            overridePendingTransition(R.anim.slide_up, R.anim.slide_up_before);
        });

        //google sign in button clicked
        binding.btnLoginGoogle.setOnClickListener(view -> {
            Intent googleSignIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(googleSignIntent, GOOGLE_LOGIN_INTENT_CODE); // 구글좌가 9001이라고 말씀하시길

        });

        //finish when sign up success
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action != null && action.equals("finish_signup")) {
                    finish();
                }
            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter("finish_signup"));
    }

    private void signIn(String id, String pw) {

        //why did I code like this???

        OnCompleteListener<DocumentSnapshot> firestoreCompleteListener = task -> {

            DocumentSnapshot document = task.getResult();
            if (document != null && document.exists()) {

                //3. firebase auth (sign in)
                firebaseAuth
                        .signInWithEmailAndPassword(id, pw)
                        .addOnSuccessListener(authResult -> {
                            UserCache.setUser(this, document.toObject(UserModel.class));
                            finishSignIn();
                        })
                        .addOnFailureListener(e -> {
                            String errorMsg = e.getLocalizedMessage();
                            if (errorMsg == null) return;

                            if (errorMsg.contains("password is invalid"))
                                showErrorMsg(getString(R.string.error_password_invalid));
                            else showErrorMsg(errorMsg);
                        });

            } else showErrorMsg(getString(R.string.error_no_user_data));
        };

        OnCompleteListener<SignInMethodQueryResult> emailCheckCompleteListener = task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                List<String> signInMethods = task.getResult().getSignInMethods();
                if (signInMethods != null && !signInMethods.isEmpty()) {

                    //2. firestore (user data check)
                    firebaseFirestore
                            .collection("users")
                            .document(id)
                            .get()
                            .addOnCompleteListener(firestoreCompleteListener);

                } else showErrorMsg(getString(R.string.error_no_user_account));
            } else showErrorMsg(getString(R.string.error_email_invalid));
        };

        //1. firebase auth (account exist check)
        firebaseAuth
                .fetchSignInMethodsForEmail(id)
                .addOnCompleteListener(emailCheckCompleteListener);
    }

    private void finishSignIn() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.slide_down_before, R.anim.slide_down);
        finish();
    }

    private void showErrorMsg(String msg) {
        binding.setOnProgress(false);
        binding.txtLoginError.setVisibility(View.VISIBLE);
        binding.txtLoginError.setText(msg);
        binding.txtLoginError.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
    }

    private void hideKeyboard(Activity activity) {
        View v = activity.getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GOOGLE_LOGIN_INTENT_CODE) { // 구글 로그인창 코드
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                e.printStackTrace();
                showErrorMsg("구글 API 에러");
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            UserModel um = new UserModel();
                            um.setEmail(user.getEmail());
                            um.setName(user.getDisplayName());
                            UserCache.setUser(LoginActivity.this, um);
                            AccountManagerKt.addGoogleAccount(user.getDisplayName(),user.getEmail());
                            finishSignIn();

                        } else {
                            showErrorMsg("구글 로그인에 실패했습니다.");
                        }
                    }
                });
    }

}
