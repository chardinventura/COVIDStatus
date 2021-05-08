package com.example.covidstatus.service;

import com.example.covidstatus.model.Country;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class CountryService {
    private Retrofit retrofit = RetrofitClient.getInstance("https://disease.sh/");

    public void getByName(String countryName, Callback<Country> callback) {
       ICountryService iCountryService = retrofit.create(ICountryService.class);
       iCountryService.getByName(countryName).enqueue(callback);
    }
}
