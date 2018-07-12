package com.example.oscarplaza.stride;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.oscarplaza.stride.Utils.Utils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.*;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;


public class TabAFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapLongClickListener,
        GoogleMap.OnMarkerClickListener,GoogleMap.OnMapClickListener {
    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;



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






    public TabAFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = super.onCreateView(inflater, container, savedInstanceState);


        getMapAsync(this);

        return rootView;
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {




        Log.d(TAG, "onMapReady: ");

        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // Posicionar el mapa en una localización y con un nivel de zoom
        float zoom = 19;
        takeGPSLocation();
        final LatLng latLng = new LatLng(getLat(), getLng());
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        //Toast.makeText(getActivity().getApplicationContext(),"latitude "+getLat()+" & longitud"+getLng(),Toast.LENGTH_LONG).show();

        // Colocar un marcador en la misma posición
        googleMap.addMarker(new MarkerOptions().position(latLng).title(getString(R.string.my_position)));
        googleMap.getUiSettings().setAllGesturesEnabled(true);

        googleMap.setOnMapLongClickListener(this);

        Utils u = new Utils();
        Geocoder geocoder = new Geocoder(getActivity().getApplicationContext());

        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude,1);
        } catch (IOException e) {
            Log.d(TAG, "onMapReady: LLOREMOS");
        }
        if(addresses != null && addresses.size() > 0 ){
            Address address = addresses.get(0);

            String street = address.getAddressLine(0);
            Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            toolbar.setTitle(street);

        }










        //googleMap.addMarker(new MarkerOptions().position(gogle).title("Position for Google service"));
        // Más opciones para el marcador en:
        // https://developers.google.com/maps/documentation/android/marker

        // Otras configuraciones pueden realizarse a través de UiSettings
        // UiSettings settings = getMap().getUiSettings();

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {




    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getContext(),"SE PRODUJO UN ERROR",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        Geocoder geocoder = new Geocoder(getContext());

        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude,1);
        } catch (IOException e) {
            Log.d(TAG, "onMapReady: LLOREMOS adentro");
        }
        ViewDialog alert = new ViewDialog();

        if(addresses != null && addresses.size() > 0 ){
            Address address = addresses.get(0);
            String street = address.getAddressLine(0);
        alert.showDialog(getContext(), street,latLng);}else{alert.showDialog(getActivity(),"No se pudo obtener el nombre de la calle",latLng);}



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

    public void takeGPSLocation(){
        gps = new GPSTracker(getContext());
        if(gps.canGetLocation){
            setLat(gps.getLatitude());
            setLng(gps.getLongitude());
        }}

    @Override
    public void onMapClick(LatLng latLng) {



    }
}
