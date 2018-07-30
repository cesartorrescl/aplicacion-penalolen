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

public class register_view extends AppCompatActivity {

    Global claseGlobal = new Global();
    private String ip = claseGlobal.getIp();

    private volleyy volley;
    protected RequestQueue fRequestQueue;

    private Context ctx;

    private EditText user;
    private EditText email;
    private EditText password;
    private EditText confpassword;
    private Button finalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);
        ctx = register_view.this;

        finalizar = (Button) findViewById(R.id.btnFinalizar);
        user = (EditText) findViewById(R.id.txtUsuario);
        email = (EditText) findViewById(R.id.txtCorreo);
        password = (EditText) findViewById(R.id.txtPassword);
        confpassword = (EditText) findViewById(R.id.txtConfPassword);
        volley = volleyy.getInstance(ctx);
        fRequestQueue = volley.getRequestQueue();

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ctx, "AYY LMAO", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    /*private boolean makeRequest(final String user, final String email, final String password) {

    }*/
}

