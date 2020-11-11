package com.example.myweather.beans;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CityWeather implements Serializable {

 @PrimaryKey(autoGenerate = true) public int id;
 @NonNull public  String cityName;


 public CityWeather(@NonNull String cityName)
 {
  id = 0;
  this.cityName = cityName;

 }
}
