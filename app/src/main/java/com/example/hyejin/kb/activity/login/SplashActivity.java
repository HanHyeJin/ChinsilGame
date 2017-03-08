package com.example.hyejin.kb.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.example.hyejin.kb.R;
import com.example.hyejin.kb.activity.login.LoginActivity;

/**
 * Created by hyejin on 2016-11-03.
 * Desc : 스플래시
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler(){
            public void handleMessage(Message msg){
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };

        handler.sendEmptyMessageDelayed(0, 2000); // 2초 후 넘어가도록 설정.
    }
}
