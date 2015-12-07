package com.bigevent.noc.bigeventdataverification;

//Android support
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigevent.noc.bigeventdataverification.API.LoginService;
import com.bigevent.noc.bigeventdataverification.model.TokenModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

//Android UI
//Buterknife
//Retrofit

public class LoginActivity extends AppCompatActivity{
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Bind(R.id.input_username) EditText _usernameText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;

//    String API = getString(R.string.APIBaseUrl); // R.string returns a resource ID.
    // This function is called when the activity starts.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //** LOGIN BUTTON **//

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

/*
            @Override
            public void onClick(View v) {
                Log.d(TAG, "This was the login button.");
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://steven.tamu.edu/be/json/")
                        .build();
                LoginService service = BasicServiceGenerator.createService(LoginService.class, "admin", "admin");
                Call<AccountModel> call = service.basicLogin();
                call.enqueue(new Callback<AccountModel>() {
                    @Override
                    public void onResponse(Response<AccountModel> response,
                                           Retrofit retrofit) {
                    }

                    @Override
                    public void onFailure(Throwable t) {
                    }
                );
            }
            ）；
*/
        //** SIGNUP BUTTON **//

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    //** LOGIN METHOD **//
    // This is outside of the oncreate method
    public void login() {
        Log.d(TAG, "Login");

        //If it doesn't validate, the login fails.
        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String username = _usernameText.getText().toString();
        String password = _passwordText.getText().toString();
        String session_name = "";
        String sessionid = "";
        String token = "";
        TextView txv = (TextView) findViewById(R.id.input_username);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://steven.tamu.edu/be/json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginService ls = retrofit.create(LoginService.class); // creates implementation of the interface
        Call<TokenModel> call = ls.getHttpToken(username, password);
        call.enqueue(new Callback<TokenModel>() {
            @Override
            public void onResponse(Response<TokenModel> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    TokenModel result = response.body();
                } else {
                    //request not successful (like 400,401,403 etc)
                    //Handle errors
                }
            }


            @Override
            public void onFailure(Throwable t) {

            }
        });
        Log.d(TAG, "Just did the call");


        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        onLoginFailed();
                        progressDialog.dismiss();
                    }}, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the login
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String username = _usernameText.getText().toString();
        String password = _passwordText.getText().toString();

        //if (username.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
        if (username.isEmpty()) {
            _usernameText.setError("enter a valid email address");
            valid = false;
        } else {
            _usernameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}