package com.example.cesar.muni;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login_view extends AppCompatActivity {



    private volleyy volley;
    protected RequestQueue fRequestQueue;

    private Context ctx;

    private String url = "http://192.168.1.92:8000/api/details";

    private Button iniciar;
    private Button registrarse;
    private EditText user;
    private EditText pass;
    private Boolean aux;
    private login_controller x;
    public String p;
    private JSONObject respuesta;
    boolean pipi=false;
    StringBuilder sb=new StringBuilder();


    public Login_view() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);
        ctx = Login_view.this;

        iniciar = (Button) findViewById(R.id.btnIniciar);
        registrarse = (Button) findViewById(R.id.btnRegistrarse);
        x = new login_controller();
        user = (EditText) findViewById(R.id.txtUsuario);
        pass = (EditText) findViewById(R.id.txtPassword);
        volley = volleyy.getInstance(ctx);
        fRequestQueue = volley.getRequestQueue();

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                makeRequest2(user.getText().toString(),pass.getText().toString());









            }
        });


    }



    private void makeRequest(){
        String url = "http://192.168.43.161/api-2/public/api/details";
        JsonObjectRequest  request = new JsonObjectRequest (Request.Method.POST,url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonArray) {
               Log.d("ss",jsonArray.toString());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(ctx,"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                params.put("Accept","application/json");
                params.put("Authorization",sb.toString());
                return params;
            }
        }
        ;
        addToQueue(request);
    }


    private boolean makeRequest2(final String user, final String pass){
        String url = "http://192.168.43.161/api-2/public/api/login2";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            respuesta = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ctx,"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();
                        }
                        p= null;
                        try {
                            p = respuesta.getJSONObject("success").get("token").toString();
                            Intent intent = new Intent(ctx,Home_reciclador.class);
                            intent.putExtra("token",p);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ctx,"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(ctx,"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", user);
                params.put("password", pass);

                return params;
            }
        };

        addToQueue(postRequest);
        return pipi;
    }

    public void addToQueue(Request request) {
        if (request != null) {
            request.setTag(this);
            if (fRequestQueue == null)
                fRequestQueue = volley.getRequestQueue();
            request.setRetryPolicy(new DefaultRetryPolicy(
                    60000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            ));

            fRequestQueue.add(request);
        }
    }


}






