package com.example.myweather.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myweather.beans.CityWeather;
import com.example.myweather.service.ICityService;

import java.util.List;

@Dao
public interface CityWeatherDao extends ICityService {

    //Requêtes, interface (contrat)

    @Override
    @Query("SELECT * FROM CityWeather")
    List<CityWeather> getCity();

    @Override
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCity(CityWeather city);

    @Query("DELETE FROM cityweather WHERE cityName = :cityName") void deleteByName (String cityName);

    @Query("UPDATE cityweather SET `temp` = :temp, `feelsLike`= :feel_likes,`tempMin` = :tempMin, `tempMax`= :tempMax," +
            "`icon` = :icon, `requestTime`= :requestTime WHERE cityName = :cityName")
    void updateCity (String cityName,String temp,String feel_likes,String tempMin,String tempMax, String icon, String requestTime);

}
