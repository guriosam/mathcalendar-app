package br.com.gods.mathcalendar;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import br.com.gods.mathcalendar.utils.LocalCache;

public class LoginActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        if (LocalCache.getInstance().isUserLogged()) {
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //EditText email = (EditText) findViewById(R.id.email_input);
        //EditText password = (EditText) findViewById(R.id.password_input);

        //Button confirm = (Button) findViewById(R.id.confirm_button);
        //Button forgot = (Button) findViewById(R.id.forgot_button);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        //loginButton.setReadPermissions("email");
        //loginButton.setPublishPermissions("user_birthday");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @TargetApi(Build.VERSION_CODES.KITKAT)
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    final GraphResponse response) {
                                /*final UserData userData = new UserData();
                                if (!Objects.equals(response.getJSONObject().getString("id"), ""))
                                    userData.setId(response.getJSONObject().getString("id"));
                                if (!Objects.equals(response.getJSONObject().getString("name"), ""))
                                    userData.setName(response.getJSONObject().getString("name"));
                                if (!Objects.equals(response.getJSONObject().getString("birthday"), ""))
                                    userData.setBirthday(response.getJSONObject().getString("birthday"));
                                if (!Objects.equals(response.getJSONObject().getString("email"), ""))
                                    userData.setEmail(response.getJSONObject().getString("email"));
                                if (!Objects.equals(response.getJSONObject().getString("gender"), ""))
                                    userData.setGender(response.getJSONObject().getString("gender"));
                                if (!Objects.equals(response.getJSONObject().getString("link"), ""))
                                    userData.setLink(response.getJSONObject().getString("link"));
                                if (!Objects.equals(response.getJSONObject().getString("timezone"), ""))
                                    userData.setTimezone(response.getJSONObject().getString("timezone"));
                                if (!Objects.equals(response.getJSONObject().getString("cover"), ""))
                                    userData.setCover(response.getJSONObject().getString("cover"));
                                userData.setVip("FALSE");*/

                                Thread t = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //System.out.println("BEFORE SEND DATA");
                                        try {
                                            postData(response.getJSONObject().getString("id"),
                                                    response.getJSONObject().getString("name"),
                                                    null,
                                                    null,
                                                    "FALSE");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                t.start();

                                Toast.makeText(getApplicationContext(), "SUCCESS, WE'VE DATA", Toast.LENGTH_LONG).show();
                            }
                        });

                Bundle parameters = new Bundle();
                //parameters.putString("fields", "id,name,birthday,email,gender,link,timezone,cover"); //Add user_birthday, email on Facebook Review
                parameters.putString("fields", "id,name,gender,link,timezone,cover");
                request.setParameters(parameters);
                request.executeAsync();

                LocalCache.getInstance().setIsUserLogged(true);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "CANCELLED", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (LocalCache.getInstance().isUserLogged()) {
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    public void postData(String id, String name, String age, String email, String vip) {

        /*new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                processJson(object);
            }
        }).execute("https://spreadsheets.google.com/tq?key=1imXumnjFyOan7V92ZYCeWUnYbRwI8yXJH_PBTJNyGvw");*/

        try {

            URL urlSpreadsheet = new URL("https://docs.google.com/forms/d/1tHtUlccnw322kjb9KTbDaczqwxpNyLh3gxHUjmILnVI/formResponse");
            HttpsURLConnection connection = (HttpsURLConnection) urlSpreadsheet.openConnection();
            String urlParameters = "";

            if (id != null)
                urlParameters += "entry_1968657343=" + URLEncoder.encode(id);
            if (name != null)
                urlParameters += "&" + "entry_68340782=" + URLEncoder.encode(name);
            if (age != null)
                urlParameters += "&" + "entry_673818712=" + URLEncoder.encode(age);
            if (email != null)
                urlParameters += "&" + "entry_1033294711=" + URLEncoder.encode(email);
            if (vip != null)
                urlParameters += "&" + "entry_1609563132=" + URLEncoder.encode(vip);

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeBytes(urlParameters);
            dataOutputStream.flush();
            dataOutputStream.close();

            //System.out.println("AFTER SEND DATA");
            System.out.println("Response Code: " + connection.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private void processJson(JSONObject object) {

        /*try {

            JSONArray rows = object.getJSONArray("rows");

            for (int r = 0; r < rows.length(); ++r) {
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");

                System.out.println(columns.getJSONObject(1).getString("v"));
                System.out.println(columns.getJSONObject(2).getString("v"));
                System.out.println(columns.getJSONObject(3).getString("f"));
                System.out.println(columns.getJSONObject(4).getString("v"));
                System.out.println(columns.getJSONObject(5).getString("f"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
}
