package com.example.myweather.service;

import com.example.myweather.City;

import java.util.List;

public interface ICityService {

    /**
     * Get all users
     *
     * @return {@link List}
     */
    List<City> getCity();

    /**
     * Deletes an user
     *
     * @param city
     */
    void deleteCity(City city);

    /**
     * Add an user
     *
     * @param city
     */
    void addCity(City city);

    /**
     * Get all users sorted by name
     *
     * @return {@link List}
     */

    List<City> sortUsersByName();
}
