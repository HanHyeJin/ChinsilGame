package com.example.hyejin.kb.activity.multiplay.play;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hyejin.kb.R;
import com.example.hyejin.kb.app.App;
import com.example.hyejin.kb.dto.First;
import com.example.hyejin.kb.dto.GameRoom;
import com.example.hyejin.kb.error.ApiError;
import com.example.hyejin.kb.error.ErrorUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hyejin on 2016-11-09.
 * Desc : 게임플레이 화면
 */
//게임을 진행하는 화면
public class MultiPlayActivity extends AppCompatActivity {

    private TextView gameQuestion;
    private EditText gameAnswer;
    private Button saveBtn;
    private boolean isOwner;
    private ProgressDialog waitDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplay);

        isOwner = isOwner(App.getInstance());
        gameQuestion = (TextView) findViewById(R.id.game_question);
        gameAnswer = (EditText) findViewById(R.id.game_answer);
        saveBtn = (Button) findViewById(R.id.que_save);

        //-- init progressDialog
        waitDialog = new ProgressDialog(this);
        waitDialog.setMessage("잠시 기다리세요...");
        waitDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        //-- 게임 질문 바인딩
        gameQuestion.setText(App.getInstance().getCard().getContent());

        //답 보여주는 화면으로 넘어감
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(gameAnswer.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "답변을 입력해주세요 :D", Toast.LENGTH_SHORT);
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            waitDialog.show();
                        }
                    });
                    App.getInstance().getHttpService().saveFirstAnswer(
                            App.getInstance().getUser().getId(),
                            App.getInstance().getGameRoom().getId(),
                            App.getInstance().getCard().getId(),
                            new First.Answer(gameAnswer.getText().toString())
                    ).enqueue(new Callback<First.Response>() {
                        @Override
                        public void onResponse(Call<First.Response> call, Response<First.Response> response) {
                            waitDialog.cancel();
                            if (response.isSuccessful()) {
                                App.getInstance().setFirstAnswer(response.body());
                                Intent intent = new Intent(getApplicationContext(), MultiAnswerActivity.class);
                                startActivity(intent);
                            } else {
                                ApiError error = ErrorUtils.parseError(response);
                                Log.e("error", error.getStatusCode() + error.getMessage());
                            }
                        }

                        @Override
                        public void onFailure(Call<First.Response> call, Throwable t) {
                            waitDialog.cancel();
                            Log.e("error", t.getMessage(), t);
                            Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (isOwner) {
            App.getInstance().getHttpService().closeRoom(
                    App.getInstance().getUser().getId(),
                    App.getInstance().getGameRoom().getId()
            ).enqueue(new ExitCallback<Void>());
        } else {

            App.getInstance().getHttpService().gameRoomQuit(
                    App.getInstance().getUser().getId(),
                    App.getInstance().getGameRoom().getId()
            ).enqueue(new ExitCallback<GameRoom.Response>());
        }
    }

    private boolean isOwner(App app) {
        return app.getGameRoom().getOwnerId().equals(app.getUser().getId());
    }

    private class ExitCallback<T> implements Callback<T> {

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.isSuccessful()) {
                MultiPlayActivity.super.onBackPressed();
            } else {
                ApiError error = ErrorUtils.parseError(response);
                Log.e("error", error.getStatusCode() + error.getMessage());
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            Log.e("error", t.getMessage(), t);
            Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
        }
    }

}
