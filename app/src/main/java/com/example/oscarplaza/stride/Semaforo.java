package com.example.oscarplaza.stride;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.oscarplaza.stride.Entidades.Observacion;
import com.example.oscarplaza.stride.Entidades.ObservacionOld;
import com.example.oscarplaza.stride.Entidades.PuntoVotados;
import com.example.oscarplaza.stride.Entidades.PuntoVotadosOld;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Semaforo extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    ArrayList<PuntoVotados> p = new ArrayList<PuntoVotados>();
    private  Observacion observacion = new Observacion(p);

    public String getMarktimeinicio() {
        return marktimeinicio;
    }

    public void setMarktimeinicio(String marktimeinicio) {
        this.marktimeinicio = marktimeinicio;
    }

    private String marktimeinicio;





    public Observacion getObservacion() {
        return observacion;
    }

    public void setObservacion(Observacion observacion) {
        this.observacion = observacion;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_semaforo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        findViewById(R.id.sucsessess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((getObservacion().getData().isEmpty() && getObservacion().getData().contains(null)) || getObservacion().getData().size() <= 0)
                {
                    Toast.makeText(getApplicationContext(),"no votation yet",Toast.LENGTH_SHORT).show();

                }
                else{
                    v.setClickable(false);

                    sendData();


                }
            }
        });
        findViewById(R.id.newerspm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyalert();


        }
        });
    }

    private void showMyalert() {
        ConfigOrNotSend alert = new ConfigOrNotSend();
        alert.showDialog(this,getObservacion());


}

    private void sendData() {
        SharedPreferences prefs = getSharedPreferences("loginPrefs", MODE_PRIVATE);

            final String tokken = prefs.getString("token", "");//"No name defined" is the default value.
            int idName = prefs.getInt("id", 0); //0 is the default value.


        //final String tokken = prefs.getString("token", "");
        //String id = prefs.getString("id", "");
        Gson gson = new Gson();

        getObservacion().setCreate_by("http://146.155.17.18:18080/users/" + idName + "/");
        ArrayList<PuntoVotadosOld> p = new ArrayList<PuntoVotadosOld>();
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();

        ObservacionOld Oold = new ObservacionOld(p);
        for (PuntoVotados Pnew:getObservacion().getData())
        {

            Oold.getPuntos().add(new PuntoVotadosOld(ts,Pnew.getVotacion(),getObservacion().getAge(),getObservacion().getAbility(),getObservacion().getSex(),ts,Pnew.getLat(),getObservacion().getCreate_by(),Pnew.getLon(),Pnew.getHdop(), getObservacion().getVersion()));

        }


        String jsArrayOld = gson.toJson(Oold.getPuntos());
        String jsArray = gson.toJson(getObservacion());


        Log.d("casa", "onResponse: "+jsArray);
        Log.d("casa", "onResponse2: "+jsArrayOld);
        final String requestBody2 = jsArrayOld;


        final String requestBody = jsArray;
        String EndPoint = "http://146.155.17.18:18080/observed/";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String EndPoint2 = "http://146.155.17.18:18080/points/";


                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoint2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        sendExit();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        findViewById(R.id.sucsessess).setClickable(true);


                    }
                }){
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";

                    }


                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Content-Type", "application/json");
                        params.put("Authorization","Token "+tokken);
                        return  params;
                    }




                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        try {
                            return requestBody2 == null ? null : requestBody2.getBytes("utf-8");
                        } catch (UnsupportedEncodingException uee) {
                            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody2, "utf-8");
                            return null;
                        }

                    }

                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        String value="";
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
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                findViewById(R.id.sucsessess).setClickable(false);


            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";

            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("Authorization","Token "+tokken);
                return  params;
            }




@Override
public byte[] getBody() throws AuthFailureError {
try {
    return requestBody == null ? null : requestBody.getBytes("utf-8");
} catch (UnsupportedEncodingException uee) {
    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
    return null;
}

}

@Override
protected Response<String> parseNetworkResponse(NetworkResponse response) {
String value="";
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

    private void sendExit() {
        Toast.makeText(this,"points saved to server",Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(Semaforo.this, LoginActivity.class);
                startActivity(intent);


            }

        },100);
    }

    @Override
public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
getMenuInflater().inflate(R.menu.menu_semaforo, menu);
return false;
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

public void SetData(Observacion observacion) {
setObservacion(observacion);

}
public Observacion getData(){return getObservacion();}

public static class PlaceholderFragment extends Fragment {
/**
* The fragment argument representing the section number for this
* fragment.
*/
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_semaforo, container, false);




            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

        interface SendMessage {
            void sendData(String message);
        }



    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment tabFragment = null;
            switch (position){
                case 0:
                    tabFragment = new semaforoFragment();
                    break;
                case 1:
                    tabFragment = new ListFragmentinSeccion();
                    break;


            }

            return tabFragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String section = null;

            switch (position) {
                case 0:
                    section = getString(R.string.title_activity_semaforo);
                    break;
                case 1:
                    section = getString(R.string.maps_and_votations);
                    break;
            }
            return section;



        }
    }
}
