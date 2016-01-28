package br.com.gods.mathcalendar.notifications;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by Caio on 24/01/2016.
 */
public interface Api {

    class response {

        public String result;
    }

    @Headers({
            "Content-Type: application/json"
    })
    @POST("/1/push")
    void sendNotification(@Body ParsePushDto parsePushDto, Callback<response> callback);
}