/*
 * @(#)class_CovidPaisDetalle.java 1.1 25/06/20
 *
 * UPT
 * Construccion de Software II.
 */

/**
 *
 * @author Fiorella Salamanca
 * @version 1.1, 25/06/20
 * @since 1.0
 */

package com.milifi.myappcovid19.ui.country;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.milifi.myappcovid19.R;

public class class_CovidPaisDetalle extends AppCompatActivity {

    TextView tvDetallePaisNombre, tvDetalleTotalCasos, tvDetalleHoyCasos, tvDetalleTotalMuertes,
            tvDetalleHoyMuertes, tvDetalleTotalRecuperados, tvDetalleTotalActivos, tvDetalleTotalCriticos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_pais_detalle);

        /* Llamar a las vistas */
        tvDetallePaisNombre = findViewById(R.id.tvDetallePaisNombre);
        tvDetalleTotalCasos = findViewById(R.id.tvDetalleTotalCasos);
        tvDetalleHoyCasos = findViewById(R.id.tvDetalleHoyCasos);
        tvDetalleTotalMuertes = findViewById(R.id.tvDetalleTotalMuertes);
        tvDetalleHoyMuertes = findViewById(R.id.tvDetalleHoyMuertes);
        tvDetalleTotalRecuperados = findViewById(R.id.tvDetalleTotalRecuperados);
        tvDetalleTotalActivos = findViewById(R.id.tvDetalleTotalActivos);
        tvDetalleTotalCriticos = findViewById(R.id.tvDetalleTotalCriticos);

        /* Llamar a CovidPais */
        class_CovidPais covidPais = getIntent().getParcelableExtra("EXTRA_COVID");

        /* Mostrar en la Vista */
        tvDetallePaisNombre.setText(covidPais.getmCovidPais());
        tvDetalleTotalCasos.setText(Integer.toString(covidPais.getmCasos()));
        tvDetalleHoyCasos.setText(covidPais.getmHoyCasos());
        tvDetalleTotalMuertes.setText(covidPais.getmMuertes());
        tvDetalleHoyMuertes.setText(covidPais.getmHoyMuertes());
        tvDetalleTotalRecuperados.setText(covidPais.getmRecuperados());
        tvDetalleTotalActivos.setText(covidPais.getmActivos());
        tvDetalleTotalCriticos.setText(covidPais.getmCriticos());
    }
}
