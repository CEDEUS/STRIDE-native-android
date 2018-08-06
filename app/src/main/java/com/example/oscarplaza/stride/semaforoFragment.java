package com.example.oscarplaza.stride;


import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.oscarplaza.stride.Entidades.Observacion;
import com.example.oscarplaza.stride.Entidades.PuntoVotados;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class semaforoFragment extends Fragment implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    ArrayList<PuntoVotados> p = new ArrayList<PuntoVotados>();
    private final Observacion observacion = new Observacion(p);
    private FusedLocationProviderClient mFusedLocationClient;

    private float accuary;

    public float getAccuary() {
        return accuary;
    }

    public void setAccuary(float accuary) {
        this.accuary = accuary;
    }

    public String getDatemaker() {
        return Datemaker;
    }

    public void setDatemaker(String datemaker) {
        Datemaker = datemaker;
    }

    String Datemaker;


    GPSTracker gps;
    private double lat=0;
    private double latgoogle=0;
    private double lngoogle=0;

    public double getLatgoogle() {
        return latgoogle;
    }

    public void setLatgoogle(double latgoogle) {
        this.latgoogle = latgoogle;
    }

    public double getLngoogle() {
        return lngoogle;
    }

    public void setLngoogle(double lngoogle) {
        this.lngoogle = lngoogle;
    }

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
    private Context c;

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    //fin metodos y caracteristicas
    private String genero;
    private int edad;
    private String  ability;

    public Observacion getObservacion() {
        return observacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }



    public semaforoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final GoogleApiClient googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        // Inflate the layout for this fragment
        Log.d("TAG", "onCreateView: ");
        View rootView = inflater.inflate(R.layout.fragment_semaforo, container, false);
        Button rojo = (Button)rootView.findViewById(R.id.button_Red);
        Button yellow = (Button)rootView.findViewById(R.id.button_2);
        Button verde = (Button)rootView.findViewById(R.id.button_3);
        rojo.setOnClickListener(this);
        verde.setOnClickListener(this);
        yellow.setOnClickListener(this);
        setC(container.getContext());
        return rootView;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onClick(View v) {
        int id = v.getId ();
        takeGPSLocation();

        String ItemSex = getActivity().getIntent().getExtras().getString("sex");
        int ItemRangoEtario = getActivity().getIntent().getExtras().getInt("etario");
        String ItemAbility = getActivity().getIntent().getExtras().getString("ability");
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            setLatgoogle(location.getLatitude());
                            setLngoogle(location.getLongitude());
                            setAccuary(location.getAccuracy());

                        }
                    }
                });




        switch (id) {
            case R.id.button_Red :
                process("R",ItemSex,ItemAbility,ItemRangoEtario);
                break;
            case R.id.button_2 :
                process("Y",ItemSex,ItemAbility,ItemRangoEtario);

                break;
            case R.id.button_3 :
                process("G",ItemSex,ItemAbility,ItemRangoEtario);

                break;

        }

    }

    private void process(String Votacion, String itemSex, String itemAbility, int itemRangoEtario) {

        setGenero(itemSex);
        setAbility(itemAbility);
        setEdad(itemRangoEtario);

        if (getAccuary() >=  15) {
            if(!samepoint(Votacion,getLatgoogle(),getLngoogle()))
            {
                addelement(Votacion,getLatgoogle(),getLngoogle());
            }
      }
        else
        {
            if(!samepoint(Votacion,getLat(),getLng()))
            {
                addelement(Votacion,getLat(),getLng());
            }
        }
      }

    private void addelement(String votacion, double lat, double lng) {

        getObservacion().getPuntos().add(new PuntoVotados(votacion,getEdad(),getAbility(),getGenero(),getDatemaker(),lat,lng));
        //Semaforo activity = (Semaforo) getActivity();
        //activity.SetData(getObservacion());

    }

    private boolean samepoint(String votacion, double lat, double lng)
    {

        boolean comparacion = true;
        if (getObservacion().getPuntos().size() == 0)
        {
            comparacion = false;
        }
        else{
            PuntoVotados last = getObservacion().getPuntos().get(getObservacion().getPuntos().size() - 1);
            int retvallat = Double.compare(last.getLat(), lat);
            int retvallng = Double.compare(last.getLng(), lng);
            if (retvallat > 0 || retvallng > 0) {
                comparacion = false;
                } else if (retvallat < 0 || retvallng < 0) {
                comparacion  = false;
                } else {
                if (last.getVotacion().equals(votacion)){
                    comparacion = true;
                }
                else{comparacion = false;}
                }


        }
        LatLng latLngre = new LatLng(lat, lng);

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
        Log.d("TAG", "samepoint: "+comparacion);
        Toast.makeText(getActivity(),""+comparacion+" "+getObservacion().getPuntos().size()+" "+street+" "+lat+" "+lng,Toast.LENGTH_SHORT).show();

        return comparacion;
    }



    private void takeGPSLocation() {

        gps = new GPSTracker(getC());
        if(gps.canGetLocation){
            setLat(gps.getLatitude());
            setLng(gps.getLongitude());
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
}
