package com.example.hyejin.kb.dto;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by hyejin on 2016-11-06.
 */

public interface HttpService {

    /**
     * 로그인 요청 API
     * @param createRequest
     * @return
     */
    @POST("sign/in")
    Call<Sign.User> signIn(@NonNull @Body Sign.CreateRequest createRequest);

    /**
     * 카드정보 가져오기
     * @param id
     * @return
     */
    @GET("cards/{id}")
    Call<Card.Response> getCard(@Path("id") Long id);

    /**
     * 방 만들기
     * @param id
     * @param Create.name 방 이름 (필수)
     * @param Create.password 방 입장 패스워드 (선택)
     * @return
     */
    @POST("users/{id}/gamerooms")
    Call<GameRoom.Response> saveGameRoom(@Path("id") Long id, @Body GameRoom.Create create);

    /**
     * 방 퇴장하기 (방장만 가능, 팀원들이 아무도 없어야 퇴장가능)
     * @param id
     * @param gameRoomId
     * @return
     */
    @DELETE("users/{id}/gamerooms/{gameRoomId}")
    Call<Void> closeRoom(@Path("id") Long id, @Path("gameRoomId") Long gameRoomId);

    /**
     * 현재 방 리스트 가져오기
     * @param id
     * @return
     */
    @GET("users/{id}/gamerooms")
    Call<List<GameRoom.Response>> getGameRooms(@Path("id") Long id);

    /**
     * 방 입장하기
     * @param id
     * @param gameRoomId
     * @param join.password 입장 패스워드 (선택)
     * @return
     */
    @PUT("users/{id}/gamerooms/{gameRoomId}/join")
    Call<GameRoom.Response> joinGameRoom(@Path("id") Long id, @Path("gameRoomId") Long gameRoomId,
                                         @Body GameRoom.Join join);

    /**
     * 게임 시작하기 (방장만 가능)
     * @param id
     * @param gameRoomId
     * @return
     */
    @PUT("users/{id}/gamerooms/{gameRoomId}/start")
    Call<Card.Response> gameStart(@Path("id") Long id, @Path("gameRoomId") Long gameRoomId);

    /**
     * 방 나가기 ( 방장 빼고 나머지들만 가능 )
     * @param id
     * @param gameRoomId
     * @return
     */
    @PUT("users/{id}/gamerooms/{gameRoomId}/quit")
    Call<GameRoom.Response> gameRoomQuit(@Path("id") Long id, @Path("gameRoomId") Long gameRoomId);


    /**
     * 첫번째 카드 답변 등록하기
     * @param id
     * @param gameRoomId
     * @param cardId
     * @param answer
     * @return
     */
    @POST("users/{id}/gamerooms/{gameRoomId}/cards/{cardId}")
    Call<First.Response> saveFirstAnswer(@Path("id") Long id,
                                         @Path("gameRoomId") Long gameRoomId,
                                         @Path("cardId") Long cardId,
                                         @Body First.Answer answer);

}
