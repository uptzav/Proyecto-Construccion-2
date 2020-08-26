package com.apk.appinfocovid19.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apk.appinfocovid19.Config.env;
import com.apk.appinfocovid19.Model.Donacion;
import com.apk.appinfocovid19.Model.ReporteCaso;
import com.apk.appinfocovid19.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DonacionActivity extends AppCompatActivity  implements  AdapterView.OnItemSelectedListener {

    Spinner donationspinner;
    EditText foodqtext, addtext, donorname;
    Toolbar toolbar;
    Button button;

    FirebaseStorage storage;

    EditText txtcelular;
    EditText txtnombre_pro;
    android.app.AlertDialog.Builder builder1;
    AlertDialog alert;
    private int currentAnimationFrame = 0;
    private LottieAnimationView animationView;

   public static String latiud="0";
   public  static String longi="0";
   private ProgressDialog progressDialog;

    private DatabaseReference databaseReference;

    Button btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donacion);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button = findViewById(R.id.fooddsendButton);
        donationspinner = findViewById(R.id.donationspinner);
        //foodqspinner = findViewById(R.id.foodqspinner);
        txtnombre_pro=(EditText)findViewById(R.id.txtnombre);

        txtcelular=(EditText)findViewById(R.id.txtCelular);
        btn=(Button)findViewById(R.id.btnubicar);

        foodqtext = findViewById(R.id.foodqtext);
        addtext = findViewById(R.id.addtext);
        donorname = findViewById(R.id.donorname);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.doptions, android.R.layout.simple_spinner_item);
        donationspinner.setAdapter(adapter);

      //  ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.foodarray, android.R.layout.simple_spinner_item);
       // foodqspinner.setAdapter(adapter2);


      //  donationspinner.setOnItemSelectedListener(this);

        donationspinner.setOnItemSelectedListener(this);
        String tipo_Donacion="";

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


                String nombreproducto=txtnombre_pro.getText().toString();  //npmbre producto
                String cantidad = foodqtext.getText().toString();
                String direccion = addtext.getText().toString();
                String celular =txtcelular.getText().toString();
                String nombreDonante = donorname.getText().toString();

                if (!ValidarCampo(nombreproducto)){
                    txtnombre_pro.setError("Campo Requerido");
                    //  Toast.makeText(DonacionActivity.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!ValidarCampo(cantidad)){
                    foodqtext.setError("Campo Requerido");
                  //  Toast.makeText(DonacionActivity.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!ValidarCampo(direccion)) {
                    addtext.setError("Campo Requerido");
                  //  Toast.makeText(DonacionActivity.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!ValidarCampo(celular)) {
                    txtcelular.setError("Campo Requerido");
                    // Toast.makeText(DonacionActivity.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!ValidarCampo(nombreDonante)) {
                    donorname.setError("Campo Requerido");
                   // Toast.makeText(DonacionActivity.this, "El campo esta vacio", Toast.LENGTH_SHORT).show();
                    return;
                }

              //  obj.setCalidadAlimentos(foodqspinner.getSelectedItem().toString());


                SharedPreferences preferences= getSharedPreferences("mitoken", Context.MODE_PRIVATE);
                String token=preferences.getString("token","no existe we");

                obj.setToken(token);
                obj.setTipoDonacion(donationspinner.getSelectedItem().toString());
                obj.setNoombre_producto(nombreproducto);
                obj.setCantidad(Integer.parseInt(cantidad));
                obj.setDireccion(direccion);
                obj.setCelular(celular);
                obj.setLatitud(latiud);
                obj.setLongitud(longi);
                obj.setEstado_recepcion("0");
                obj.setNombreDonante(nombreDonante);
             //   EnviarData(obj);
                EnviarData2(obj);

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonacionActivity.this,UbicacionActivity.class));
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        if (donationspinner.getSelectedItem().equals("Fondos")){
            //Toast.makeText(this, listView.getItemAtPosition(position) +" " +PrecioCaballeros[position], Toast.LENGTH_SHORT).show();
          //  Toast.makeText(this, "Eligio Fondos", Toast.LENGTH_SHORT).show();
            startActivity( new Intent(DonacionActivity.this,FondosActivity.class));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static boolean ValidarCampo(String tmp) {
        return !tmp.isEmpty();
    }

    public  void EnviarData2( Donacion obj)
    {

        progressDialog =new ProgressDialog(DonacionActivity.this);
        databaseReference = FirebaseDatabase.getInstance().getReference("Donaciones");

        progressDialog.setTitle("Enviando");
        progressDialog.setMessage("Cargando");
        progressDialog.show();
        String key = databaseReference.push().getKey();
        obj.setKey_donacion(key);

        databaseReference.child(key).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){

                 //   Toast.makeText(DonacionActivity.this, "Enviado", Toast.LENGTH_SHORT).show();
                    //  progressDialog.dismiss();
                    progressDialog.dismiss();
                    DialogCheck();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DonacionActivity.this, "ERror", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private  void  DialogCheck(){


        builder1 = new AlertDialog.Builder(DonacionActivity.this);
        Button btcerrrar;
        TextView tvestado;
        View v = LayoutInflater.from(DonacionActivity.this).inflate(R.layout.dialogo_ok, null);
        animationView = v.findViewById(R.id.animation_viewcheck);
        resetAnimationView();
        animationView.playAnimation();
        builder1.setView(v);
        btcerrrar=(Button)v.findViewById(R.id.idbtncerrardialogo);

        btcerrrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });

        alert  = builder1.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        alert.show();

    }


    private void resetAnimationView() {
        currentAnimationFrame = 0;
        animationView.addValueCallback(new KeyPath("**"), LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return null;
                    }
                }
        );
    }

    //myql ---
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