package com.example.myweather.OpenWeatherAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IService {

    @GET("weather?appid=455c70c40063b41bf3cf235af1d60c8d&units=metric")
    Call<Weather> getWeatherData(@Query("q") String city);

}
