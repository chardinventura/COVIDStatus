package com.example.covidstatus.service;

import com.example.covidstatus.model.Country;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ICountryService {
    @GET("v3/covid-19/countries/{country}")
    Call<Country> getByName(@Path("country") String country);
}
