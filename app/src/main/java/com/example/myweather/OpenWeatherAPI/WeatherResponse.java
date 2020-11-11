package com.example.myweather.OpenWeatherAPI;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather  {

    @SerializedName("main")
    private Weather main;

    @SerializedName("coord")
    private Weather coord;

    @SerializedName("weather")
    public List<Weather> weather = null;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    @SerializedName("icon")
    String icon;

    @SerializedName("temp")
    String temp;

    @SerializedName("feels_like")
    String feels_like;

    @SerializedName("temp_min")
    String temp_min;

    @SerializedName("temp_max")
    String temp_max;

    public Weather getMain() {
        return main;
    }

    public void setMain(Weather main) {
        this.main = main;
    }

    public Weather getCoord() {
        return coord;
    }

    public void setCoord(Weather coord) {
        this.coord = coord;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }


    public String getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(String feels_like) {
        this.feels_like = feels_like;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
