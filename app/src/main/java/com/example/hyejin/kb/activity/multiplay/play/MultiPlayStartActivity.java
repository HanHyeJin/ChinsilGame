package com.example.hyejin.kb.activity.multiplay.play;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hyejin.kb.R;
import com.example.hyejin.kb.app.App;
import com.example.hyejin.kb.dto.Card;
import com.example.hyejin.kb.dto.GameRoom;
import com.example.hyejin.kb.error.ApiError;
import com.example.hyejin.kb.error.ErrorUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hyejin on 2016-11-09.
 * 게임 대기화면 activity
 */
public class MultiPlayStartActivity extends AppCompatActivity {

    private ImageView startBtn;
    private TextView textView;
    private boolean isOwner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_multistart);

        isOwner = isOwner(App.getInstance());
        startBtn = (ImageView) findViewById(R.id.startbtn);
        textView = (TextView) findViewById(R.id.activity_multistart_textview);

        if (isOwner) {
            startBtn.setVisibility(View.VISIBLE);
            textView.setText(R.string.multiplay_start_textview_owner);
        } else {
            startBtn.setVisibility(View.INVISIBLE);
            textView.setText(R.string.multiplay_start_textview_normal);
        }

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                App.getInstance().getHttpService().gameStart(
                        App.getInstance().getUser().getId(),
                        App.getInstance().getGameRoom().getId()
                ).enqueue(new Callback<Card.Response>() {
                    @Override
                    public void onResponse(Call<Card.Response> call, Response<Card.Response> response) {
                        if (response.isSuccessful()) {
                            App.getInstance().setCard(response.body());
                            Intent intent = new Intent(getApplicationContext(), MultiPlayActivity.class);
                            startActivity(intent);

                        } else {
                            ApiError error = ErrorUtils.parseError(response);
                            Log.e("error", error.getStatusCode() + error.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<Card.Response> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d("debug",intent.getExtras().getString("test"));
        super.onNewIntent(intent);
    }

    @Override
    public void onBackPressed() {
        if (isOwner) {
            //--todo 실시간으로 방 인원이 바뀔때마다 알림이 와야 함
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
                MultiPlayStartActivity.super.onBackPressed();
            } else {
                ApiError error = ErrorUtils.parseError(response);
                Log.e("error", error.getStatusCode() + error.getMessage());
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
        }
    }

}