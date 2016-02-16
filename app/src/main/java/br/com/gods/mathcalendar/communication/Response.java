package br.com.gods.mathcalendar.communication;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Response implements Serializable {

    @SerializedName("response")
    private boolean response;


    public Response() {}

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
}
