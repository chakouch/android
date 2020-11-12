package com.example.myweather;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static String getDate() {

        String lastUpdate;
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        lastUpdate = ("le " + dateFormat.format(date) + " à " +      timeFormat.format(date));
     return lastUpdate;
    }

    public static int getImageWeather(String code){

        //Méthode qui ne nous plait pas, nous voulons bien la réponse... :/

        Map<String, String> imageCode = new HashMap<>();
        String img = null;
        imageCode.put("0","01d");imageCode.put("1","01n");
        imageCode.put("2","02d");imageCode.put("3","02n");
        imageCode.put("4","03d");imageCode.put("5","03n");
        imageCode.put("6","04d");imageCode.put("7","04n");
        imageCode.put("8","09d");imageCode.put("9","09n");
        imageCode.put("10","10d");imageCode.put("11","10n");
        imageCode.put("12","11d");imageCode.put("13","11n");
        imageCode.put("14","13d");imageCode.put("15","13n");
        imageCode.put("16","50d");imageCode.put("17","50n");

        for (Map.Entry<String,String> entry : imageCode.entrySet()){
            if (entry.getValue().equals(code))
                img = entry.getKey();
        }

        int[] imageList = new int[]{R.drawable.img_01d,R.drawable.img_01n,R.drawable.img_02d,R.drawable.img_02n,
                R.drawable.img_03d,R.drawable.img_03n,R.drawable.img_04d,R.drawable.img_04n,R.drawable.img_09d,
                R.drawable.img_09n, R.drawable.img_10d,R.drawable.img_10n,R.drawable.img_11d,R.drawable.img_11n,
                R.drawable.img_13d, R.drawable.img_13n, R.drawable.img_50d, R.drawable.img_50n};

       return imageList[Integer.parseInt(img)];
    }
}
