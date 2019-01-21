package com.example.oscarplaza.stride.Utils;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.oscarplaza.stride.CheckButton;
import com.example.oscarplaza.stride.LoginActivity;
import com.example.oscarplaza.stride.R;

import java.security.PublicKey;

import static android.content.Context.MODE_PRIVATE;

public class Utils {
    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    private String URL;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    private String response;

    public Utils(String URL) {
        this.URL = URL;
    }
    public Utils() {
    }

    public String generateurlmaps(String lat ,String lng)
    {
        String URL = "http://maps.google.com/maps/api/geocode/json?latlng="+lat+","+lng+"&sensor=false";
        setURL(URL);
        return getURL();
    }


    /**
     *
     * @param loginActivity the context
     * @return boolean
     */
    public boolean seesion(LoginActivity loginActivity){
        SharedPreferences loginPreferences = loginActivity.getSharedPreferences("loginPrefs", MODE_PRIVATE);
        if (loginPreferences.contains("id"))
        {
            return true;

        }
        else
            {
                return false;
            }

    }

    /**
     *
     * @param s
     * @param forms
     * @return
     */
    public String getIdAbility(String s,CheckButton forms) {
        String as = "";
        if (s.equals(forms.getString(R.string.normal)))
        {
            as ="1";
        }
        else if (s.equals(forms.getString(R.string.asistida)))
        {
            as="2";
        }
        else if (s.equals(forms.getString(R.string.mecanica)))
        {
            as="3";
        }
        return as;


    }


    public int getIdEdad(String s, CheckButton forms) {
        int as = 0;
        if (s.equals(forms.getString(R.string.Kid)))
        {
            as =1;
        }
        else if (s.equals(forms.getString(R.string.adole)))
        {
            as=2;
        }
        else if (s.equals(forms.getString(R.string.adults)))
        {
            as=3;
        }
        else if(s.equals(forms.getString(R.string.Elderly))){as=4;}
        return as;

    }

    public String GetIdGeneto(String s, CheckButton forms) {
        String  as = "";
        if (s.equals(forms.getString(R.string.male)))
        {
            as ="H";
        }
        else if (s.equals(forms.getString(R.string.female)))
        {
            as="M";
        }
        else if (s.equals(forms.getString(R.string.no_define)))
        {
            as="O";
        }
        return as;
    }

    public boolean isNetworkAvailable(Activity _this) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) _this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
