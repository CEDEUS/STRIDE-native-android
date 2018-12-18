package com.example.oscarplaza.stride;
import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.oscarplaza.stride.Entidades.RespLogin;
import com.example.oscarplaza.stride.Entidades.SeverUrl;
import com.example.oscarplaza.stride.Utils.Utils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity{
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button btnlogin;
    private static String TAG ="LoginActivity";
    public static final int TAG_CODE_PERMISSION_LOCATION = 301;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utils uv = new Utils();
        if (!uv.seesion(this)) {


            //Remove title bar
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                            PackageManager.PERMISSION_GRANTED) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        TAG_CODE_PERMISSION_LOCATION);

            }
            getSupportActionBar().hide();
//Remove notification bar
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            // Set up the login form.
            mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
            mPasswordView = (EditText) findViewById(R.id.password);
            mLoginFormView = findViewById(R.id.login_form);
            mProgressView = findViewById(R.id.login_progress);
            btnlogin = findViewById(R.id.login);
            btnlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        attemptLogin();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            Utils u = new Utils();
            if (!u.isNetworkAvailable(this))
            {
                ViewDialog v = new  ViewDialog();
                v.nohaveinternet(this);
            }

        }else{
            super.onCreate(savedInstanceState);

            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            LoginActivity.this.startActivity(i);
            finish();

        }
        }

    private void attemptLogin() throws JSONException {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;
        //validacion claves
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_incorrect_password));
            focusView = mPasswordView;
            cancel = true;
        }
        else if(!isPasswordValid(password)){
            mPasswordView.setError(getString(R.string.error_incorrect_password));
            focusView = mPasswordView;
            cancel = true;
            }
// Validacion email
       /* if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }*/

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            //TODO SUBIR API EN GO A AWS E INSTALAR VOLLEY
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            }, 100);
            final JSONObject jsonBody = new JSONObject();
            jsonBody.put("username", email);
            jsonBody.put("password", password);
            final String requestBody = jsonBody.toString();

            RequestQueue queue = Volley.newRequestQueue(this);
            SeverUrl u = new SeverUrl();
            String EndPoint = u.GetUrlEndPoint(0);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoint, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "onResponse: "+response);
                    Gson g = new Gson();
                    RespLogin r= g.fromJson(response,RespLogin.class);
                    sharedResponse(r.getToken(),r.getUserId());
                    Log.d(TAG, "onResponse: ");
                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "onErrorResponse: ");
                    showProgress(false);

                    mEmailView.setError(getString(R.string.error_incorrect_email));
                    mPasswordView.setError(getString(R.string.error_incorrect_password));
                }
            }){
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";

                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/json");
                    return  params;
                }
                            /*

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("username", "usuario");
                            params.put("password","password123");
                            return  params;
                        }*/

                @Override
                public byte[] getBody() throws AuthFailureError {
                    Log.d(TAG, "getBody: ");
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }

                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String value="";
                    try {
                         value = new String(response.data, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.data);
                    }
                    return Response.success(value, HttpHeaderParser.parseCacheHeaders(response));
                }
            };
            queue.add(stringRequest);








        }
    }
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        // validacion previa a la  clave
        return password.length() > 4;
    }
    //COSMETICOS (OCULTA Y MUESTA UI)

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    private void sharedResponse(String Tokken, Integer userId) {
        Log.d(TAG, "sharedResponse: ");
        SharedPreferences loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPreferences.edit();
        editor.putString("token", Tokken);
        editor.putInt("id",userId);
        editor.putInt("count",10);
        editor.commit();
    }
}

