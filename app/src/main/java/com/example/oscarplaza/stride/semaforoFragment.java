package com.example.oscarplaza.stride;


import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class semaforoFragment extends Fragment implements  GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, View.OnTouchListener {
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
    private long pressStartTime;

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
        Button undo = (Button)rootView.findViewById(R.id.undo);
        rojo.setOnTouchListener(this);
        verde.setOnTouchListener(this);
        yellow.setOnTouchListener(this);
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Semaforo activity = (Semaforo) getActivity();
                activity.Deletedata();

            }
        });
        setC(container.getContext());
        return rootView;
    }



    private void process(String Votacion,String categoria) {
        String[] caca = generate();


        setGenero(caca[0]);
        setAbility(caca[2]);
        setEdad(Integer.valueOf(caca[1]));
        getObservacion().setAbility(getAbility());
        getObservacion().setAge(getEdad());
        getObservacion().setSex(getGenero());
        getObservacion().setVersion("1.1.0-beta");

        if (getAccuary() >=  20) {
            if(!samepoint(Votacion,getLatgoogle(),getLngoogle()))
            {
                addelement(Votacion,getLatgoogle(),getLngoogle(),getAccuary(),categoria);
            }
      }
        else
        {
            if(!samepoint(Votacion,getLat(),getLng()))
            {
                addelement(Votacion,getLat(),getLng(),getAccuary(),categoria);
            }
        }
      }

    private void addelement(String votacion, double lat, double lng, double hdop, String categoria) {

        getObservacion().getData().add(new PuntoVotados(votacion,hdop,lng,lat,categoria));
        Semaforo activity = (Semaforo) getActivity();
        activity.SetData(getObservacion());

    }

    private boolean samepoint(String votacion, double lat, double lng)
    {

        boolean comparacion = true;
        if (getObservacion().getData().size() == 0)
        {
            comparacion = false;
        }
        else{
            PuntoVotados last = getObservacion().getData().get(getObservacion().getData().size() - 1);
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

    @SuppressLint("MissingPermission")
    @Override
    public void onClick(View view) {


    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch(motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pressStartTime = System.currentTimeMillis();

                // PRESSED
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                long pressDuration = System.currentTimeMillis() - pressStartTime;
                if (pressDuration >= 500) {
                    switch (view.getId()) {
                        case R.id.button_Red :
                            preprocces("R");
                            break;
                        case R.id.button_2 :

                            preprocces("Y");

                            break;
                        case R.id.button_3 :
                            process("G","none");
                            break;
                            }
                }else
                    {
                        switch (view.getId()) {
                            case R.id.button_Red :
                                process("R","none");
                                break;
                            case R.id.button_2 :

                                process("Y","none");

                                break;
                            case R.id.button_3 :
                                process("G","none");
                                break;
                        }

                    }


                break;
        }
        return false;
    }

    @SuppressLint("MissingPermission")
    private String[] generate() {
        takeGPSLocation();
        String[] toppings = new String[3];
        toppings[0] = getActivity().getIntent().getExtras().getString("sex");
        toppings[1] = String.valueOf(getActivity().getIntent().getExtras().getInt("etario"));
        toppings[2]  = getActivity().getIntent().getExtras().getString("ability");
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
        return toppings;

    }

    private void preprocces(String votacion) {

        Categorymodal  alert= new Categorymodal();
        alert.showDialog(getActivity(),votacion,this);






    }

    public void Metodoenvio(String category, String votacion) {
        process(votacion,category);
    }
/*
    private String determinarCategoria(long pressDuration) {
        String salida ="";

        if (pressDuration <=100)
        {
            salida ="None";
        }
        if(pressDuration >= 101 && pressDuration <= 1000 )
        {
            salida="Dangerous traffic";

        }
        if (pressDuration >= 1001 && pressDuration <= 2000)
        {
            salida="Feeling unsafe";
        }
        if (pressDuration >= 2001 && pressDuration <= 3000)
        {
            salida="Bad quality sidewalk";
        }
        if (pressDuration >= 3001 && pressDuration <= 4000)
        {
            salida="Not attractive";
        }
        if (pressDuration >= 4001)
        {
            salida="All is bad!";
        }
        return  salida;

    }
/*
    @SuppressLint("MissingPermission")
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int id = view.getId();
        takeGPSLocation();
        Toast.makeText(getActivity(),""+motionEvent.getDownTime(),Toast.LENGTH_SHORT).show();


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
    return  false;
    }*/
}
