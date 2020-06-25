
/*
 * @(#)class_CovidPaisAdaptador.java 1.1 25/06/20
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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.milifi.myappcovid19.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class class_CovidPaisAdaptador extends RecyclerView.Adapter<class_CovidPaisAdaptador.ViewHolder> implements Filterable {

    private List<class_CovidPais> covidPaises;
    private List<class_CovidPais> covidPaisesCompleto;

    private Context context;

    public class_CovidPaisAdaptador(List<class_CovidPais> covidPaises, Context context) {
        this.covidPaises = covidPaises;
        this.context = context;
        covidPaisesCompleto = new ArrayList<>(covidPaises);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_covid_pais, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        class_CovidPais classCovidPais = covidPaises.get(position);
        holder.tvTotalCasos.setText(Integer.toString(classCovidPais.getmCasos()));
        holder.tvPaisNombre.setText(classCovidPais.getmCovidPais());

        // Glide
        Glide.with(context)
                .load(classCovidPais.getmBanderas())
                .apply(new RequestOptions().override(240, 160))
                .into(holder.imgPaisBandera);
    }

    @Override
    public int getItemCount() {
        return covidPaises.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTotalCasos, tvPaisNombre;
        ImageView imgPaisBandera;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTotalCasos = itemView.findViewById(R.id.tvTotalCasos);
            tvPaisNombre = itemView.findViewById(R.id.tvPaisNombre);
            imgPaisBandera = itemView.findViewById(R.id.imgPaisBandera);
        }
    }

    @Override
    public Filter getFilter() {
        return covidPaisesFilter;
    }

    private Filter covidPaisesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<class_CovidPais> filteredClassCovidPais = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredClassCovidPais.addAll(covidPaisesCompleto);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (class_CovidPais itemClassCovidPais : covidPaisesCompleto) {
                    if (itemClassCovidPais.getmCovidPais().toLowerCase().contains(filterPattern)) {
                        filteredClassCovidPais.add(itemClassCovidPais);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredClassCovidPais;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            covidPaises.clear();
            covidPaises.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
