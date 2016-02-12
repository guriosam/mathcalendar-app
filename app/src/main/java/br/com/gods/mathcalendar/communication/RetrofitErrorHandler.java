package br.com.gods.mathcalendar.communication;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;


public class RetrofitErrorHandler implements ErrorHandler {

    @Override
    public Throwable handleError(RetrofitError cause) {
        return cause;
    }
}
