package com.example.myweather.OpenWeatherAPI;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitAPIClient {

    private static Retrofit retrofit = null;

     public static Retrofit getclient(OkHttpClient okHttpClient){

         if (retrofit == null){
             retrofit = new  Retrofit.Builder()
                     .baseUrl("https://jsonplaceholder.typicode.com")
                     .addConverterFactory(MoshiConverterFactory.create())
                     .client(okHttpClient)
                     .build();
         }
         return retrofit;
     }
}
