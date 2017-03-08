package com.example.hyejin.kb.app;

import android.app.Application;

import com.example.hyejin.kb.dto.Card;
import com.example.hyejin.kb.dto.First;
import com.example.hyejin.kb.dto.GameRoom;
import com.example.hyejin.kb.dto.HttpService;
import com.example.hyejin.kb.dto.Sign;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hyejin on 2016-11-06.
 */
public class App extends Application {

    private static App instance;
    private Retrofit retrofit;
    private HttpService httpService;
    private Sign.User user;
    private GameRoom.Response gameRoom;
    private Card.Response card;
    private First.Response firstAnswer;

    public static final String ENDPOINT = "http://soon.japanwest.cloudapp.azure.com:3005/";



    @Override
    public void onCreate() {
        super.onCreate();

        App.instance = this;

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        httpService = retrofit.create(HttpService.class);
    }

    public static App getInstance() { return instance; }
    public HttpService getHttpService() { return httpService; }

    public Sign.User getUser() { return user; }
    public void setUser(Sign.User user) { this.user = user; }
    public GameRoom.Response getGameRoom() { return gameRoom; }
    public void setGameRoom(GameRoom.Response gameRoom) { this.gameRoom = gameRoom; }
    public Card.Response getCard() { return card; }
    public void setCard(Card.Response card) { this.card = card; }
    public First.Response getFirstAnswer() { return firstAnswer; }
    public void setFirstAnswer(First.Response firstAnswer) { this.firstAnswer = firstAnswer; }

    public Retrofit getRetrofit() {
        return retrofit;
    }



}
