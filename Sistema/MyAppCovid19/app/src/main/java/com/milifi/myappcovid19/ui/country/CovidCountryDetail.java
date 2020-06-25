package com.milifi.myappcovid19.ui.country;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.milifi.myappcovid19.R;

public class CovidCountryDetail extends AppCompatActivity {

    TextView tvDetailCountryName, tvDetailTotalCases, tvDetailTodayCases, tvDetailTotalDeaths,
            tvDetailTodayDeaths, tvDetailTotalRecovered, tvDetailTotalActive, tvDetailTotalCritical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_country_detail);

        // call view
        tvDetailCountryName = findViewById(R.id.tvDetailCountryName);
        tvDetailTotalCases = findViewById(R.id.tvDetailTotalCases);
        tvDetailTodayCases = findViewById(R.id.tvDetailTodayCases);
        tvDetailTotalDeaths = findViewById(R.id.tvDetailTotalDeaths);
        tvDetailTodayDeaths = findViewById(R.id.tvDetailTodayDeaths);
        tvDetailTotalRecovered = findViewById(R.id.tvDetailTotalRecovered);
        tvDetailTotalActive = findViewById(R.id.tvDetailTotalActive);
        tvDetailTotalCritical = findViewById(R.id.tvDetailTotalCritical);


        // call Covid Country
        class_CovidPais classCovidPais = getIntent().getParcelableExtra("EXTRA_COVID");

        // set text view
        tvDetailCountryName.setText(classCovidPais.getmCovidCountry());
        tvDetailTotalCases.setText(Integer.toString(classCovidPais.getmCases()));
        tvDetailTodayCases.setText(classCovidPais.getmTodayCases());
        tvDetailTotalDeaths.setText(classCovidPais.getmDeaths());
        tvDetailTodayDeaths.setText(classCovidPais.getmTodayDeaths());
        tvDetailTotalRecovered.setText(classCovidPais.getmRecovered());
        tvDetailTotalActive.setText(classCovidPais.getmActive());
        tvDetailTotalCritical.setText(classCovidPais.getmCritical());

    }
}
