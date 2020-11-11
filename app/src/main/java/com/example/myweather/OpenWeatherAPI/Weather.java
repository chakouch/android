package com.example.myweather.OpenWeatherAPI;

import com.squareup.moshi.Json;

import java.io.Serializable;

public class Weather implements Serializable {

    @Json(name ="temp")
    public String temp;

    @Json(name ="feels_like")
    public String feels_like;

    @Json(name ="temp_min")
    public String temp_min;

    @Json(name ="temp_max")
    public String temp_max;

    public String icon;

    public Weather(String temp, String feels_like, String temp_min, String temp_max) {
        this.temp = temp;
        this.feels_like = feels_like;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public String getTemp() {
        return temp;
    }

    public String getFeels_like() {
        return feels_like;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }
}
