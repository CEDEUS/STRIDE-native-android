package com.example.oscarplaza.stride;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class ConfigOrNotSend {
    public void showDialog(final Context activity, final Observacion latLng){
        DialogPlus dialog = DialogPlus.newDialog(activity)
                .setAdapter(new Simpleadaptera())
                .setContentHolder(new ViewHolder(R.layout.content_dialog2))
                .setHeader(R.layout.header2)
                .setFooter(R.layout.footer2)
                .setGravity(Gravity.CENTER)


                .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                .create();
        View header = dialog.getHeaderView();
        View content = dialog.getHolderView();
        TextView tx = (TextView)content.findViewById(R.id.information);
        tx.setText("You collected "+latLng.getData().size()+" points.\n Do you want to save?");
        Button btnsave = (Button)content.findViewById(R.id.footer_close_button);

        Button btndontsave = (Button)content.findViewById(R.id.footer_confirm_button);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences prefs = activity.getSharedPreferences("loginPrefs", MODE_PRIVATE);

                final String tokken = prefs.getString("token", "");//"No name defined" is the default value.
                int idName = prefs.getInt("id", 0); //0 is the default value.
                //final String tokken = prefs.getString("token", "");
                //String id = prefs.getString("id", "");
                Gson gson = new Gson();
                latLng.setCreate_by("http://strideapi.cedeus.cl/api/users/" + idName + "/");
                ArrayList<PuntoVotadosOld> p = new ArrayList<PuntoVotadosOld>();
                Long tsLong = System.currentTimeMillis()/1000;
                String ts = tsLong.toString();
                ObservacionOld Oold = new ObservacionOld(p);
                for (PuntoVotados Pnew:latLng.getData())
                {

                    Oold.getPuntos().add(new PuntoVotadosOld(ts,Pnew.getVotacion(),latLng.getAge(),latLng.getAbility(),latLng.getSex(),ts,Pnew.getLat(),latLng.getCreate_by(),Pnew.getLon(),Pnew.getHdop(),latLng.getVersion()));

                }
                String jsArrayOld = gson.toJson(Oold.getPuntos());
                String jsArray = gson.toJson(latLng);
                final String requestBody2 = jsArrayOld;
                final String requestBody = jsArray;
                String EndPoint = "http://strideapi.cedeus.cl/observed/";

                RequestQueue queue = Volley.newRequestQueue(activity);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoint, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String EndPoint2 = "http://strideapi.cedeus.cl/points/";
                        Toast.makeText(activity,"SAVED...",Toast.LENGTH_SHORT).show();

                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                Intent intent = new Intent(activity, LoginActivity.class);
                                activity.startActivity(intent);


                            }

                        },100);



                        RequestQueue queue = Volley.newRequestQueue(activity);
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoint2, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(activity,"SAVED...",Toast.LENGTH_SHORT).show();

                                new Handler().postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(activity, LoginActivity.class);
                                        activity.startActivity(intent);


                                    }

                                },100);

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("TAG", "onErrorResponse: "+error.getMessage());


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
                        Log.d("TAG", "onErrorResponse: "+error.getMessage());
                        Log.d("TAG", "onErrorResponse: "+error.getCause());



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
        });
        btndontsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, CheckButton.class);
                activity.startActivity(i);
            }
        });



        dialog.show();

    }
}
