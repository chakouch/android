package com.example.myweather.OpenWeatherAPI;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CustomInterceptor implements Interceptor {
    @Override

    //Intercepteur personalisé, je mentirai si je disais que j'ai compris à quoi ça sert
    public Response intercept(Chain chain) throws IOException {
        final Request request = chain.request().newBuilder().addHeader("Custom-Header", "XXXX-XXXX-XXXX").build();
        return chain.proceed(request);
    }
}
