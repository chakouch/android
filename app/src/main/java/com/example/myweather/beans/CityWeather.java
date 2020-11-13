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
 @NonNull public String temp;
 @NonNull public String feelsLike;
 @NonNull public String tempMin;
 @NonNull public String tempMax;
 @NonNull public String icon;
 @NonNull public String requestTime;

 public CityWeather(@NonNull String cityName,String temp,String feelsLike,String tempMin,String tempMax,
                    String icon,String requestTime)
 {
  id = 0;
  this.cityName = cityName;
  this.temp = temp;
  this.feelsLike = feelsLike;
  this.tempMin = tempMin;
  this.tempMax = tempMax;
  this.icon = icon;
  this.requestTime = requestTime;
 }


}
