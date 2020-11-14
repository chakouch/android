package com.example.myweather;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCity extends AppCompatActivity implements View.OnClickListener {

    private EditText cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Initialisation de la vue
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_city);
        cityName = findViewById(R.id.city);
        findViewById(R.id.save).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String city = String.valueOf(cityName.getText());
        if (TextUtils.isEmpty(city)){
            //Vérification entrée non vide
            Toast.makeText(getApplicationContext(), "Invalid city name", Toast.LENGTH_LONG).show();
        }
        else {
            //Ajout simple de la ville
            String cityBeautiful = city.substring(0, 1).toUpperCase() + city.substring(1);
            Toast.makeText(getApplicationContext(), "City called '"+ cityBeautiful +"' added", Toast.LENGTH_SHORT).show();
            CityRepository.getInstance(AddCity.this).addCity(new CityWeather(cityBeautiful,"","","","",
                    "",""));
            finish();
            Utils.updateData(cityBeautiful,this);
        }
    }


}
