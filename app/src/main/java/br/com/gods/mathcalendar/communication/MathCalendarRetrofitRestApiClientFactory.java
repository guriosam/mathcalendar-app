package br.com.gods.mathcalendar.communication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import br.com.gods.mathcalendar.BuildConfig;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;


public class MathCalendarRetrofitRestApiClientFactory implements MathCalendarApiClientFactory {

    private RestAdapter restAdapter;
    private MathCalendarApiRequestInterceptor requestInterceptor;

    private String appVersion;
    private String appToken;
    private String userToken;

    public MathCalendarRetrofitRestApiClientFactory(String appVersion, String appToken, String userToken) {

        this.appVersion = ""; //appVersion;
        this.appToken = ""; //appToken;
        this.userToken = ""; //userToken;
        this.requestInterceptor = new MathCalendarApiRequestInterceptor(appVersion, appToken, userToken);

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        OkHttpClient httpClient = new OkHttpClient();
        httpClient.setConnectTimeout(1, TimeUnit.MINUTES);

        try {
            RestAdapter.Builder builder = new RestAdapter.Builder()
                    .setEndpoint(BuildConfig.URL_SERVER)
                    .setErrorHandler(new RetrofitErrorHandler())
                    .setConverter(new GsonConverter(gson))
                    .setClient(new OkClient(httpClient));

            builder.setRequestInterceptor(requestInterceptor);

            restAdapter = builder.build();

            if (BuildConfig.DEBUG) {
                restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
            }

        } catch (RuntimeException error) {
            error.printStackTrace();
        }
    }

    @Override
    public void setAppToken(String appToken) {
        this.appToken = appToken;
        this.requestInterceptor.setAppToken(appToken);
    }

    @Override
    public void setUserToken(String userToken) {
        this.userToken = userToken;
        this.requestInterceptor.setUserToken(userToken);
    }

    @Override
    public MathCalendarApiClient buildApiClient() {
        return restAdapter.create(MathCalendarApiClient.class);
    }

    private class MathCalendarApiRequestInterceptor implements RequestInterceptor {

        private String appVersion;
        private String appToken;
        private String userToken;

        private MathCalendarApiRequestInterceptor(String appVersion, String appToken, String userToken) {
            this.appVersion = appVersion;
            this.appToken = appToken;
            this.userToken = userToken;
        }

        public void setAppToken(String appToken) {
            this.appToken = appToken;
        }

        public void setUserToken(String userToken) {
            this.userToken = userToken;
        }

        @Override
        public void intercept(RequestFacade request) {

            StringBuilder authorizationHeader = new StringBuilder();

            if (((appToken != null) && (!"".equals(appToken))) && ((userToken == null) || "".equals(userToken))) {
                //authorizationHeader.append("Basic "); //authorizationHeader.append("token=\"");
                //authorizationHeader.append(appToken);
            }

            if ((userToken != null) && !"".equals(userToken)) {
                //authorizationHeader.append("Bearer "); //authorizationHeader.append(":");
                //authorizationHeader.append(userToken);
            }

            if (authorizationHeader.length() > 0) {
                //authorizationHeader.append("\"");
                //request.addHeader("Authorization", authorizationHeader.toString());
            }

            //request.addHeader("mobile-system", "Android");
            //request.addHeader("mobile-application-version", appVersion);
        }
    }
}
