package com.example.myweather;

import android.content.Context;
import android.util.Log;

import com.example.myweather.OpenWeatherAPI.CustomInterceptor;
import com.example.myweather.OpenWeatherAPI.IService;
import com.example.myweather.OpenWeatherAPI.RetrofitAPIClient;
import com.example.myweather.OpenWeatherAPI.WeatherResponse;
import com.example.myweather.beans.CityWeather;
import com.example.myweather.repository.CityRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Utils {

    public static String getDate() {

        String lastUpdate;
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        lastUpdate = ( dateFormat.format(date) + " " + timeFormat.format(date));

        return lastUpdate;
    }

    public static int getImageWeather(String code){

        //Méthode qui ne nous plait pas, nous voulons bien la réponse... :
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
                img = entry.getKey(); }

        int[] imageList = new int[]{R.drawable.img_01d,R.drawable.img_01n,R.drawable.img_02d,R.drawable.img_02n,
                R.drawable.img_03d,R.drawable.img_03n,R.drawable.img_04d,R.drawable.img_04n,R.drawable.img_09d,
                R.drawable.img_09n, R.drawable.img_10d,R.drawable.img_10n,R.drawable.img_11d,R.drawable.img_11n,
                R.drawable.img_13d, R.drawable.img_13n, R.drawable.img_50d, R.drawable.img_50n};

        return imageList[Integer.parseInt(img)];
    }

    public static void updateData(String name, Context context) {

        final String apiID = "455c70c40063b41bf3cf235af1d60c8d";
        final String units = "metric";
        final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient okHttp = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).addInterceptor(new CustomInterceptor()).build();

        IService iService = RetrofitAPIClient.getclient(okHttp).create(IService.class);
        Call<WeatherResponse> call = iService.getWeatherData(name, apiID, units);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                try {
                    String temps = response.body().main.temp;
                    String feels_like = response.body().main.feels_like;
                    String tempMin = response.body().main.tempMin;
                    String tempMax = response.body().main.tempMax;
                    String icon = response.body().weather.get(0).icon;
                    String resquestTime = Utils.getDate();
                    CityRepository.getInstance(context).updateCity(new CityWeather(name, temps, feels_like, tempMin, tempMax,
                            icon, resquestTime));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d("JPP", "jvais me pendre");
            }
        });
    }
}