package com.milifi.myappcovid19.ui.country;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.milifi.myappcovid19.R;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CountryFragment extends Fragment {

    RecyclerView rvCovidPais;
    ProgressBar progressBar;
    CovidPaisAdapter covidPaisAdapter;

    private static final String TAG = CountryFragment.class.getSimpleName();
    List<CovidPais> covidPaises;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_country, container, false);

        // set has option menu as true because we have menu
        setHasOptionsMenu(true);

        // call view
        rvCovidPais = root.findViewById(R.id.rvCovidCountry);
        progressBar = root.findViewById(R.id.progress_circular_country);
        rvCovidPais.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvCovidPais.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_divider));
        rvCovidCountry.addItemDecoration(dividerItemDecoration);

        //call list
        covidPaises = new ArrayList<>();

        // call Volley method
        getDataFromServerSortTotalCases();

        return root;
    }

    private void showRecyclerView() {
        covidPaisAdapter = new CovidPaisAdapter(covidPaises, getActivity());
        rvCovidPais.setAdapter(covidPaisAdapter);

        ItemClickSupport.addTo(rvCovidPais).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedCovidPais(covidPaises.get(position));
            }
        });
    }

    private void showSelectedCovidPais(CovidPais covidPais) {
        Intent covidCovidPaisDetalle = new Intent(getActivity(), CovidPaisDetalle.class);
        covidCovidPaisDetalle.putExtra("EXTRA_COVID", covidPais);
        startActivity(covidCovidPaisDetalle);
    }

    private void getDataFromServerSortTotalCases() {
        String url = "https://corona.lmao.ninja/v2/countries";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);

                            // Extract JSONObject inside JSONObject
                            JSONObject countryInfo = data.getJSONObject("countryInfo");

                            covidPaises.add(new CovidPais(
                                    data.getString("country"), data.getInt("cases"),
                                    data.getString("todayCases"), data.getString("deaths"),
                                    data.getString("todayDeaths"), data.getString("recovered"),
                                    data.getString("active"), data.getString("critical"),
                                    countryInfo.getString("flag")
                            ));
                        }

                        // sort descending
                        Collections.sort(covidPaises, new Comparator<CovidPais>() {
                            @Override
                            public int compare(CovidPais o1, CovidPais o2) {
                                if (o1.getmCases() > o2.getmCases()) {
                                    return -1;
                                } else {
                                    return 1;
                                }
                            }
                        });

                        // Action Bar Title
                        getActivity().setTitle(jsonArray.length() + " Paises");

                        showRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Log.e(TAG, "onResponse: " + error);
                    }
                });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    private void getDataFromServerSortAlphabet() {
        String url = "https://corona.lmao.ninja/v2/countries";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);

                            // Extract JSONObject inside JSONObject
                            JSONObject countryInfo = data.getJSONObject("countryInfo");

                            covidCountries.add(new CovidCountry(
                                    data.getString("country"), data.getInt("cases"),
                                    data.getString("todayCases"), data.getString("deaths"),
                                    data.getString("todayDeaths"), data.getString("recovered"),
                                    data.getString("active"), data.getString("critical"),
                                    countryInfo.getString("flag")
                            ));
                        }

                        // Action Bar Title
                        getActivity().setTitle(jsonArray.length() + " Paises");

                        showRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Log.e(TAG, "onResponse: " + error);
                    }
                });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.country_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = new SearchView(getActivity());
        searchView.setQueryHint("Buscar...");
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (covidCountryAdapter != null) {
                    covidCountryAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });

        searchItem.setActionView(searchView);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort_alpha:
                Toast.makeText(getContext(), "Ordenado Alfabeticamente", Toast.LENGTH_SHORT).show();
                covidPaises.clear();
                progressBar.setVisibility(View.VISIBLE);
                getDataFromServerSortAlphabet();
                return true;

            case R.id.action_sort_cases:
                Toast.makeText(getContext(), "Ordenado por Total de Casos", Toast.LENGTH_SHORT).show();
                covidPaises.clear();
                progressBar.setVisibility(View.VISIBLE);
                getDataFromServerSortTotalCases();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
