package com.example.myweather;

import androidx.annotation.NonNull;

import java.io.Serializable;

final public class City implements Serializable {

    @NonNull
    public final String name;

    public City(@NonNull String name) {

        this.name = name;
    }
}
