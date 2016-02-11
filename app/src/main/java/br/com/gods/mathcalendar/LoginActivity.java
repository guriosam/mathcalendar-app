package br.com.gods.mathcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button confirm;
    private Button forgot;

    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the SDK before executing any other operations,
        // especially, if you're using Facebook UI elements.
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        email = (EditText) findViewById(R.id.email_input);
        password = (EditText) findViewById(R.id.password_input);

        confirm = (Button) findViewById(R.id.confirm_button);
        forgot = (Button) findViewById(R.id.forgot_button);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                //Application code
                                //Data comes in JSON format, just handle it here!!
                                /*try {
                                    System.out.println("LENGTH GRAPH RESPONSE: " + response.getJSONObject().length());
                                    System.out.println("CONTENT GRAPH RESPONSE: " + response.getJSONObject().toString());

                                    PersonData personData = new PersonData();
                                    personData.setId(response.getJSONObject().getString("id"));
                                    personData.setName(response.getJSONObject().getString("name"));
                                    personData.setBirthday(response.getJSONObject().getString("birthday"));
                                    personData.setEmail(response.getJSONObject().getString("email"));
                                    personData.setGender(response.getJSONObject().getString("gender"));
                                    personData.setLink(response.getJSONObject().getString("link"));
                                    personData.setTimezone(response.getJSONObject().getString("timezone"));
                                    personData.setCover(response.getJSONObject().getString("cover"));
                                    System.out.println(personData.toString());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }*/
                                Toast.makeText(getApplicationContext(), "SUCCESS, WE'VE DATA", Toast.LENGTH_LONG).show();
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,birthday,email,gender,link,timezone,cover");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(getApplicationContext(), "CANCEL", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
