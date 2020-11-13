package com.example.myweather;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myweather.OpenWeatherAPI.CustomInterceptor;
import com.example.myweather.OpenWeatherAPI.IService;
import com.example.myweather.OpenWeatherAPI.RetrofitAPIClient;
import com.example.myweather.OpenWeatherAPI.WeatherResponse;
import com.example.myweather.beans.CityWeather;
import com.example.myweather.repository.CityRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityWeatherInformation extends AppCompatActivity {

    public static final String CITY_NAME = "cityName";
    private String city;
    private TextView name;
    private TextView temp;
    private TextView feelTemp;
    private TextView min;
    private TextView max;
    private ImageView imageView;
    HashMap getWeatherData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        name = findViewById(R.id.name);
        temp=findViewById(R.id.temp);
        feelTemp=findViewById(R.id.feelTemp);
        min = findViewById(R.id.min);
        max = findViewById(R.id.max);
        imageView = (ImageView) findViewById(R.id.image);

        final CityWeather cityWeather = (CityWeather) getIntent().getSerializableExtra(CityWeatherInformation.CITY_NAME);
        city =cityWeather.cityName;
        name.setText(city);
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
                    .setPositiveButton("Yes", (dialog, which) -> {
                        Toast.makeText(getApplicationContext(), "City called '"+ city +"' deleted", Toast.LENGTH_LONG).show();
                        CityRepository.getInstance(CityWeatherInformation.this).deleteCity(new CityWeather(city));
                        finish();
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.cancel())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void getWeatherData(String name){


        String apiID="455c70c40063b41bf3cf235af1d60c8d";
        String units="metric";
        final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient okHttp = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor) .addInterceptor(new CustomInterceptor()).build();

        IService iService = RetrofitAPIClient.getclient(okHttp).create(IService.class);
        Call<WeatherResponse> call = iService.getWeatherData(name,apiID,units);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                try {

                    temp.setText(response.body().main.temp);
                    feelTemp.setText(response.body().main.feels_like);
                    min.setText(response.body().main.tempMin);
                    max.setText(response.body().main.tempMax);
                    String imagePAth = response.body().weather.get(0).icon;
                    imageView.setImageResource(Utils.getImageWeather(imagePAth));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d("JPP","jvais me pendre");
            }
        });
    }
}
