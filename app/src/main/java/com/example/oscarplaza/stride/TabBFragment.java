package com.example.oscarplaza.stride;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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
    TextView points_today;

    public TextView getPoints_today() {
        return points_today;
    }

    public void setPoints_today(TextView points_today) {
        this.points_today = points_today;
    }

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

                Gson g = new Gson();
                RespStatics r = g.fromJson(response, RespStatics.class);
                Date sdf = null;
                String parsedDate = r.getDateJoined().toString()    ;

                try {
                    sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(r.getDateJoined());
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                     parsedDate = formatter.format(sdf);
                } catch (ParseException e) {
                    e.printStackTrace();
                }



                getDate_joined().setText(parsedDate);
                getDays_surveyed().setText(r.getTodayPoints().toString());
                getEmails().setText(r.getEmail().toString());
                //getGroups().setText(r.getGroups().toString());
                getUsername().setText(r.getUsername().toString());
                getEmails().setText(r.getEmail().toString());
                getToday_observed_person().setText(r.getTodayObservedPerson().toString());
                getTotal_observed_person().setText(r.getTotalObservedPerson().toString());
                getTotal_points_voted().setText(r.getTotalPointsVoted().toString());
                getPoints_today().setText(r.getDaysSurveyed().toString());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TabBFragment", "onResponse: " + error.getMessage());


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
        setUsername((TextView)rootView.findViewById(R.id.user_name_in_app_ex));
        setDate_joined((TextView)rootView.findViewById(R.id.date_joined_in_stride_ex));
        setTotal_observed_person((TextView)rootView.findViewById(R.id.total_observed_person_ex));
        setTotal_points_voted((TextView)rootView.findViewById(R.id.total_points_voted_ex));
        setDays_surveyed((TextView)rootView.findViewById(R.id.days_surveyed_ex));
        setToday_observed_person((TextView)rootView.findViewById(R.id.today_observed_person_ex));
        setPoints_today((TextView)rootView.findViewById(R.id.number_of_points_today_ex));
        getInfo();
        return rootView;
    }
}
