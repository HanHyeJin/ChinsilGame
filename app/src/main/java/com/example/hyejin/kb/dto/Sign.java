package com.example.hyejin.kb.dto;

import java.util.Date;

/**
 * Created by hyejin on 2016-11-06.
 */
public class Sign {

    public static class CreateRequest {

        private String nickName;
        private String uuid;
        private String token;

        public CreateRequest(String nickName, String uuid, String token) {
            this.nickName = nickName;
            this.uuid = uuid;
            this.token = token;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public static class User {

        private Long id;
        private String uuid;
        private String nickName;
        private String token;
        private Date regdate;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getToken() { return token;}

        public void setToken(String token) { this.token = token;}

        public Date getRegdate() { return regdate; }

        public void setRegdate(Date regdate) { this.regdate = regdate; }
    }




}
