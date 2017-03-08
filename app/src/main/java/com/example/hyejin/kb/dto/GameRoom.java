package com.example.hyejin.kb.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by Taehoon Yoo
 * User  : NOEP
 * Date  : 2016. 12. 1.
 * Time  : 오후 10:11
 * Page  : http:noep.github.io
 * Email : noep@naver.com
 * Desc  :
 */
public class GameRoom {

    public enum Status {READY, START}

    public static class Create {
        private String name;
        private String password;

        public Create(String name, String password) {
            this.name = name;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class Response {

        private Long id;
        private Status status;
        private String name;
        private String password;
        private Integer count;
        private Integer maximum;
        private Long ownerId;
        private List<Sign.User> userList;
        private Card card;
        private Date regdate;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id;  }
        public Status getStatus() { return status; }
        public void setStatus(Status status) {  this.status = status;  }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public Integer getCount() { return count; }
        public void setCount(Integer count) { this.count = count; }
        public Integer getMaximum() { return maximum; }
        public void setMaximum(Integer maximum) {  this.maximum = maximum;}
        public Long getOwnerId() { return ownerId; }
        public void setOwnerId(Long ownerId) { this.ownerId = ownerId;}
        public List<Sign.User> getUserList() { return userList; }
        public void setUserList(List<Sign.User> userList) { this.userList = userList;}
        public Card getCard() { return card; }
        public void setCard(Card card) { this.card = card;}
        public Date getRegdate() { return regdate; }
        public void setRegdate(Date regdate) { this.regdate = regdate;}
    }


    public static class Join {
        private String password;

        public Join(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
