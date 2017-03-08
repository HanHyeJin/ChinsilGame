package com.example.hyejin.kb.activity.multiplay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hyejin.kb.activity.multiplay.play.MultiPlayStartActivity;
import com.example.hyejin.kb.activity.multiplay.create.MultiPlusActivity;
import com.example.hyejin.kb.R;
import com.example.hyejin.kb.adapter.GameRoomAdaptor;
import com.example.hyejin.kb.app.App;
import com.example.hyejin.kb.dto.GameRoom;
import com.example.hyejin.kb.error.ApiError;
import com.example.hyejin.kb.error.ErrorUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hyejin on 2016-11-09.
 * Desc : 멀티플레이 방 목록 보여주기
 * MultiWait  > MultiPlayStart> MultiPlay > MultiAnswer > MultiEnd
 * MultiWait > MultiPlus  > MultiPlayStart> MultiPlay > MultiAnswer > MultiEnd
 */
public class MultiWaitActivity extends AppCompatActivity {
    public ListView m_ListView;
    private List<GameRoom.Response> gameRoomList;
    private GameRoomAdaptor gameRoomAdaptor;
    private ImageView plus_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiwait);

        m_ListView = (ListView) findViewById(R.id.listview_room);
        plus_btn = (ImageView) findViewById(R.id.plus_btn);
        //-- todo 방 하나씩 갱신될 때 마다 새로고침

        App.getInstance().getHttpService().getGameRooms(App.getInstance().getUser().getId())
                .enqueue(new Callback<List<GameRoom.Response>>() {
                    @Override
                    public void onResponse(Call<List<GameRoom.Response>> call, Response<List<GameRoom.Response>> response) {
                        if (response.isSuccessful()) {
                            gameRoomList = response.body();
                            gameRoomAdaptor = new GameRoomAdaptor(gameRoomList,
                                    (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE));
                            m_ListView.setAdapter(gameRoomAdaptor);

                        } else {
                            ApiError error = ErrorUtils.parseError(response);
                            Log.e("error", error.getStatusCode() + error.getMessage());
                        }

                    }

                    @Override
                    public void onFailure(Call<List<GameRoom.Response>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();

                    }
                });


        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MultiPlusActivity.class);
                startActivity(intent);
            }
        });

        m_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //--todo 방에 비밀번호가 있는 지 체크해서 있으면 비밀번호 입력받는 창 열어줘야 함
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long gameRoomId) {
                App.getInstance().getHttpService().joinGameRoom(
                        App.getInstance().getUser().getId(),
                        gameRoomId,
                        new GameRoom.Join(""))
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
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        App.getInstance().getHttpService().getGameRooms(App.getInstance().getUser().getId())
                .enqueue(new Callback<List<GameRoom.Response>>() {
                    @Override
                    public void onResponse(Call<List<GameRoom.Response>> call, Response<List<GameRoom.Response>> response) {
                        if (response.isSuccessful()) {
                            gameRoomAdaptor.setList(response.body());
                        } else {
                            ApiError error = ErrorUtils.parseError(response);
                            Log.e("error", error.getStatusCode() + error.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GameRoom.Response>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
