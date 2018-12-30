package com.example.oscarplaza.stride;


import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oscarplaza.stride.Entidades.Observacion;
import com.example.oscarplaza.stride.Entidades.PuntoVotados;
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

import java.io.IOException;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragmentinSeccion extends SupportMapFragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapLongClickListener,
        GoogleMap.OnMarkerClickListener,GoogleMap.OnMapClickListener {
    private GoogleMap mMap;

    public GoogleMap getmMap() {
        return mMap;
    }

    public void setmMap(GoogleMap mMap) {
        this.mMap = mMap;
    }

    GPSTracker gps;
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
    //public static final int TAG_CODE_PERMISSION_LOCATION = 301;
    //private FusedLocationProviderClient mFusedLocationClient;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
        {
            Semaforo activity = (Semaforo) getActivity();

            if(!activity.getData().getData().isEmpty() && !activity.getData().getData().contains(null) )
            {

                float zoom = 19;
                Observacion o = activity.getData();
                takeGPSLocation();
                LatLng latLng = new LatLng(getLat(), getLng());
                getmMap().clear();
                getmMap().moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

                int  contando=1;
                for(PuntoVotados p : o.getData())
                {
                        CreateMarket(p.getLat(),p.getLng(),p.getScore(),getmMap(),contando);
                        contando++;

                }
                }else{ }

        }
        else
        {


        }
    }

    public void CreateMarket(double lat, double lng, String score, GoogleMap googleMap, int contando)  {

        switch (score){
            case "G":
                googleMap.addMarker(new MarkerOptions().position(new LatLng(lat,lng)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).title(contando+")"));
                break;
            case "Y":
                googleMap.addMarker(new MarkerOptions().position(new LatLng(lat,lng)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)).title(contando+")"));
                break;
            case "R":
                googleMap.addMarker(new MarkerOptions().position(new LatLng(lat,lng)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)).title(contando+")"));
                break;
        }
        }
        public ListFragmentinSeccion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Toast.makeText(getActivity(),"entro on create view",Toast.LENGTH_SHORT).show();
        // Inflate the layout for this fragment
        View rootView = super.onCreateView(inflater, container, savedInstanceState);


        getMapAsync(this);

        return rootView;


    }

    @Override
    public void onResume() {
        //Toast.makeText(getActivity(),"entro resumen",Toast.LENGTH_SHORT).show();

        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        //Toast.makeText(getActivity(),"entro onAttach",Toast.LENGTH_SHORT).show();

        super.onAttach(context);
    }

    @Override
    public void onPause() {
        //Toast.makeText(getActivity(),"entro Pause",Toast.LENGTH_SHORT).show();

        super.onPause();
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


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Posicionar el mapa en una localización y con un nivel de zoom

        setmMap(googleMap);
        }
    public void takeGPSLocation(){
        gps = new GPSTracker(getContext());
        if(gps.canGetLocation){
            setLat(gps.getLatitude());
            setLng(gps.getLongitude());
        }}
}
