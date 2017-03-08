package com.example.hyejin.kb.activity.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.hyejin.kb.activity.gamemenu.MenuActivity;
import com.example.hyejin.kb.R;
import com.example.hyejin.kb.app.App;
import com.example.hyejin.kb.dto.Sign;
import com.example.hyejin.kb.error.ApiError;
import com.example.hyejin.kb.error.ErrorUtils;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hyejin on 2016-11-03.
 * Desc : 로그인 화면
 */
public class LoginActivity extends AppCompatActivity {
    private AutoCompleteTextView mEditId;
    //    private EditText mEditPw;
    private View mProgressView;
    private View mLoginFormView;
    private ArrayList<Object> dataList = new ArrayList<Object>();
    private Button mSignInButton;
    private ProgressDialog waitDialog;

    // 로그인 정보 저장장
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        //-- init progressDialog
        waitDialog = new ProgressDialog(this);
        waitDialog.setMessage("잠시 기다리세요...");
        waitDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        setTitle("로그인");
        pref = getSharedPreferences("pref", MODE_PRIVATE);
        editor = pref.edit();
        mEditId = (AutoCompleteTextView) findViewById(R.id.editTextId);

        mEditId.requestFocus();
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.showSoftInput(mEditId, InputMethodManager.SHOW_FORCED);

        mSignInButton = (Button) findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {


        // Reset errors.
        mEditId.setError(null);
//        mEditPw.setError(null);

        // Store values at the time of the login attempt.
        String id = mEditId.getText().toString();
//        String password = mEditPw.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid id.
        if (id.equals("")) {
            mEditId.setError(getString(R.string.error_invalid_email));
            focusView = mEditId;
            cancel = true;
        }
        // Check for a valid password, if the user entered one.
//        if (password.equals("")) {
//            mEditPw.setError(getString(R.string.error_invalid_password));
//            focusView = mEditPw;
//            cancel = true;
//        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

            Sign.CreateRequest createRequest = new Sign.CreateRequest(
                    mEditId.getText().toString(),
                    UUID.randomUUID().toString(),
                    FirebaseInstanceId.getInstance().getToken()
            );

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    waitDialog.show();
                }
            });

            App.getInstance().getHttpService().signIn(createRequest).enqueue(new Callback<Sign.User>() {
                @Override
                public void onResponse(Call<Sign.User> call, Response<Sign.User> response) {

                    waitDialog.cancel();
                    if (response.isSuccessful()) {
                        //-- set user
                        App.getInstance().setUser(response.body());
                        Toast.makeText(getApplicationContext(), "안녕하세요 " + App.getInstance().getUser().getNickName() + "님", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        Log.i("info", "log test log test");
                        startActivity(intent);
                        finish();
                    } else {
                        ApiError error = ErrorUtils.parseError(response);
                        Log.e("error", error.getStatusCode() + error.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<Sign.User> call, Throwable t) {
                    waitDialog.cancel();
                    Log.e("error", t.getLocalizedMessage(), t);
                    Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}



