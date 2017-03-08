package com.example.hyejin.kb.dto;

/**
 * Created by Taehoon Yoo
 * User  : NOEP
 * Date  : 2016. 12. 1.
 * Time  : 오후 10:13
 * Page  : http:noep.github.io
 * Email : noep@naver.com
 * Desc  :
 */

public class Card {

    public static class Response {

        private Long id;
        private String title;
        private String content;
        private String pictureUri;

        //-- for push
        private Long gameRoomId;
        private Long userId;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPictureUri() {
            return pictureUri;
        }

        public void setPictureUri(String pictureUri) {
            this.pictureUri = pictureUri;
        }

        public Long getGameRoomId() { return gameRoomId; }

        public void setGameRoomId(Long gameRoomId) { this.gameRoomId = gameRoomId; }

        public Long getUserId() { return userId; }

        public void setUserId(Long userId) { this.userId = userId; }
    }

}
