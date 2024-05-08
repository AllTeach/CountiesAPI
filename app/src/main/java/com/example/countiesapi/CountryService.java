package com.example.countiesapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CountryService {
    private static final String API_URL = "https://restcountries.com/v2/all";

    public void getCountries(CountryListener listener) {
        new Thread(new CountryRunnable(listener)).start();
    }

    private static class CountryRunnable implements Runnable {
        private CountryListener listener;

        public CountryRunnable(CountryListener listener) {
            this.listener = listener;
        }

        @Override
        public void run() {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(API_URL)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String jsonData = response.body().string();

                Gson gson = new Gson();
                Type listType = new TypeToken<List<Map<String, Object>>>(){}.getType();
                List<Map<String, Object>> data = gson.fromJson(jsonData, listType);

                ArrayList<Country> countries = new ArrayList<>();
                for (Map<String, Object> countryData : data) {
                    String name = (String) countryData.get("name");
                    String officialName = (String) countryData.get("nativeName");
                    String language;
                    try {
                        language = ((Map<String, String>) ((List<?>) countryData.get("languages")).get(0)).get("name");
                    }catch (Exception e)
                    {
                        language = "Unknown";

                    }
                    String currency;
                    try {
                        currency = ((Map<String, String>) ((List<?>) countryData.get("currencies")).get(0)).get("name");

                    }
                    catch (Exception e)
                    {
                        currency = "Unknown";

                    }

                    ArrayList<String> borders = new ArrayList<>();
                    try {
                        borders = (ArrayList<String>) countryData.get("borders");

                        if(borders== null)
                        {
                            borders = new ArrayList<>();
                            borders.add("none");
                        }
                    }catch (Exception e)
                    {
                        borders.add("none");
                    }

                    countries.add(new Country(name, officialName, currency, language, borders));
                }

                if (listener != null) {
                    listener.onCountriesReceived(countries);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public interface CountryListener {
        void onCountriesReceived(ArrayList<Country> countries);
    }
}