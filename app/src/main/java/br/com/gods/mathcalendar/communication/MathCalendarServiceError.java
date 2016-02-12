package br.com.gods.mathcalendar.communication;

import com.google.gson.annotations.SerializedName;


public class MathCalendarServiceError {

    @SerializedName("error")
    public boolean error2;

    @SerializedName("applicationMessage")
    public String response;

    private String message;
    private Throwable error;


    /*public MathCalendarServiceError(String message, Throwable error) {
        this.message = message;
        this.error = error;
    }

    public MathCalendarServiceError(String message) {
        this.message = message;
    }

    public MathCalendarServiceError(Throwable error) {
        this.error = error;
    }

    public boolean isError2() {
        return error2;
    }

    public void setError2(boolean error2) {
        this.error2 = error2;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }*/
}
