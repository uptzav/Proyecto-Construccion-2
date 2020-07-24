package com.apk.appinfocovid19.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apk.appinfocovid19.Config.env;
import com.apk.appinfocovid19.Model.Diagnostico;
import com.apk.appinfocovid19.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DiagnosticoActivity extends AppCompatActivity {

    RadioGroup feelradioGroup, fevelradioGroup, coughradioGroup, fatigueradioGroup, breatheadioGroup, whereareadioGroup;
    Button sendButton;
    Toolbar toolbar;
    RadioButton radioButton;
    EditText edtmaildisplay, edtbodytempdisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostico);
        toolbar = findViewById(R.id.toolbar);

        edtbodytempdisplay = findViewById(R.id.edtbodytempdisplay);

        feelradioGroup = findViewById(R.id.feelradioGroup);
        fevelradioGroup = findViewById(R.id.fevelradioGroup);
        coughradioGroup = findViewById(R.id.coughradioGroup);
        fatigueradioGroup = findViewById(R.id.fatigueradioGroup);
        breatheadioGroup = findViewById(R.id.breatheadioGroup);
        whereareadioGroup = findViewById(R.id.whereareadioGroup);

        sendButton = findViewById(R.id.sendButton);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendButton.setEnabled(false);
                Diagnostico obj = new Diagnostico();
                obj.setTemperaturaCorporal(edtbodytempdisplay.getText().toString());
                obj.setCongestionNasal(obtenerValorRadioButton(feelradioGroup.getCheckedRadioButtonId()));
                obj.setIndigestionEstomacal(obtenerValorRadioButton(fevelradioGroup.getCheckedRadioButtonId()));
                obj.setTieneTos(obtenerValorRadioButton(coughradioGroup.getCheckedRadioButtonId()));
                obj.setFatiga(obtenerValorRadioButton(fatigueradioGroup.getCheckedRadioButtonId()));
                obj.setFaltaAlimento(obtenerValorRadioButton(breatheadioGroup.getCheckedRadioButtonId()));
                obj.setPerdidaGusto(obtenerValorRadioButton(whereareadioGroup.getCheckedRadioButtonId()));


                if (!ValidarCampo(obj.getTemperaturaCorporal())) {
                    Toast.makeText(DiagnosticoActivity.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }
                EnviarData(obj);
            }
        });
    }

    public void EnviarData(final Diagnostico obj) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, env.apiEnviarDiagnostico,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean status = jsonResponse.getBoolean("status");
                            String mensaje = jsonResponse.getString("message");
                            boolean respuesta;
                            if (status) {
                                Toast.makeText(DiagnosticoActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(DiagnosticoActivity.this, MenuActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(DiagnosticoActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(DiagnosticoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String error_msg = error.getMessage();
                        Toast.makeText(DiagnosticoActivity.this, error_msg, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("temperaturaCorporal", obj.getTemperaturaCorporal());
                params.put("congestionNasal", obj.getCongestionNasal());
                params.put("indigestionEstomacal", obj.getIndigestionEstomacal());
                params.put("tieneTos", obj.getTieneTos());
                params.put("fatiga", obj.getFatiga());
                params.put("faltaAlimento", obj.getFaltaAlimento());
                params.put("perdidaGusto", obj.getPerdidaGusto());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(DiagnosticoActivity.this);
        requestQueue.add(stringRequest);
    }

    public boolean ValidarCampo(String tmp) {
        return !tmp.isEmpty();
    }

    public String obtenerValorRadioButton(int radioId) {
        radioButton = findViewById(radioId);
        return radioButton.getText().toString();
    }
}