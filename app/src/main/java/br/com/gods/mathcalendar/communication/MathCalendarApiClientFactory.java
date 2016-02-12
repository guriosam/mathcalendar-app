package br.com.gods.mathcalendar.communication;


public interface MathCalendarApiClientFactory {

    MathCalendarApiClient buildApiClient();

    void setAppToken(String appToken);
    void setUserToken(String userToken);
}
