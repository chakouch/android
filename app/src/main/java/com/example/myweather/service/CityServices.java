package com.example.myweather.service;

import com.example.myweather.beans.CityWeather;

import java.util.ArrayList;
import java.util.List;

public class CityServices implements ICityService {

    //Liste des services proposés sur les données
    private final List<CityWeather> cities = new ArrayList<>();

    @Override
    public List<CityWeather> getCity() {
        return cities;
    }

    @Override
    public void deleteByName(String city) {
        cities.remove(city);
    }

    @Override
    public void addCity(CityWeather city) {
        cities.add(city);
    }

}
