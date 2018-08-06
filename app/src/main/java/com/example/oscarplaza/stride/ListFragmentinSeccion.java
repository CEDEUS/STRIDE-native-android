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

            if(!activity.getData().getPuntos().isEmpty() && !activity.getData().getPuntos().contains(null) )
            {

                float zoom = 19;
                Observacion o = activity.getData();
                takeGPSLocation();
                LatLng latLng = new LatLng(getLat(), getLng());
                getmMap().clear();
                getmMap().moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

                int number =0;
                for(PuntoVotados p : o.getPuntos())
                {
                    LatLng latLngre = new LatLng(p.getLat(), p.getLng());

                    Geocoder geocoder = new Geocoder(getActivity().getApplicationContext());

                    List<Address> addresses = null;
                    try {
                        addresses = geocoder.getFromLocation(latLngre.latitude, latLngre.longitude,1);
                    } catch (IOException e) {
                        Log.d("TAG", "onMapReady: LLOREMOS");
                    }
                    String street;
                    if(addresses != null && addresses.size() > 0 ){
                        Address address = addresses.get(0);

                        street = address.getAddressLine(0);}
                    else{street="no found";}

                  switch (p.getVotacion()){
                      case "G":
                          getmMap().addMarker(new MarkerOptions().position(latLngre).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).title(number+") "+street));

                          break;
                      case "Y":
                          getmMap().addMarker(new MarkerOptions().position(latLngre).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)).title(number+") "+street));

                          break;
                      case "R":
                          getmMap().addMarker(new MarkerOptions().position(latLngre).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)).title(number+") "+street));
                          break;
                  }
                  number++;
                }
                }else{ }

        }
        else
        {

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

        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // Posicionar el mapa en una localización y con un nivel de zoom
        float zoom = 19;
        takeGPSLocation();
        LatLng latLng = new LatLng(getLat(), getLng());
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        googleMap.setMyLocationEnabled(true);

        setmMap(googleMap);
        }
    public void takeGPSLocation(){
        gps = new GPSTracker(getContext());
        if(gps.canGetLocation){
            setLat(gps.getLatitude());
            setLng(gps.getLongitude());
        }}
}
