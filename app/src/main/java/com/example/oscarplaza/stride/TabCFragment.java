package com.example.oscarplaza.stride;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import com.example.oscarplaza.stride.Entidades.RespMylastPoint;
import com.example.oscarplaza.stride.Entidades.RespStatics;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class TabCFragment extends android.support.v4.app.Fragment {

    public TabCFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lastpoint, container, false);
        ListView lv= (ListView) rootView.findViewById(R.id.list_last);

        getData(lv);

        return rootView;

    }

    private void getData(final ListView lv) {
        SharedPreferences prefs = this.getActivity().getSharedPreferences("loginPrefs", MODE_PRIVATE);

        final String tokken = prefs.getString("token", "");//"No name defined" is the default value.
        int idName = prefs.getInt("id", 0); //0 is the default value.
        int count = prefs.getInt("count",10);// 10 if no initialize
        String EndPoint = "http://146.155.17.18:18080/my_last_point?count="+count;
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, EndPoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson g = new Gson();
                RespMylastPoint r = g.fromJson(response,RespMylastPoint.class);

                //ObservacionAdapter oba = new ObservacionAdapter(getActivity(),r.getResults());


               // lv.setAdapter(oba);

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
        }

