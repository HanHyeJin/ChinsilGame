package com.example.hyejin.kb.error;

/**
 * Created by Taehoon Yoo
 * User  : NOEP
 * Date  : 2016. 12. 2.
 * Time  : 오전 12:31
 * Page  : http:noep.github.io
 * Email : noep@naver.com
 * Desc  :
 */
public class ApiError {

    private int statusCode;
    private String message;

    public ApiError() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
