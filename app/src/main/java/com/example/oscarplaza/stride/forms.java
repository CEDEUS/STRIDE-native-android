package com.example.oscarplaza.stride;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oscarplaza.stride.Utils.Utils;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.IndicatorType;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;
import com.warkiz.widget.TickMarkType;

public class forms extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // Atributos para su uso y metodos de Edicion en los listner
    private static String  TAG = "forms";
    private String radioButtonInicatos = "";

    public String getRadioButtonInicatos() {
        return radioButtonInicatos;
    }

    public void setRadioButtonInicatos(String radioButtonInicatos) {
        this.radioButtonInicatos = radioButtonInicatos;
    }

    public int isSwitchIscheked() {
        return switchIscheked;
    }

    public void setSwitchIscheked(int switchIscheked) {
        this.switchIscheked = switchIscheked;
    }

    private int switchIscheked = 0;

    public int getSeekbarThumbr() {
        return seekbarThumbr;
    }

    public void setSeekbarThumbr(int seekbarThumbr) {
        this.seekbarThumbr = seekbarThumbr;
    }

    private int seekbarThumbr = 0;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);     //  Fixed Portrait orientation

        setContentView(R.layout.activity_forms);
        RadioGroup rg = (RadioGroup)findViewById(R.id.radiog);
        Button btnred =(Button)findViewById(R.id.button_Red);
        Button btnyellow =(Button)findViewById(R.id.button_2);
        Button btngreen =(Button)findViewById(R.id.button_3);
        String[] array = {getString(R.string.Kid), getString(R.string.adole),getString(R.string.adults),getString(R.string.Elderly)};
         final IndicatorSeekBar discrete_ticks_texts1 = IndicatorSeekBar
                .with(this)
                .max(2000)
                .min(1000)
                .progress(100)
                .tickCount(4)
                .showTickMarksType(TickMarkType.OVAL)
                .tickTextsArray(array)
                .tickMarksSize(10)//dp
                .showTickTexts(true)
                .indicatorTextColor(Color.parseColor("#ffffff"))
                .showIndicatorType(IndicatorType.CIRCULAR_BUBBLE)
                .thumbColor(Color.parseColor("#ff0000"))
                .tickTextsSize(8)//sp
                .indicatorTextSize(8)//sp
                .thumbSize(12)
                .trackProgressSize(8)
                .trackBackgroundSize(8)
                .build();
        String[] arraycapacity = {getString(R.string.normal), getString(R.string.asistida),getString(R.string.mecanica)};
        final IndicatorSeekBar dtt = IndicatorSeekBar
                .with(this)
                .max(2000)
                .min(1000)
                .progress(100)
                .tickCount(3)
                .showTickMarksType(TickMarkType.OVAL)
                .tickTextsArray(arraycapacity)
                .tickMarksSize(10)//dp
                .showTickTexts(true)
                .indicatorTextColor(Color.parseColor("#ffffff"))
                .showIndicatorType(IndicatorType.CIRCULAR_BUBBLE)
                .thumbColor(Color.parseColor("#ff0000"))
                .tickTextsSize(8)//sp
                .indicatorTextSize(8)//sp
                .thumbSize(12)
                .trackProgressSize(8)
                .trackBackgroundSize(8)
                .build();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final LinearLayout content = (LinearLayout) findViewById(R.id.seekbarcontent);
        final LinearLayout contentstatus = (LinearLayout)findViewById(R.id.content_refer_physicar_capacity);
        TextView tv2 = new TextView(this);
        TextView textView1 = new TextView(this);
        tv2.setText(R.string.movility);
        textView1.setText(R.string.groupE);
        content.addView(textView1);
        contentstatus.addView(tv2);


        discrete_ticks_texts1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        content.addView(discrete_ticks_texts1);
        discrete_ticks_texts1.setIndicatorTextFormat("${TICK_TEXT}");
        discrete_ticks_texts1.setMinimumWidth(550);

        dtt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        contentstatus.addView(dtt);
        dtt.setIndicatorTextFormat("${TICK_TEXT}");
        dtt.setMinimumWidth(550);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        RadioButton rb=(RadioButton)findViewById(rg.getCheckedRadioButtonId());
        setRadioButtonInicatos(rb.getText().toString());
        // lo que esta abajo esta poco claro
        setSwitchIscheked(0);
        setSeekbarThumbr(0);
        // eso de arriba
        //Listners y los seteo se las variables
        btnred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetAndDrivenparams("Rojo");
            }
        });
        btnyellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetAndDrivenparams("Amarillo");

            }
        });
        btngreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetAndDrivenparams("Vende");
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb=(RadioButton)findViewById(checkedId);
                setRadioButtonInicatos(rb.getText().toString());
            }
        });
        dtt.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                setSwitchIscheked(seekParams.thumbPosition);

            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });
        discrete_ticks_texts1.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                setSeekbarThumbr(seekParams.thumbPosition);
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });
//hasta aca

    }

    private void GetAndDrivenparams(String semaforo) {
        takeGPSLocation();
        Utils u = new Utils();
        Log.d(TAG, "GetAndDrivenparams: _____________________________________________________");

        Log.d(TAG, "GetAndDrivenparams: possition del seekbar "+getString(u.GetRangeName(getSeekbarThumbr())));
        Log.d(TAG, "GetAndDrivenparams:  id del sexo "+getRadioButtonInicatos());


        Log.d(TAG, "GetAndDrivenparams: "+isSwitchIscheked());

        Log.d(TAG, "GetAndDrivenparams: semaforo "+semaforo);
        Log.d(TAG, "GetAndDrivenparams: latitud "+getLat());
        Log.d(TAG, "GetAndDrivenparams: longitud "+getLng());
        Log.d(TAG, "GetAndDrivenparams: _____________________________________________________");



    }

    private void takeGPSLocation() {

        gps = new GPSTracker(getApplicationContext());
        if(gps.canGetLocation){
            setLat(gps.getLatitude());
            setLng(gps.getLongitude());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.forms, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
