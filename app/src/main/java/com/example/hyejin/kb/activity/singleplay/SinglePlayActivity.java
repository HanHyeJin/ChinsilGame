package com.example.hyejin.kb.activity.singleplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hyejin.kb.R;

/**
 * Created by hyejin on 2016-11-09.
 */
//// TODO: 2016-11-30 후에 하기

public class SinglePlayActivity extends AppCompatActivity {
    EditText singleAnswer;
    Button singleSavebtn;
    TextView singleQue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleplay);

        singleAnswer = (EditText) findViewById(R.id.single_game_answer);
        singleSavebtn = (Button) findViewById(R.id.single_que_save);
        singleQue = (TextView) findViewById(R.id.single_game_question);


    }
}
