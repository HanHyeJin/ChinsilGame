package com.example.hyejin.kb.error;

import com.example.hyejin.kb.app.App;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by Taehoon Yoo
 * User  : NOEP
 * Date  : 2016. 12. 2.
 * Time  : 오전 12:31
 * Page  : http:noep.github.io
 * Email : noep@naver.com
 * Desc  :
 */
public class ErrorUtils {
    public static ApiError parseError(Response<?> response) {
        Converter<ResponseBody, ApiError> converter =
                App.getInstance().getRetrofit()
                        .responseBodyConverter(ApiError.class, new Annotation[0]);
        ApiError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiError();
        }

        return error;
    }
}
