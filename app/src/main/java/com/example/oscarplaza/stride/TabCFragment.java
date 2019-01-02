package com.example.oscarplaza.stride;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.oscarplaza.stride.Entidades.RespMylastPoint;
import com.example.oscarplaza.stride.Entidades.RespResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class TabCFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapLongClickListener,
        GoogleMap.OnMarkerClickListener,GoogleMap.OnMapClickListener {
    private GoogleMap mMap;
    private int cuenta;

    public GoogleMap getmMap() {
        return mMap;
    }

    public void setmMap(GoogleMap mMap) {
        this.mMap = mMap;
    }


    public TabCFragment() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Toast.makeText(getActivity(),"entro on create view",Toast.LENGTH_SHORT).show();
        // Inflate the layout for this fragment
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        getMapAsync(this);
        try{
        getData();}
        catch (Exception e){}


        return rootView;

    }

    private void getData() {
        SharedPreferences prefs = this.getActivity().getSharedPreferences("loginPrefs", MODE_PRIVATE);

        final String tokken = prefs.getString("token", "");//"No name defined" is the default value.
        int idName = prefs.getInt("id", 0); //0 is the default value.
        int count = prefs.getInt("count",10);// 10 if no initialize
        String EndPoint = "http://146.155.17.18:18080/my_last_point?count="+count;
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        ArrayList<RespResult> arr = new ArrayList<RespResult>();
        int cuantos =1;
        capturedataandrecursive(arr,EndPoint,tokken,cuantos);



    }

    private void capturedataandrecursive(final ArrayList<RespResult> arr, String endPoint, final String tokken, final int cuantos) {
        RequestQueue queue = Volley.newRequestQueue(getActivity());


        StringRequest stringRequest = new StringRequest(Request.Method.GET, endPoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson g = new Gson();
                RespMylastPoint r = g.fromJson(response,RespMylastPoint.class);
                arr.addAll(r.getResults());
                //ObservacionAdapter oba = new ObservacionAdapter(getActivity(),arr);
                //lv.setAdapter(oba);
                int i = 0;

                for(RespResult p : arr)
                {
                    CreateMarket(p.getLat(),p.getLon(),p.getScore(),getmMap(),cuantos+i);

                    if(i == arr.size() - 1){
                        float zoom = 19;
                        LatLng latLng = new LatLng(p.getLat(), p.getLon());
                        getmMap().moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));


                        // Last iteration
                    }
                    i++;

                }
                setCuenta(getCuenta()+arr.size()-1);
                arr.clear();

                if (r.getNext() == null){}
                else{capturedataandrecursive(arr,r.getNext(),tokken, getCuenta());}


            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"error",Toast.LENGTH_SHORT).show();
            }
        }

        ){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";

            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("Authorization", "Token " + tokken);
                return params;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String value = "";
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

    private void CreateMarket(Double lat, Double lon, String score, GoogleMap googleMap, int i) {
        switch (score){
            case "G":
                googleMap.addMarker(new MarkerOptions().position(new LatLng(lat,lon)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).title(i+")"));
                break;
            case "Y":
                googleMap.addMarker(new MarkerOptions().position(new LatLng(lat,lon)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)).title(i+")"));
                break;
            case "R":
                googleMap.addMarker(new MarkerOptions().position(new LatLng(lat,lon)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)).title(i+")"));
                break;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Posicionar el mapa en una localización y con un nivel de zoom

        setmMap(googleMap);
    }

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }
}

