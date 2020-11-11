package com.example.myweather;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myweather.OpenWeatherAPI.CustomInterceptor;
import com.example.myweather.OpenWeatherAPI.IService;
import com.example.myweather.OpenWeatherAPI.RetrofitAPIClient;
import com.example.myweather.OpenWeatherAPI.Weather;
import com.example.myweather.beans.CityWeather;
import com.example.myweather.repository.CityRepository;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityWeatherInformation extends AppCompatActivity {

    public static final String CITY_NAME = "cityName";
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        final CityWeather cityWeather = (CityWeather) getIntent().getSerializableExtra(CityWeatherInformation.CITY_NAME);
        city =cityWeather.cityName;
        getWeatherData(city);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_add_city, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == R.id.trash)
        {
            new AlertDialog.Builder(this)
                    .setTitle("WARNINGS!")
                    .setMessage("Are you sure you want to delete this city?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Weather for the city called '"+ city +"' deleted", Toast.LENGTH_LONG).show();
                            CityRepository.getInstance(CityWeatherInformation.this).deleteCity(new CityWeather(city));
                            finish();
                        }
                    })
                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }



    public void getWeatherData(String name){

        String apiID="455c70c40063b41bf3cf235af1d60c8d";
        String units="455c70c40063b41bf3cf235af1d60c8d";
        final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient okHttp = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor) .addInterceptor(new CustomInterceptor()).build();
        IService iService = RetrofitAPIClient.getclient(okHttp).create(IService.class);
        Call<Weather> call = iService.getWeatherData(name,apiID,units);

        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                final Weather weather = response.body();
                System.out.println(weather.getTemp());
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });
    }
    }
