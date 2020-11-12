package com.example.myweather;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

                    Log.d("WOAL",response.body().dt);

                    String imagePAth = response.body().weather.get(0).icon;
                    getImageWeather(imagePAth);
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
            public void getImageWeather(String code){

                Map<String, String> imageCode = new HashMap<>();

                String img = null;
                imageCode.put("0","01d");imageCode.put("1","01n");
                imageCode.put("2","02d");imageCode.put("3","02n");
                imageCode.put("4","03d");imageCode.put("5","03n");
                imageCode.put("6","04d");imageCode.put("7","04n");
                imageCode.put("8","09d");imageCode.put("9","09n");
                imageCode.put("10","10d");imageCode.put("11","10n");
                imageCode.put("12","11d");imageCode.put("13","11n");
                imageCode.put("14","13d");imageCode.put("15","13n");
                imageCode.put("16","50d");imageCode.put("17","50n");

                for (Map.Entry<String,String> entry : imageCode.entrySet()){
                    if (entry.getValue().equals(code))
                        img = entry.getKey();
                }

                int[] imageList = new int[]{R.drawable.img_01d,R.drawable.img_01n,R.drawable.img_02d,R.drawable.img_02n,
                        R.drawable.img_03d,R.drawable.img_03n,R.drawable.img_04d,R.drawable.img_04n,R.drawable.img_09d,
                        R.drawable.img_09n, R.drawable.img_10d,R.drawable.img_10n,R.drawable.img_11d,R.drawable.img_11n,
                        R.drawable.img_13d, R.drawable.img_13n, R.drawable.img_50d, R.drawable.img_50n};

                imageView.setImageResource(imageList[Integer.parseInt(img)]);
            }
    }
