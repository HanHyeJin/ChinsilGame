package com.example.hyejin.kb.activity.multiplay.create;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hyejin.kb.activity.multiplay.play.MultiPlayActivity;
import com.example.hyejin.kb.R;
import com.example.hyejin.kb.activity.multiplay.play.MultiPlayStartActivity;
import com.example.hyejin.kb.app.App;
import com.example.hyejin.kb.dto.GameRoom;
import com.example.hyejin.kb.error.ApiError;
import com.example.hyejin.kb.error.ErrorUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hyejin on 2016-11-09.
 * Desc : 멀티플레이 방 만드는 화면
 */
public class MultiPlusActivity extends AppCompatActivity {
    EditText room_name_make;
    EditText room_pwd_make;
    ImageView make_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplus);

        room_name_make = (EditText) findViewById(R.id.make_room_name);
        room_pwd_make = (EditText) findViewById(R.id.make_room_pw);
        make_btn = (ImageView) findViewById(R.id.make_room_button);

        make_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //-- 방제 필수
                if (!TextUtils.isEmpty(room_name_make.getText())) {

                    App.getInstance().getHttpService().saveGameRoom(
                            App.getInstance().getUser().getId(),
                            new GameRoom.Create(
                                    room_name_make.getText().toString(),
                                    room_pwd_make.getText().toString()))
                            .enqueue(new Callback<GameRoom.Response>() {

                                @Override
                                public void onResponse(Call<GameRoom.Response> call, Response<GameRoom.Response> response) {
                                    if (response.isSuccessful()) {
                                        App.getInstance().setGameRoom(response.body());

                                        Intent intent = new Intent(getApplicationContext(), MultiPlayStartActivity.class);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        ApiError error = ErrorUtils.parseError(response);
                                        Log.e("error", error.getStatusCode() + error.getMessage());
                                    }
                                }

                                @Override
                                public void onFailure(Call<GameRoom.Response> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                                }
                            });
                }

            }

        });
    }
}
