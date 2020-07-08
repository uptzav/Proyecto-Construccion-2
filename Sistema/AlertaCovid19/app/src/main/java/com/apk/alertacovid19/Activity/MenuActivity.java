/*
 * @(#)class_CovidPais.java 1.1 30/06/20
 *
 * UPT
 * Construccion de Software II.
 */

/**
 *
 * @author Mireya Pilco
 * @version 1.1, 01/06/20
 * @since 1.0
 */

package com.apk.alertacovid19.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apk.alertacovid19.Model.class_ModeloPais;
import com.apk.alertacovid19.Config.class_ConfiguracionAPI;
import com.apk.alertacovid19.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    private GoogleMap map;
    private List<class_ModeloPais> paisList;
    AppCompatImageView menuButton;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    TextView confirmedCountText, deathCountText, recoveredCountText, titleCasesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        menuButton = findViewById(R.id.menuButton);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(navigationView);
            }
        });

        confirmedCountText = findViewById(R.id.confirmedCountText);
        deathCountText = findViewById(R.id.deathCountText);
        recoveredCountText = findViewById(R.id.recoveredCountText);
        titleCasesText = findViewById(R.id.titleCasesText);
        paisList = new ArrayList<>();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        VerEstadisticasGlobales();
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        map = googleMap;
        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.stylemap));
        StringRequest stringRequest = new StringRequest(Request.Method.GET, class_ConfiguracionAPI.api_all_country, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        final class_ModeloPais pais = new class_ModeloPais();
                        JSONObject json = null;
                        JSONObject jsonSub = null;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            json = jsonArray.getJSONObject(i);
                            jsonSub = json.getJSONObject("countryInfo");

                            pais.setLatitud(jsonSub.getDouble("lat"));
                            pais.setLongitud(jsonSub.getDouble("long"));
                            pais.setNombrePais(json.getString("country"));
                            paisList.add(pais);

                            class_ModeloPais location = paisList.get(i);
                            Marker marker = null;
                            LatLng position = new LatLng(location.getLatitud(), location.getLongitud());
                            marker = googleMap.addMarker(new MarkerOptions().position(position).title(location.getNombrePais()));
                            if (pais.getNombrePais().equals("Peru")) {
                                CameraPosition camPos = new CameraPosition(position, 5, 0, 0);
                                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(camPos));
                                marker.showInfoWindow();
                            }

                            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker) {
                                    String markertitle = marker.getTitle();
                                    VerEstadisticasPais(markertitle);
                                    return false;
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MenuActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if (menuItem.getItemId() == R.id.nav_global_cases) {
            Toast.makeText(this, "Estadisticas Globales", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void VerEstadisticasGlobales() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, class_ConfiguracionAPI.api_all_countries, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        confirmedCountText.setText(String.valueOf(jsonObject.getInt("cases")));
                        deathCountText.setText(String.valueOf(jsonObject.getInt("deaths")));
                        recoveredCountText.setText(String.valueOf(jsonObject.getInt("recovered")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MenuActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void VerEstadisticasPais(final String nombrePais) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, class_ConfiguracionAPI.api_detail_country + nombrePais, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        confirmedCountText.setText(String.valueOf(jsonObject.getInt("cases")));
                        deathCountText.setText(String.valueOf(jsonObject.getInt("deaths")));
                        recoveredCountText.setText(String.valueOf(jsonObject.getInt("recovered")));
                        titleCasesText.setText("COVID-19 Casos en " + nombrePais);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MenuActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
