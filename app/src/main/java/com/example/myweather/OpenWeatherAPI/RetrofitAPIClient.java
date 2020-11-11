package com.example.myweather.OpenWeatherAPI;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitAPIClient {

    private static Retrofit retrofit = null;

     public static Retrofit getclient(OkHttpClient okHttpClient){

         if (retrofit == null){
             retrofit = new  Retrofit.Builder()
                     .baseUrl("https://jsonplaceholder.typicode.com")
                     .addConverterFactory(GsonConverterFactory.create())
                     .client(okHttpClient)
                     .build();
         }
         return retrofit;
     }
}
