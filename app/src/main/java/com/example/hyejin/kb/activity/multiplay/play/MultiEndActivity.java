package com.example.hyejin.kb.activity.multiplay.play;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.hyejin.kb.R;

/**
 * Created by hyejin on 2016-11-09.
 */

//게임의 마지막 화면으로 모든 사람들의 정답을 공개하는 화면
public class MultiEndActivity extends AppCompatActivity {
    ListView endListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiend);

        endListView = (ListView) findViewById(R.id.listview_answer_check);
    }

}