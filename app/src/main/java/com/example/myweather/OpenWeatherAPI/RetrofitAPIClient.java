package com.example.myweather.OpenWeatherAPI;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitAPIClient {

    private static Retrofit retrofit = null;

     public static Retrofit getclient(){

         if (retrofit == null){
             retrofit = new  Retrofit.Builder()
                     .baseUrl("https://api.openweathermap.org/data/2.5/")
                     .addConverterFactory(MoshiConverterFactory.create())
                     .build();
         }
         return retrofit;
     }
}
