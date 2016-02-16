package br.com.gods.mathcalendar.communication;

public interface UserService {

    void userData(String name/*, String age, String email, String vip*/, MathCalendarServiceCallback<Response> callback);
}
