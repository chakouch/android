package com.example.myweather.OpenWeatherAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IService {

    @GET("https://api.openweathermap.org/data/2.5/weather?")
    Call<Weather> getWeatherData(@Query("q") String city, @Query("appid") String apiID, @Query("units") String units);

}
