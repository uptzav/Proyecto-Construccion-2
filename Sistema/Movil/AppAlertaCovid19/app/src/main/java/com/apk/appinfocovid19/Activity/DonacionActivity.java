package com.apk.appinfocovid19.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apk.appinfocovid19.Config.env;
import com.apk.appinfocovid19.Model.Donacion;
import com.apk.appinfocovid19.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DonacionActivity extends AppCompatActivity {

    Spinner donationspinner, foodqspinner;
    EditText foodqtext, addtext, donorname;
    Toolbar toolbar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donacion);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button = findViewById(R.id.fooddsendButton);
        donationspinner = findViewById(R.id.donationspinner);
        foodqspinner = findViewById(R.id.foodqspinner);

        foodqtext = findViewById(R.id.foodqtext);
        addtext = findViewById(R.id.addtext);
        donorname = findViewById(R.id.donorname);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.doptions, android.R.layout.simple_spinner_item);
        donationspinner.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.foodarray, android.R.layout.simple_spinner_item);
        foodqspinner.setAdapter(adapter2);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Donacion obj = new Donacion();
                String cantidad = foodqtext.getText().toString();
                String direccion = addtext.getText().toString();
                String nombreDonante = donorname.getText().toString();

                if (!ValidarCampo(cantidad)){
                    Toast.makeText(DonacionActivity.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!ValidarCampo(direccion)) {
                    Toast.makeText(DonacionActivity.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!ValidarCampo(nombreDonante)) {
                    Toast.makeText(DonacionActivity.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }
                obj.setTipoDonacion(donationspinner.getSelectedItem().toString());
                obj.setCalidadAlimentos(foodqspinner.getSelectedItem().toString());
                obj.setCantidad(Integer.parseInt(cantidad));
                obj.setDireccion(direccion);
                obj.setNombreDonante(nombreDonante);

                EnviarData(obj);

                //String obj = donationspinner.getSelectedItem().toString();
            }
        });

    }

    public static boolean ValidarCampo(String tmp) {
        return !tmp.isEmpty();
    }

    public void EnviarData(final Donacion obj) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, env.apiEnviarDonacion,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean status = jsonResponse.getBoolean("status");
                            String mensaje = jsonResponse.getString("message");
                            boolean respuesta;
                            if (status) {
                                Toast.makeText(DonacionActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(DonacionActivity.this, MenuActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(DonacionActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(DonacionActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String error_msg = error.getMessage();
                        Toast.makeText(DonacionActivity.this, error_msg, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tipoDonacion", obj.getTipoDonacion());
                params.put("calidadAlimentos", obj.getCalidadAlimentos());
                params.put("cantidad", String.valueOf(obj.getCantidad()));
                params.put("direccion", obj.getDireccion());
                params.put("nombreDonante", obj.getNombreDonante());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(DonacionActivity.this);
        requestQueue.add(stringRequest);
    }
}