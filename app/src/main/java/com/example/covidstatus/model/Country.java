package com.example.covidstatus.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Country {
    private String country;
    private CountryInfo countryInfo;
    private Long cases;
    private Long todayCases;
    private Long deaths;
    private Long todayDeaths;
    private Long recovered;
    private Long todayRecovered;
    private Long active;
    private Long critical;
    private String continent;
}
