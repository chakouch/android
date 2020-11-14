package com.example.myweather.OpenWeatherAPI;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse  {

    //Réponse du JSON lors de l'appelle de L'API

    @SerializedName("coord")
    @Expose
    public Coord coord;

    @SerializedName("weather")
    @Expose
    public List<Weather> weather = null;

    @SerializedName("main")
    @Expose
    public Main main;

    @SerializedName("dt")
    @Expose
    public String dt;


    public class Coord {

    }
    public class Main {

        @SerializedName("temp")
        @Expose
        public String temp;
        @SerializedName("feels_like")
        @Expose
        public String feels_like;
        @SerializedName("temp_min")
        @Expose
        public String tempMin;
        @SerializedName("temp_max")
        @Expose
        public String tempMax;

    }

    public class Weather {
        @SerializedName("icon")
        @Expose
        public String icon;

    }


}
