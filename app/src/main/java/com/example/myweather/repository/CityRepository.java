package com.example.myweather.repository;

import android.content.Context;

import androidx.room.Room;

import com.example.myweather.beans.CityWeather;
import com.example.myweather.datebase.CityWeatherDatabases;
import com.example.myweather.service.CityServices;

import java.util.List;

public final class CityRepository {
    private static volatile CityRepository instance;
    private final CityServices cityservices = null;
    private CityWeatherDatabases cityWeatherDatabases;


    public static CityRepository getInstance(Context context) {
        if (instance == null) {
            synchronized (CityRepository.class) {
                if (instance == null)
                 instance = new CityRepository(context);
            }
        }
        return instance;
    }

    private CityRepository(Context context){
        cityWeatherDatabases = Room.databaseBuilder(context, CityWeatherDatabases.class,"city-database").allowMainThreadQueries().build();
    }

    public List<CityWeather> getCity()
    {
        return cityWeatherDatabases.cityWeatherDao().getCity();
    }

    public void addCity(CityWeather city)
    {
        cityWeatherDatabases.cityWeatherDao().addCity(city);
    }

    public void deleteCity(CityWeather city)
    {
        cityWeatherDatabases.cityWeatherDao().deleteByName(city.cityName);
    }
}
