package br.com.gods.mathcalendar;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
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
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Objects;

import br.com.gods.mathcalendar.communication.MathCalendarApplication;
import br.com.gods.mathcalendar.communication.UserData;
import br.com.gods.mathcalendar.communication.UserService;

public class LoginActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private ProfileTracker profileTracker;
    private CallbackManager callbackManager;

    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        userService = MathCalendarApplication.getMathCalendarApplicationContext().getServiceFactory().createService(UserService.class);

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                // Track User Profile
                // If logged, call MainActivity Class
            }
        };

        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText email = (EditText) findViewById(R.id.email_input);
        EditText password = (EditText) findViewById(R.id.password_input);

        Button confirm = (Button) findViewById(R.id.confirm_button);
        Button forgot = (Button) findViewById(R.id.forgot_button);

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
                                    GraphResponse response) {
                                try {
                                    final UserData userData = new UserData();
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
                                    Thread t = new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            userService.userData(userData.getName(), userData.getBirthday(), userData.getEmail(), userData.getVip());
                                                // DEIXA ISSO AQUI TALVEZ PRECISE
                                                    /*, new BaseListFragment().new DefaulServiceCallback<ErrorResponse>() {

                                                @Override
                                                public void success(ErrorResponse errorResponse) {
                                                }
                                            });*/
                                        }
                                    });
                                    t.start();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(getApplicationContext(), "SUCCESS, WE'VE DATA", Toast.LENGTH_LONG).show();
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,birthday,email,gender,link,timezone,cover");
                request.setParameters(parameters);
                request.executeAsync();

                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "CANCEL", Toast.LENGTH_LONG).show();
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
    protected void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
    }
}
