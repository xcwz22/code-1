package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class login extends MainActivity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initializeButtons();

    }

    public void initializeButtons() {


            Button loginButton = findViewById(R.id.loginButton);

            loginButton.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    TextView loginEmail = findViewById(R.id.loginEmail);
                    TextView loginPassword = findViewById(R.id.loginPassword);
                    String LoginEmail = loginEmail.getText().toString();
                    String LoginPassword = loginPassword.getText().toString();
                    Button loginButton = findViewById(R.id.loginButton);


                    String url1 = "https://tristichic-taxes.000webhostapp.com/login.php";

                    // BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                    StringRequest sr = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                @Override
                        public void onResponse(String response) {
                            TextView txtv = findViewById(R.id.textView6);
                            txtv.setText(response);
                            Toast.makeText(login.this, response.trim(), Toast.LENGTH_SHORT).show();
                            if (response.equals("Login successful!")) {
                                startWelcomeActivity();
                            }

                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(login.this, error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("user", LoginEmail);
                            params.put("password", LoginPassword);
                            return params;

                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(login.this);
                    requestQueue.add(sr);


                }
            });

    }
    public void startWelcomeActivity(){
        Intent intent = new Intent(this, welcome.class);
        startActivity(intent);

}}




