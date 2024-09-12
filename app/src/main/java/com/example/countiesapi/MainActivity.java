package com.example.countiesapi;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvCountries;
    private CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // just changing for github
        lvCountries = findViewById(R.id.lvCountries);
        adapter = new CountryAdapter(this, new ArrayList<>());
        lvCountries.setAdapter(adapter);

        loadCountries();
    }

    private void loadCountries() {
        CountryService countryService = new CountryService(this);
        countryService.getCountries(new CountryService.CountryListener() {
            @Override
            public void onCountriesReceived(ArrayList<Country> countries) {
                if (countries != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.clear();
                            adapter.addAll(countries);
                        }
                    });
                }
            }
        });
    }
}