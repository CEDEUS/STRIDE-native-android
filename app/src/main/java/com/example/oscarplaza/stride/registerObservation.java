package com.example.oscarplaza.stride;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;


public class registerObservation extends Fragment {
    private double lat=0;



    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    private double lng=0;


    public registerObservation() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register_observation, container, false);
        takeGPSLocation();
        String url ="http://maps.google.com/maps/api/geocode/json?latlng="+ String.valueOf(getLat())+","+String.valueOf(getLng())+"&sensor=false";
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", "onResponse: "+response);



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);




        return v;
    }
    public void takeGPSLocation(){
        GPSTracker gps = new GPSTracker(getContext());
        if(gps.canGetLocation){
            setLat(gps.getLatitude());
            setLng(gps.getLongitude());
        }}


}
