package com.example.hyejin.kb.activity.gamemenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.hyejin.kb.activity.multiplay.MultiWaitActivity;
import com.example.hyejin.kb.R;
import com.example.hyejin.kb.activity.singleplay.SinglePlayActivity;

/**
 * Created by hyejin on 2016-11-09.
 * Desc : 혼자하기, 같이하기 메뉴 보여주기
 */
public class MenuActivity extends AppCompatActivity {
    ImageView singleBtn;
    ImageView multiBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        singleBtn = (ImageView) findViewById(R.id.singlebtn);
        multiBtn = (ImageView) findViewById(R.id.multibtn);

        singleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SinglePlayActivity.class);
                startActivity(intent);
            }
        });
        multiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MultiWaitActivity.class);
                startActivity(intent);
            }
        });

    }
}
