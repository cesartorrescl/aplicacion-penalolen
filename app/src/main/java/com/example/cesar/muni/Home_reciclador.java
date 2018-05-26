package com.example.cesar.muni;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Home_reciclador extends AppCompatActivity {

    TextView usuario;
    private volleyy volley;
    protected RequestQueue fRequestQueue;
    StringBuilder sb=new StringBuilder();
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_reciclador);
        ctx = Home_reciclador.this;
        volley = volleyy.getInstance(ctx);
        fRequestQueue = volley.getRequestQueue();
        Intent intent = getIntent();
        String message = intent.getStringExtra("token");
        usuario = (TextView) findViewById(R.id.textView);
        sb.append("Bearer ");
        sb.append(message);
        makeRequest();



    }

    private void makeRequest(){
        String url = "http://192.168.43.161/api-2/public/api/details";
        JsonObjectRequest request = new JsonObjectRequest (Request.Method.POST,url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonArray) {
                usuario.setText(jsonArray.toString());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

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
