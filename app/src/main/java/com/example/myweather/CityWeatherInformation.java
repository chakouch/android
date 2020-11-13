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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    private String nameString;
    private String tempString;
    private String feelTempString;
    private String minString;
    private String maxString;
    private String imageViewString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        final CityWeather cityWeather = (CityWeather) getIntent().getSerializableExtra(CityWeatherInformation.CITY_NAME);

        city =cityWeather.cityName;
        name = findViewById(R.id.name);
        temp=findViewById(R.id.temp);
        feelTemp=findViewById(R.id.feelTemp);
        min = findViewById(R.id.min);
        max = findViewById(R.id.max);
        imageView = findViewById(R.id.image);
        name.setText(city);

        try {
            getWeatherData(city);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
                        CityRepository.getInstance(CityWeatherInformation.this).deleteCity(new CityWeather(city,"","","",
                                "","",""));
                        finish();
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.cancel())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void getWeatherData(String city) throws ParseException {

        final List<CityWeather> cityList = CityRepository.getInstance(this).getCity();
        for (int i = 0; i < cityList.size(); i++) {
            if (cityList.get(i).cityName.equals(city)){
                String requestTime = cityList.get(i).requestTime;

                if (Utils.compareDate(requestTime) == 1){
                    Utils.updateData(cityList.get(i).cityName, this);
                    getWeatherData(cityList.get(i).cityName); }

                temp.setText(cityList.get(i).temp);
                feelTemp.setText(cityList.get(i).feelsLike);
                min.setText(cityList.get(i).tempMin);
                max.setText(cityList.get(i).tempMin);
                String imagePAth = cityList.get(i).icon;
                imageView.setImageResource(Utils.getImageWeather(imagePAth));
            }
        }
    }



}
