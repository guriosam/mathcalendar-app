package br.com.gods.mathcalendar.communication;


public interface MathCalendarServiceCallback<T> {

    void success(T response);
    void failure(MathCalendarServiceError error);
}
