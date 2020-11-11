package com.example.myweather;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myweather.beans.CityWeather;
import com.example.myweather.repository.CityRepository;

public class AddCity extends AppCompatActivity implements View.OnClickListener {

    private EditText cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_city);
        cityName = findViewById(R.id.city);
        findViewById(R.id.save).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String city = String.valueOf(cityName.getText());
        if (TextUtils.isEmpty(city)){
            Toast.makeText(getApplicationContext(), "Invalid city name", Toast.LENGTH_LONG).show();
        }
        else {
            CityRepository.getInstance(this).addCity(new CityWeather(city));
            finish();
        }
    }
}
