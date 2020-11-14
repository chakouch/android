package com.example.myweather.datebase;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.myweather.beans.CityWeather;
import com.example.myweather.dao.CityWeatherDao;
//Définition de la base de données en prennant CityWeather à l'aide Room
@Database(entities = { CityWeather.class }, version = 1)
public abstract class CityWeatherDatabases
        extends RoomDatabase
{
    public abstract CityWeatherDao cityWeatherDao();
}