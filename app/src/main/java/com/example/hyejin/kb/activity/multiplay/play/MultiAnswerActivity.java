package com.example.hyejin.kb.activity.multiplay.play;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.hyejin.kb.R;

/**
 * Created by hyejin on 2016-11-09.
 */
//Todo 서버와 붙여야함

public class MultiAnswerActivity extends AppCompatActivity {
    public ListView answerListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multianswer);

        answerListView = (ListView) findViewById(R.id.listview_answer);

    }
}