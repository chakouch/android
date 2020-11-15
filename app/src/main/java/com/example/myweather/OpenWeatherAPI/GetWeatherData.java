package com.example.myweather.OpenWeatherAPI;

import android.content.Context;
import android.util.Log;

import com.example.myweather.AddCity;
import com.example.myweather.CityWeatherInformation;
import com.example.myweather.Utils;
import com.example.myweather.beans.CityWeather;
import com.example.myweather.repository.CityRepository;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetWeatherData {

    final String apiID = "455c70c40063b41bf3cf235af1d60c8d";
    final String units = "metric";
    final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    public enum Status {ADD,UPDATE};

    public GetWeatherData(String name, Context context, Status status ) {

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient okHttp = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).addInterceptor(new CustomInterceptor()).build();

        IService iService = RetrofitAPIClient.getclient(okHttp).create(IService.class);
        Call<WeatherResponse> call = iService.getWeatherData(name, apiID, units);

        //CallBack donc --> dernière opération
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

                    //Mise à jour de la base
                    CityRepository.getInstance(context).updateCity(new CityWeather(name, temps, feels_like, tempMin, tempMax,
                            icon, resquestTime));

                    Map<String, String> weatherInformation = new HashMap<String, String >();
                    weatherInformation.put("temp",temps);
                    weatherInformation.put("feelTemp",feels_like);
                    weatherInformation.put("min",tempMin);
                    weatherInformation.put("max",tempMax);
                    weatherInformation.put("icon",icon);

                    //Mise à jour de la vue (CityWeatherInformation)
                    if (status == Status.UPDATE)
                        updateData(weatherInformation,context);
                    else
                        addData(weatherInformation,context);

                } catch (Exception e) {
                    e.printStackTrace();
                    AddCity addCity = (AddCity) context;
                    addCity.failToAddCity();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d("JPP", "jvais me pendre");
            }
        });
    }

    public void updateData(Map<String,String>weatherInformation,Context context){
        CityWeatherInformation cityWeatherInformation = (CityWeatherInformation) context;
        cityWeatherInformation.refreshScreen((HashMap<String, String>) weatherInformation);
    }
    public void addData(Map<String,String>weatherInformation,Context context){
        AddCity addCity = (AddCity) context;
        addCity.addCity((HashMap<String, String>)weatherInformation);
    }
}
