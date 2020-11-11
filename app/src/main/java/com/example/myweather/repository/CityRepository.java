package com.example.myweather.repository;

import com.example.myweather.City;
import com.example.myweather.service.CityServices;

import java.util.List;

public final class CityRepository {
    private static volatile CityRepository instance;
    private final CityServices cityservices = null;
    private String test = "Metz";

    public static CityRepository getInstance() {
        if (instance == null) {
            synchronized (CityRepository.class) {
                if (instance == null)
                 instance = new CityRepository();
            }
        }
        return instance;
    }

    public String getTest() {
        return test;
    }
    public List<City> getCity()
    {
        return cityservices.getCity();
    }

    public List<City> sortUsersByName()
    {
        return cityservices.sortUsersByName();
    }
}
