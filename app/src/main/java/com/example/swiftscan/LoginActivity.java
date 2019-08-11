package com.example.swiftscan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private CallbackManager mCallbackManager;
    private LoginResult loginResultT;


    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logOut();
        LoginButton loginButton = findViewById(R.id.buttonFacebook);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                loginResultT = loginResult;
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if (accessToken == null) {
                    Toast.makeText(LoginActivity.this, "Login unSuccessful..Please contact developer... ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Successful. ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void logInOnClick(final View view) {
        EditText login = findViewById(R.id.textLogin);
        EditText pass = findViewById(R.id.textPassword);
        //TODO
        boolean check = (login.getText().length() > 2) & (pass.getText().length() > 2);
        if (check) {
            Log.d(TAG, "signInWithEmail:success");
            DBHelper db = DBHelper.getInstance(getApplicationContext());
            db.setName(login.getText().toString());
            Intent intent = new Intent(LoginActivity.this, ScanActivity.class);
            startActivity(intent);
        } else {
            Log.w(TAG, "signInWithEmail:failure");
            Toast.makeText(LoginActivity.this, R.string.wrong_login_pass, Toast.LENGTH_LONG).show();
        }
    }

    public void signInOnClick(View view) {
        Intent intent = new Intent(LoginActivity.this, SighnInActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(final AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        try {
            Log.d(TAG, "signInWithCredential:success");
            Intent intent = new Intent(LoginActivity.this, ScanActivity.class);
            GraphRequest request = GraphRequest.newMeRequest(
                    loginResultT.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            try {
                                Log.i("Response", response.toString());
                                final String firstName = response.getJSONObject().getString("first_name");
                                final String lastName = response.getJSONObject().getString("last_name");
                                String viewName = lastName + " " + firstName.charAt(0) + ".";
                                DBHelper db = DBHelper.getInstance(getApplicationContext());
                                db.setName(viewName);
                                Log.d(TAG, "User profile updated.:" + firstName + lastName);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,email,first_name,last_name,gender");
            request.setParameters(parameters);
            request.executeAsync();
            startActivity(intent);
        } catch (Exception e) {
            Log.w(TAG, "signInWithCredential:failure " + e.getMessage());
            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
        }
    }
}
