package br.com.gods.mathcalendar.notifications;

import com.bowyer.app.parsesendclient.BuildConfig;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Caio on 24/01/2016.
 */
public class ApiCreater {

    private static final String PARSE_APPLICATION_ID = "f80h6WmBBeuTECiygw2jYT3OFXSATLdA1mHIHn6i";
    private static final String PARSE_REST_API_KEY = "NSNYDYHLQhDAKpr1KP9C4GIDCZvKupPv87EsA85O";
    private static final String serverUrl = "https://api.parse.com";
    public static Api sharedInstance;

    public static synchronized Api getInstance() {
        if (sharedInstance != null) {
            return sharedInstance;
        }

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestInterceptor.RequestFacade request) {
                request.addHeader(PARSE_APPLICATION_ID, EnvConst.PARSE_APPLICATION_ID);
                request.addHeader(PARSE_REST_API_KEY, EnvConst.PARSE_REST_API_KEY);
            }
        };
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(serverUrl)
                .setRequestInterceptor(requestInterceptor)
                .setClient(new OkClient(new OkHttpClient()));

        if (BuildConfig.DEBUG) {
            builder.setLogLevel(RestAdapter.LogLevel.FULL).setLog(new BetterLog("RETROFIT"));
        }

        return sharedInstance = builder.build().create(Api.class);
    }
}