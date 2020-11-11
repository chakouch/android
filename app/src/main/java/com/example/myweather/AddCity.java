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
                            // Continue with delete operation
                            Toast.makeText(getApplicationContext(), "Weather for the city called Paris deleted", Toast.LENGTH_LONG).show();
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


    @Override
    public void onClick(View view) {

        String city = String.valueOf(cityName.getText());
        if (TextUtils.isEmpty(city)){
            Toast.makeText(getApplicationContext(), "Invalid city name", Toast.LENGTH_LONG).show();
        }
        else {
            CityRepository.getInstance(this).addCity(new CityWeather(city));
        }
    }
}
