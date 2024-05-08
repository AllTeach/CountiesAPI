package com.example.countiesapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CountryAdapter extends ArrayAdapter<Country> {
    public CountryAdapter(Context context, ArrayList<Country> countries) {
        super(context, 0, countries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Country country = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_country, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvOfficialName = convertView.findViewById(R.id.tvOfficialName);
        TextView tvCurrency = convertView.findViewById(R.id.tvCurrency);
        TextView tvLanguage = convertView.findViewById(R.id.tvLanguage);

        TextView tvNeighbours = convertView.findViewById(R.id.tvNeighbours);
        tvName.setText(country.getName());
        tvOfficialName.setText(country.getOfficialName());
        tvCurrency.setText(country.getCurrency());
        tvLanguage.setText(country.getLanguage());
        tvNeighbours.setText(country.getNeighbours().toString());
        return convertView;
    }
}