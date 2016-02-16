package br.com.gods.mathcalendar.communication;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;


public interface MathCalendarApiClient {

    @FormUrlEncoded
    @POST("forms/d/1tHtUlccnw322kjb9KTbDaczqwxpNyLh3gxHUjmILnVI/formResponse")
    void userData(@Field("name") String first
                  //@Field("age") String second,
                  //@Field("email") String third,
                  /*@Field("vip") String fourth*/, Callback<Response> callback);
}