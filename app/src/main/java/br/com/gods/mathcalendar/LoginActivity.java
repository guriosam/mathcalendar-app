package br.com.gods.mathcalendar;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "br.com.gods.mathcalendar",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));

                Toast.makeText(getApplicationContext(), Base64.encodeToString(md.digest(), Base64.DEFAULT), Toast.LENGTH_LONG).show();
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException ignored) {

        }

        loginButton = (LoginButton) findViewById(R.id.login_button);
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

                                Thread t = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            postData(response.getJSONObject().getString("id"),
                                                    response.getJSONObject().getString("name"),
                                                    null,
                                                    null,
                                                    null);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                t.start();

                                Toast.makeText(getApplicationContext(), "SUCCESS, WE HAVE DATA", Toast.LENGTH_LONG).show();
                            }
                        });

                Bundle parameters = new Bundle();
                //WAITING FACEBOOK REVIEW FOR ADD user_birthday, email INTO PARAMETERS
                //parameters.putString("fields", "id,name,birthday,email,gender,timezone");
                parameters.putString("fields", "id,name,gender,timezone");
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

        /**
         * THE FOLLOWING CODE SNIPPET SENDS USER DATA TO A SPREADSHEET VIA HTTPSURLCONNECTION CLASS BUT ISN'T ENOUGH FOR US;
         */

        /*try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
