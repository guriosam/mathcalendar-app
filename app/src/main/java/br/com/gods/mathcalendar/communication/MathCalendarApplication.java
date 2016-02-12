package br.com.gods.mathcalendar.communication;


import android.app.Application;

//import com.google.android.gms.analytics.GoogleAnalytics;
//import com.google.android.gms.analytics.Tracker;

//import java.util.HashMap;

public class MathCalendarApplication extends Application {

    //private static final String PROPERTY_ID = "UA-XXXXX-Y";
    //private HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    private static MathCalendarApplication applicationContext;
    //private NotificationCenter notificationCenter;

    private MathCalendarApiClient apiClient;

    private MathCalendarServiceFactory serviceFactory;

    public static MathCalendarApplication getMathCalendarApplicationContext() {

        if (applicationContext == null) {
            throw new RuntimeException("O contexto da aplicação não foi iniciado.");
        }

        return applicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        buildMathCalendarApiClient();

        //this.notificationCenter = new NotificationCenter(this);
        this.serviceFactory = new MathCalendarServiceFactory(apiClient);

        registerServices();

        MathCalendarApplication.applicationContext = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        /*if (this.notificationCenter != null) {
            this.notificationCenter.destroyDefaultNotificationCenter(this);
            this.notificationCenter = null;
        }*/
        MathCalendarApplication.applicationContext = null;
    }

    /*public NotificationCenter getNotificationCenter() {
        return this.notificationCenter;
    }*/

    public MathCalendarServiceFactory getServiceFactory() {
        return this.serviceFactory;
    }

    /*public void registerAppToken(String appToken) {
        apiClientFactory.setAppToken(appToken);
    }*/


    /*public void registerUserToken(String userToken) {
        apiClientFactory.setUserToken(userToken);
    }*/

    private void buildMathCalendarApiClient() {

        String appToken = ""; //AppCache.getInstance(this).getAppToken();
        String userToken = ""; //AppCache.getInstance(this).getUserToken();
        String version = "";
        /*try {
            PackageInfo packageInfo = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Erro ao acessar a versão do aplicativo", e);
        }*/

        MathCalendarApiClientFactory apiClientFactory = new MathCalendarRetrofitRestApiClientFactory(version, appToken, userToken);

        apiClient = apiClientFactory.buildApiClient();
    }

    private void registerServices() {
        serviceFactory.registerService(UserService.class, UserServiceImplementation.class);
    }
}