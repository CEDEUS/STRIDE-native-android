package com.example.oscarplaza.stride;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oscarplaza.stride.Entidades.Observacion;
import com.example.oscarplaza.stride.Entidades.PuntoVotados;
import com.example.oscarplaza.stride.Utils.Utils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class forms extends AppCompatActivity {
    // Atributos para su uso y metodos de Edicion en los listner
    private static String  TAG = "forms";
    ArrayList<PuntoVotados> p = new ArrayList<PuntoVotados>();
    private final Observacion observacion = new Observacion(p);

    public String getDatemaker() {
        return Datemaker;
    }

    public void setDatemaker(String datemaker) {
        Datemaker = datemaker;
    }

    String Datemaker;


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



    //fin metodos y caracteristicas
    private String genero;
    private int edad;
    private int  ability;

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

    public int getAbility() {
        return ability;
    }

    public void setAbility(int ability) {
        this.ability = ability;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);     //  Fixed Portrait orientation
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        setDatemaker(ts);

        setContentView(R.layout.content_forms);
        TextView etario = (TextView)findViewById(R.id.textView5);
        SpannableString content = new SpannableString(getString(R.string.groupE));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        etario.setText(content);
        TextView tv = (TextView)findViewById(R.id.textView4);
        content = new SpannableString(getString(R.string.sexo));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tv.setText(content);
        TextView tv2 = (TextView)findViewById(R.id.textView9);
        content = new SpannableString(getString(R.string.movility));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tv2.setText(content);
        Button btnred =(Button)findViewById(R.id.button_Red);
        Button btnyellow =(Button)findViewById(R.id.button_2);
        Button btngreen =(Button)findViewById(R.id.button_3);
        findViewById(R.id.sucsessess).setVisibility(View.GONE);
        findViewById(R.id.sucsessess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String jsArray = gson.toJson(getObservacion().getPuntos());



                Toast.makeText(getApplicationContext(),jsArray,Toast.LENGTH_LONG).show();
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetAndDrivenparams(1);
            }
        });
        btnyellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetAndDrivenparams(2);

            }
        });
        btngreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetAndDrivenparams(3);
            }
        });
    }
    private void GetAndDrivenparams(int semaforo) {
        Utils u = new Utils();
        RadioGroup rgenero = (RadioGroup)findViewById(R.id.radiog);
        RadioGroup retario = (RadioGroup)findViewById(R.id.radioge);
        RadioGroup ability = (RadioGroup)findViewById(R.id.radiops);
        RadioButton rb=(RadioButton)findViewById(rgenero.getCheckedRadioButtonId());
        setGenero(u.GetIdGeneto(rb.getText().toString(),this));
        rb=(RadioButton)findViewById(retario.getCheckedRadioButtonId());
        setEdad(u.getIdEdad(rb.getText().toString(),this));
        rb=(RadioButton)findViewById(ability.getCheckedRadioButtonId());
        setAbility(u.getIdAbility(rb.getText().toString(),this));
        takeGPSLocation();

        getObservacion().getPuntos().add(new PuntoVotados(semaforo,getEdad(),getAbility(),getGenero(),getDatemaker(),getLat(),getLng()));
        PuntoVotados P = getObservacion().getPuntos().get(getObservacion().getPuntos().size() - 1);
        //Toast.makeText(getApplicationContext(),P.toString(),Toast.LENGTH_LONG).show();
        if (getObservacion().getPuntos().size() >2) {
            findViewById(R.id.sucsessess).setVisibility(View.VISIBLE);
            block();
            }


    }

    private void block() {
        RadioGroup rgenero = (RadioGroup)findViewById(R.id.radiog);

        RadioGroup retario = (RadioGroup)findViewById(R.id.radioge);
        RadioGroup ability = (RadioGroup)findViewById(R.id.radiops);
        rgenero.setEnabled(false);
        retario.setEnabled(false);
        ability.setEnabled(false);
        rgenero.setClickable(false);
        retario.setClickable(false);
        ability.setClickable(false);
    }


    private void takeGPSLocation() {

        gps = new GPSTracker(getApplicationContext());
        if(gps.canGetLocation){
            setLat(gps.getLatitude());
            setLng(gps.getLongitude());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return false;
    }


}
