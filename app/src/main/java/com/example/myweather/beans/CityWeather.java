package com.example.myweather.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class CityWeather implements Serializable {

 @PrimaryKey(autoGenerate = true) public int id;
 @NonNull public String cityName;

 public CityWeather(@NonNull String cityName)
 {
  id = 0;
  this.cityName = cityName;
 }


}
