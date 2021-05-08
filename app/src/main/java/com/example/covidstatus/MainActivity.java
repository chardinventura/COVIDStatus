package com.example.covidstatus;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.covidstatus.model.Country;
import com.example.covidstatus.service.CountryService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CountryService countryService;
    private EditText edSearch;
    private Button bSearch;

    private TextView tvTodayLabel;
    private LinearLayout llToday;
    private TextView tvTodayCases;
    private TextView tvTodayRecovered;
    private TextView tvTodayDeaths;

    private TextView tvTotalLabel;
    private LinearLayout llTotal;
    private TextView tvCases;
    private TextView tvRecovered;
    private TextView tvDeaths;

    private TextView tvActive;
    private TextView tvCritical;

    private LinearLayout llInfoCountry;
    private TextView tvCountry;
    private TextView tvContinent;
    private ImageView ivFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryService = new CountryService();
        edSearch = findViewById(R.id.etSearch);
        bSearch = findViewById(R.id.bSearch);

        tvTodayLabel = findViewById(R.id.tvTodayLabel);
        llToday = findViewById(R.id.llToday);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTodayRecovered = findViewById(R.id.tvTodayRecovered);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);

        tvTotalLabel = findViewById(R.id.tvTotalLabel);
        llTotal = findViewById(R.id.llTotal);
        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvDeaths = findViewById(R.id.tvDeaths);

        tvActive = findViewById(R.id.tvActive);
        tvCritical = findViewById(R.id.tvCritical);

        llInfoCountry = findViewById(R.id.llInfoCounty);
        tvCountry = findViewById(R.id.tvCountry);
        tvContinent = findViewById(R.id.tvContinent);
        ivFlag = findViewById(R.id.ivflag);

        bSearch.setOnClickListener(view -> search(edSearch.getText().toString()));
    }

    public void search(String countryName) {
        if(countryName.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese un pais", Toast.LENGTH_SHORT).show();
            return;
        }
        countryService.getByName(countryName, new Callback<Country>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {
                if (response.isSuccessful()) {
                    setVisibleViews(View.VISIBLE);
                    fillViews(response.body());
                    return;
                }
                Toast.makeText(MainActivity.this, "Pais no encontrado o no tiene casos", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void fillViews(Country country) {
        tvTodayCases.setText("Casos: " + country.getTodayCases());
        tvTodayRecovered.setText("Recuperados: " + country.getTodayRecovered());
        tvTodayDeaths.setText("Muertos: " + country.getTodayDeaths());
        tvCases.setText("Casos: " + country.getCases());
        tvRecovered.setText("Recuperados: " + country.getRecovered());
        tvDeaths.setText("Muertes: " + country.getDeaths());
        tvActive.setText("Activos: " + country.getActive());
        tvCritical.setText("Criticos: " + country.getCritical());
        tvCountry.setText("Pais: " + country.getCountry());
        tvContinent.setText("Continente: " + country.getContinent());
        Picasso.get()
                .load(country.getCountryInfo().getFlag())
                .into(ivFlag);
    }

    private void setVisibleViews(int visible) {
        tvTodayLabel.setVisibility(visible);
        llToday.setVisibility(visible);
        tvTotalLabel.setVisibility(visible);
        llTotal.setVisibility(visible);
        llInfoCountry.setVisibility(visible);
        ivFlag.setVisibility(visible);
    }
}