package br.com.gods.mathcalendar.communication;


public class AppCache {

    //private static Context context;
    //private static AppCache instance = null;
    //private SharedPreferences sharedPreferences;

    //###### SOCIAL NETWORKS INFO
    //private Set<String> socialNetworks;

    //###### USER INFORMATION
    //private String personName = "";
    //private String userMail = "";
    //private String profilePicture = "";
    //private String password = "";
    //private String localProfilePicture = "";

    //###### TOKEN    
    //private String appToken = "MzAyYTdkNTU2MTc1MjY0YzdlNWIzMjY4Mjc0OTczNDk6NDc3MDQxNGMyODNhMjAzNDdjN2I1NTM2NTA0MjU3NzM=";

    //private String userToken = "5b95567c-e2d4-4b22-a739-aaef98514d45";
    //private String userToken = "";

    //###### USER LOGGED
    //private boolean userLogged;  // Replaced: before was false

    //###### TOKENS
    //private String twitterToken = "";
    //private String twitterToken2 = "";
    //private String linkedinToken = "";
    //private String linkedinSecret = "";
    //private String instagramToken = "";
    //private String instagramCallback = "";
    //private String youtubeToken = "";
    //private String youtubeCallback = "";
    //private String tumblrToken = "";
    //private String tumblrSecret = "";
    //private int expiresIn = 0;
    //private HashMap<String, Boolean> validNetworks = new HashMap<>();


    /*public String getTumblrToken() {
        tumblrToken = sharedPreferences.getString("tumblrToken", "");
        return tumblrToken;
    }*/

    /*public void setTumblrToken(String tumblrToken) {
        preferenceEditor("tumblrToken", tumblrToken);
        this.tumblrToken = tumblrToken;
    }

    *//*public String getTumblrSecret() {
        tumblrSecret = sharedPreferences.getString("tumblrSecret", "");
        return tumblrSecret;
    }*//*

    public void setTumblrSecret(String tumblrSecret) {
        preferenceEditor("tumblrSecret", tumblrSecret);
        this.tumblrSecret = tumblrSecret;
    }

    private AppCache(Context mContext) {
        sharedPreferences = mContext.getSharedPreferences("WIKIPASS", Context.MODE_PRIVATE);
    }*/

    /*public static AppCache getInstance(Context mContext) {
        context = mContext;

        if (instance == null) {
            instance = new AppCache(mContext);
        }

        return instance;
    }*/

    /*private void preferenceEditor(String preference, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preference, value);
        editor.commit();
    }*/

    /*public String getAppToken() {
        //this.appToken = sharedPreferences.getString("appToken", "");
        return appToken;
    }

    public void setAppToken(String token) {
        //preferenceEditor("appToken", token);
        this.appToken = token;
    }

    public String getUserToken() {
        this.userToken = sharedPreferences.getString("userToken", "");
        return userToken;
    }*/

    /*public void setUserToken(String token) {
        preferenceEditor("userToken", token);
        this.userToken = token;
    }

    *//*public String getPersonName() {
        personName = sharedPreferences.getString("personName", "");
        return personName;
    }*//*

    public void setPersonName(String name) {
        preferenceEditor("personName", name);
        this.personName = name;
    }

    *//*public String getUserMail() {
        userMail = sharedPreferences.getString("userMail", "");
        return userMail;
    }*//*

    public void setUserMail(String mail) {
        preferenceEditor("userMail", mail);
        this.userMail = mail;
    }

    public void setPassword(String password) {
        preferenceEditor("password", password);
        this.password = password;
    }

    *//*public String getPassword() {
        password = sharedPreferences.getString("password", "");
        return password;
    }

    public String getProfilePicture() {
        profilePicture = sharedPreferences.getString("profilePicture", "");
        return profilePicture;
    }*//*

    public void setProfilePicture(String picture) {
        preferenceEditor("profilePicture", picture);
        this.profilePicture = picture;
    }

    *//*public void setLocalProfilePicture(String picture) {
        this.localProfilePicture = picture;
    }

    public boolean isUserLogged() {
        userLogged = sharedPreferences.getBoolean("userLogged", false);
        return userLogged;
    }*//*

    public void setUserLogged(boolean userLogged) {
        this.userLogged = userLogged;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("userLogged", userLogged);
        editor.commit();
    }

    *//*public Set<String> getSocialNetworks() {
        socialNetworks = sharedPreferences.getStringSet("socialNetworks", null);
        return socialNetworks;
    }

    public void setSocialNetworks(List<String> socialNetworks) {

        this.socialNetworks = new HashSet<String>(socialNetworks);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> stringSet = new HashSet<String>(socialNetworks);
        editor.putStringSet("socialNetworks", stringSet);
        editor.commit();
    }

    public String getTwitterToken() {
        twitterToken = sharedPreferences.getString("twitterToken", "");
        return twitterToken;
    }*//*

    public void setTwitterToken(String token) {
        preferenceEditor("twitterToken", token);
        this.twitterToken = token;
    }*/

    /*public String getTwitterToken2() {
        twitterToken2 = sharedPreferences.getString("twitterToken2", "");
        return twitterToken2;
    }*/

    /*public void setTwitterToken2(String token) {
        preferenceEditor("twitterToken2", token);
        this.twitterToken2 = token;
    }

    *//*public String getLinkedinToken() {
        linkedinToken = sharedPreferences.getString("linkedinToken", "");
        return linkedinToken;
    }*//*

    public void setLinkedinToken(String token) {
        preferenceEditor("linkedinToken", token);
        this.linkedinToken = token;
    }

    *//*public String getLinkedinSecret() {
        linkedinSecret = sharedPreferences.getString("linkedinSecret", "");
        return linkedinSecret;
    }*//*

    public void setLinkedinSecret(String secret) {
        preferenceEditor("linkedinSecret", secret);
        this.linkedinSecret = secret;
    }

    *//*public String getInstagramToken() {
        instagramToken = sharedPreferences.getString("instagramToken", "");
        return instagramToken;
    }*//*

    public void setInstagramToken(String instagramToken) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        preferenceEditor("instagramToken", instagramToken);
        editor.commit();
        this.instagramToken = instagramToken;
    }

    *//*public String getInstagramCallback() {
        instagramCallback = sharedPreferences.getString("instagramCallback", "");
        return instagramCallback;
    }*//*

    public void setInstagramCallback(String instagramCallback) {
        preferenceEditor("instagramCallback", instagramCallback);
        this.instagramCallback = instagramCallback;
    }

    *//*public String getYoutubeToken() {
        youtubeToken = sharedPreferences.getString("youtubeToken", "");
        return youtubeToken;
    }*//*

    public void setYoutubeToken(String token) {
        preferenceEditor("youtubeToken", token);
        this.youtubeToken = token;
    }

    *//*public String getYoutubeCallback() {
        youtubeCallback = sharedPreferences.getString("youtubeCallback", "");
        return youtubeCallback;
    }*//*

    public void setYoutubeCallback(String youtubeCallback) {
        preferenceEditor("youtubeCallback", youtubeCallback);
        this.youtubeCallback = youtubeCallback;
    }*/

    /*public void clearPreferences() {
        setPassword("");
        setProfilePicture("");
        setTwitterToken2("");
        setTwitterToken("");
        setInstagramToken("");
        setUserToken("");
        setInstagramCallback("");
        setLinkedinSecret("");
        setLinkedinToken("");
        setPersonName("");
        setTumblrSecret("");
        setTumblrToken("");
        setUserLogged(false);
        setYoutubeToken("");
        setYoutubeCallback("");
        setUserMail("");
        setExpiresIn(0);
    }*/

    /*public void setExpiresIn(int expiresIn) {
        preferenceEditor("expires", Integer.toString(expiresIn));
        this.expiresIn = expiresIn;
    }*/

    /*public int getExpiresIn() {
        personName = sharedPreferences.getString("expires", "");
        expiresIn = Integer.valueOf(personName);
        return expiresIn;
    }

    public String getLocalProfilePicture() {
        return localProfilePicture;
    }

    public void addValidNetwork(String name, boolean valid) {
        validNetworks.put(name, valid);
    }

    public String isValidNetwork(String name) {

        if(validNetworks.get(name) != null){
            if(validNetworks.get(name)){
                return "ON";
            } else {
                return "OFF";
            }
        }

        return "NEW";
    }*/
}
