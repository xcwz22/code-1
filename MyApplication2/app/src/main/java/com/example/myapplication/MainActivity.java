package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView toLogin;
    Button create;
    EditText editTextName, editLastName;

    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_main);

        ids();
        actions();
    }

    private void ids() {
        toLogin = findViewById(R.id.textView3);
        create = findViewById(R.id.nextButton);
        editTextName = findViewById(R.id.editTextName);
        editLastName = findViewById(R.id.editLastName);
    }

    private void actions() {
        toLogin.setOnClickListener(v -> loginActivity());
        create.setOnClickListener(v -> register());
    }

    private void register() {
        String user = editTextName.getText().toString();
        String password = editLastName.getText().toString();

        URL url = null;
        try {
            url = new URL("https://tristichic-taxes.000webhostapp.com/connection.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        URLConnection urlcon = null;
        try {
            urlcon = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlcon.getURL().toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, response.trim(), Toast.LENGTH_SHORT).show();
            }
        }, error -> Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user",user);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }

    public void loginActivity(){
        Intent intent = new Intent(this,login.class);
        startActivity(intent);
    }

}

