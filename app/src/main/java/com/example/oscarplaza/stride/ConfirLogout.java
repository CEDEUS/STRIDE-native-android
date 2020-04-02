package com.example.oscarplaza.stride;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.oscarplaza.stride.Entidades.RespLogin;
import com.example.oscarplaza.stride.Entidades.SeverUrl;
import com.google.gson.Gson;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;


import org.json.simple.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class ConfirLogout {
    public void showAlert(final MainActivity _this) {
        final DialogPlus dialog = DialogPlus.newDialog(_this)
                .setAdapter(new Simpleadaptera())
                .setContentHolder(new ViewHolder(R.layout.confir_logout))
                .setGravity(Gravity.CENTER)
                .setExpanded(true)
                .create();
        View content = dialog.getHolderView();
        Button yes = (Button)content.findViewById(R.id.logout_yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _this.confirmlogout();

            }
        });
        Button no = (Button)content.findViewById(R.id.logout_no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();







    }

    public void NewAccount(final LoginActivity _this) {
        final DialogPlus dialog = DialogPlus.newDialog(_this)
                .setAdapter(new Simpleadaptera())
                .setContentHolder(new ViewHolder(R.layout.content_new_user))
                .setGravity(Gravity.CENTER)
                .setExpanded(true,900)
                .create();
        View content = dialog.getHolderView();
        final EditText nuser = (EditText)content.findViewById(R.id.new_user);
        final EditText nemail = (EditText)content.findViewById(R.id.new_email);
        final EditText npassword = (EditText)content.findViewById(R.id.new_password);


        Button confirm = (Button)content.findViewById(R.id.confirm_info);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nuser.getText().length() >=4 && nemail.getText().length() >= 6  && npassword.getText().length() >= 8)
                {
                    crearUsuario(_this,nuser.getText().toString(),nemail.getText().toString(),npassword.getText().toString(),dialog);
                }

            }
        });



        dialog.show();
    }

    private void crearUsuario(final LoginActivity aThis, String user, String email, String password, final DialogPlus dialog)
    {
        final JSONObject jsonBody = new JSONObject();
        jsonBody.put("username", user);
        jsonBody.put("password", password);
        jsonBody.put("email", email);

        final String requestBody = jsonBody.toString();

        RequestQueue queue = Volley.newRequestQueue(aThis);
        String EndPoint = "http://strideapi.cedeus.cl/api/users/create/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(aThis,aThis.getString(R.string.you_now_haveaccount),Toast.LENGTH_LONG).show();
                dialog.dismiss();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(aThis,error.getMessage(),Toast.LENGTH_LONG).show();
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
                return  params;
            }
                            /*

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("username", "usuario");
                            params.put("password","password123");
                            return  params;
                        }*/

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
}
