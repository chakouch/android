package com.example.myweather;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myweather.beans.CityWeather;
import com.example.myweather.repository.CityRepository;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityWeatherInformation extends AppCompatActivity {

    public static final String CITY_NAME = "cityName";
    private String city;
    private TextView name;
    public TextView temp;
    private TextView feelTemp;
    private TextView min;
    private TextView max;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Initialisation de la vue
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
        //Menu "icone poubelle"
        getMenuInflater().inflate(R.menu.menu_add_city, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        //Boite de dialogue lors de la suppression
        if (item.getItemId() == R.id.trash)
        {
            new AlertDialog.Builder(this)
                    .setTitle("WARNINGS!")
                    .setMessage("Are you sure you want to delete this city?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        //message plus supression de la ville dans la base en fonction du nom
                        Toast.makeText(getApplicationContext(), "City called '"+ city +"' deleted", Toast.LENGTH_SHORT).show();
                        CityRepository.getInstance(CityWeatherInformation.this).deleteCity(new CityWeather(city,"","","",
                                "","",""));
                        //Retour sur l'activité parent
                        finish();
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.cancel())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void getWeatherData(String city) throws ParseException {

        //Récupération de l'heure de requête dans la base
        final List<CityWeather> cityList = CityRepository.getInstance(this).getCity();
        for (int i = 0; i < cityList.size(); i++) {
            if (cityList.get(i).cityName.equals(city)){
                String requestTime = cityList.get(i).requestTime;

                //Vérification de l'heure
                if (Utils.compareDate(requestTime) == 1){
                    Utils.updateData(cityList.get(i).cityName, CityWeatherInformation.this);
                }

                Map<String, String> weatherInformation = new HashMap<>();
                weatherInformation.put("temp",cityList.get(i).temp);
                weatherInformation.put("feelTemp",cityList.get(i).feelsLike);
                weatherInformation.put("min",cityList.get(i).tempMin);
                weatherInformation.put("max",cityList.get(i).tempMax);
                weatherInformation.put("icon",cityList.get(i).icon);

                refreshScreen((HashMap<String, String>) weatherInformation);
            }
        }
    }

    public void refreshScreen(HashMap<String ,String> weatherInformation){

        //Affichage des données
        temp.setText(weatherInformation.get("temp"));
        feelTemp.setText(weatherInformation.get("feelTemp"));
        min.setText(weatherInformation.get("min"));
        max.setText(weatherInformation.get("max"));
        String imagePAth = weatherInformation.get("icon");
        imageView.setImageResource(Utils.getImageWeather(imagePAth));
    }
}
