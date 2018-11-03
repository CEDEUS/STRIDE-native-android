package com.example.oscarplaza.stride;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.oscarplaza.stride.Entidades.RespLogin;
import com.example.oscarplaza.stride.Entidades.RespStatics;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class TabBFragment extends Fragment {
    TextView emails;
    TextView ids;
    TextView username;
    TextView date_joined;
    TextView groups;
    TextView  total_observed_person;
    TextView total_points_voted;
    TextView today_observed_person;
    TextView days_surveyed;

    public TextView getEmails() {
        return emails;
    }

    public void setEmails(TextView emails) {
        this.emails = emails;
    }

    public TextView getIds() {
        return ids;
    }

    public void setIds(TextView ids) {
        this.ids = ids;
    }

    public TextView getUsername() {
        return username;
    }

    public void setUsername(TextView username) {
        this.username = username;
    }

    public TextView getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(TextView date_joined) {
        this.date_joined = date_joined;
    }

    public TextView getGroups() {
        return groups;
    }

    public void setGroups(TextView groups) {
        this.groups = groups;
    }

    public TextView getTotal_observed_person() {
        return total_observed_person;
    }

    public void setTotal_observed_person(TextView total_observed_person) {
        this.total_observed_person = total_observed_person;
    }

    public TextView getTotal_points_voted() {
        return total_points_voted;
    }

    public void setTotal_points_voted(TextView total_points_voted) {
        this.total_points_voted = total_points_voted;
    }

    public TextView getToday_observed_person() {
        return today_observed_person;
    }

    public void setToday_observed_person(TextView today_observed_person) {
        this.today_observed_person = today_observed_person;
    }

    public TextView getDays_surveyed() {
        return days_surveyed;
    }

    public void setDays_surveyed(TextView days_surveyed) {
        this.days_surveyed = days_surveyed;
    }

    public TabBFragment() {
        // Required empty public constructor
    }



    private void getInfo() {
        SharedPreferences prefs = this.getActivity().getSharedPreferences("loginPrefs", MODE_PRIVATE);
        final String tokken = prefs.getString("token", "");
        String EndPoint = "http://146.155.17.18:18080/api/me";
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, EndPoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("TabBFragment", "onResponse: " + response);
                Gson g = new Gson();
                RespStatics r = g.fromJson(response,RespStatics.class);
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date date =new Date();
                try {
                     date  = format.parse(r.getDateJoined());
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                getDate_joined().setText(format.format(date));
                getDays_surveyed().setText(r.getDaysSurveyed().toString());
                getEmails().setText(r.getEmail().toString());
                //getGroups().setText(r.getGroups().toString());
                getIds().setText(r.getId().toString());
                getUsername().setText(r.getUsername().toString());
                getEmails().setText(r.getEmail().toString());
                getToday_observed_person().setText(r.getTodayObservedPerson().toString());
                getTotal_observed_person().setText(r.getTotalObservedPerson().toString());
                getTotal_points_voted().setText(r.getTotalPointsVoted().toString());





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TabBFragment", "onResponse: " + error.getMessage());
                Toast.makeText(getActivity(),"hola"+error.getMessage(),Toast.LENGTH_SHORT).show();


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
        };
        queue.add(stringRequest);



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tab_b, container, false);
        setEmails((TextView)rootView.findViewById(R.id.email_ex_ex));
        setIds((TextView)rootView.findViewById(R.id.id_for_user_ex));
        setUsername((TextView)rootView.findViewById(R.id.user_name_in_app_ex));
        setDate_joined((TextView)rootView.findViewById(R.id.date_joined_in_stride_ex));
        setTotal_observed_person((TextView)rootView.findViewById(R.id.total_observed_person_ex));
        setTotal_points_voted((TextView)rootView.findViewById(R.id.total_points_voted_ex));
        setDays_surveyed((TextView)rootView.findViewById(R.id.days_surveyed_ex));
        setToday_observed_person((TextView)rootView.findViewById(R.id.today_observed_person_ex));
        getInfo();
        return rootView;
    }
}
