package com.example.myweather.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myweather.beans.CityWeather;
import com.example.myweather.service.ICityService;

import java.util.List;

@Dao
public interface CityWeatherDao extends ICityService {

    @Override
    @Query("SELECT * FROM CityWeather")
    List<CityWeather> getCity();

    @Override
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCity(CityWeather city);


    @Query("DELETE FROM cityweather WHERE cityName = :cityName") void deleteByName(String cityName);

}
