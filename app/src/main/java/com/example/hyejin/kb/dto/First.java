package com.example.hyejin.kb.dto;

/**
 * Created by Taehoon Yoo
 * User  : NOEP
 * Date  : 2016. 12. 18.
 * Time  : 오후 10:07
 * Page  : http:noep.github.io
 * Email : noep@naver.com
 * Desc  :
 */
public class First {

    public static class Answer {
        private String answer;

        public Answer(String answer) {
            this.answer = answer;
        }

        public String getAnswer() { return answer; }
        public void setAnswer(String answer) { this.answer = answer; }
    }

    public static class Response {

        private Long id;
        private String answer;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getAnswer() { return answer; }
        public void setAnswer(String answer) { this.answer = answer; }
    }
}
