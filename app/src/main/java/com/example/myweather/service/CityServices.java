package com.example.myweather.service;

import com.example.myweather.beans.CityWeather;

import java.util.ArrayList;
import java.util.List;

public class CityServices implements ICityService {

    private final List<CityWeather> cities = new ArrayList<>();

    @Override
    public List<CityWeather> getCity() {
        return cities;
    }

    @Override
    public void deleteCity(CityWeather city) {
    }

    @Override
    public void addCity(CityWeather city) {
        cities.add(city);
    }

}
