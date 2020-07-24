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
import com.apk.appinfocovid19.Model.ReporteCaso;
import com.apk.appinfocovid19.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ReportarCasoActivity extends AppCompatActivity {

    RadioGroup radioGrupoTipoCaso, radioGrupoSexo;
    RadioButton radioButton;
    EditText txtApellidosPaciente, txtNombresPaciente, txtEdad, txtCelular, txtDistrito, txtDireccion, txtOtrasObservaciones;
    Button button;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_caso);
        toolbar = findViewById(R.id.toolbar);

        radioGrupoTipoCaso = findViewById(R.id.radioGrupoTipoCaso);
        radioGrupoSexo = findViewById(R.id.radioGrupoSexo);
        txtApellidosPaciente = findViewById(R.id.txtApellidosPaciente);
        txtNombresPaciente = findViewById(R.id.txtNombresPaciente);
        txtEdad = findViewById(R.id.txtEdad);
        txtCelular = findViewById(R.id.txtCelular);
        txtDistrito= findViewById(R.id.txtDistrito);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtOtrasObservaciones = findViewById(R.id.txtOtrasObservaciones);

        button = findViewById(R.id.sendButton);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReporteCaso obj = new ReporteCaso();
                obj.setTipoCaso(obtenerValorRadioButton(radioGrupoTipoCaso.getCheckedRadioButtonId()));
                obj.setApellidoPaciente(txtApellidosPaciente.getText().toString());
                obj.setNombrePaciente(txtNombresPaciente.getText().toString());
                obj.setSexo(obtenerValorRadioButton(radioGrupoSexo.getCheckedRadioButtonId()));
                obj.setEdad(Integer.parseInt(txtEdad.getText().toString()));
                obj.setCelular(Integer.parseInt(txtCelular.getText().toString()));
                obj.setDistrito(txtDistrito.getText().toString());
                obj.setDireccion(txtDireccion.getText().toString());
                obj.setOtrasObservaciones(txtOtrasObservaciones.getText().toString());

                if (!ValidarCampo(obj.getTipoCaso())){
                    Toast.makeText(ReportarCasoActivity.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!ValidarCampo(obj.getApellidoPaciente())){
                    Toast.makeText(ReportarCasoActivity.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!ValidarCampo(obj.getNombrePaciente())){
                    Toast.makeText(ReportarCasoActivity.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!ValidarCampo(obj.getSexo())){
                    Toast.makeText(ReportarCasoActivity.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!ValidarCampo(String.valueOf(obj.getEdad()))){
                    Toast.makeText(ReportarCasoActivity.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!ValidarCampo(String.valueOf(obj.getCelular()))){
                    Toast.makeText(ReportarCasoActivity.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (!ValidarCampo(obj.getDistrito())){
                    Toast.makeText(ReportarCasoActivity.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!ValidarCampo(obj.getDireccion())){
                    Toast.makeText(ReportarCasoActivity.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!ValidarCampo(obj.getOtrasObservaciones())){
                    Toast.makeText(ReportarCasoActivity.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }

                EnviarData(obj);
            }
        });
    }

    public boolean ValidarCampo(String tmp) {
        return !tmp.isEmpty();
    }

    public void EnviarData(final ReporteCaso obj) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, env.apiEnviarReporteCaso,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean status = jsonResponse.getBoolean("status");
                            String mensaje = jsonResponse.getString("message");
                            boolean respuesta;
                            if (status) {
                                Toast.makeText(ReportarCasoActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(ReportarCasoActivity.this, MenuActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(ReportarCasoActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(ReportarCasoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String error_msg = error.getMessage();
                        Toast.makeText(ReportarCasoActivity.this, error_msg, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tipoCaso",obj.getTipoCaso());
                params.put("apellidoPaciente",obj.getApellidoPaciente());
                params.put("nombrePaciente",obj.getNombrePaciente());
                params.put("sexo",obj.getSexo());
                params.put("edad",String.valueOf(obj.getEdad()));
                params.put("celular",String.valueOf(obj.getCelular()));
                params.put("distrito",obj.getDistrito());
                params.put("direccion",obj.getDireccion());
                params.put("otrasObservaciones",obj.getOtrasObservaciones());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ReportarCasoActivity.this);
        requestQueue.add(stringRequest);
    }

    public String obtenerValorRadioButton(int radioId) {
        radioButton = findViewById(radioId);
        return radioButton.getText().toString();
    }
}