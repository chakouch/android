package com.example.myweather.service;

import com.example.myweather.beans.CityWeather;

import java.util.List;

public interface ICityService {

    /**
     * Get all users
     *
     * @return {@link List}
     */
    List<CityWeather> getCity();

    /**
     * Deletes an user
     *
     * @param city
     */
    void deleteCity(CityWeather city);

    /**
     * Add an user
     *
     * @param city
     */
    void addCity(CityWeather city);

}
