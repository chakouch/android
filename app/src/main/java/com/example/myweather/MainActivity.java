package com.example.myweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myweather.adapter.CityAdapter;
import com.example.myweather.beans.CityWeather;
import com.example.myweather.repository.CityRepository;
import com.example.myweather.service.CityServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity   {

    private RecyclerView cityWeather;
    private FloatingActionButton addWeather;
    private TextView information;
    private CityWeather city;
    private ImageView imageView;
    private CityServices cityServices;
    private enum OrderState {Order, NotOrder}
    private OrderState currentOrderState = OrderState.NotOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityWeather = findViewById(R.id.cityWeather);
        addWeather = findViewById(R.id.addWeather);
        information = findViewById(R.id.information);
        imageView = findViewById(R.id.imageView);
        cityWeather.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        cityServices = new CityServices();
        initList();
        displayList();

        addWeather.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddCity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        initList();
        displayList();
    }

    private void initList()
    {
        final List<CityWeather> cityList = CityRepository.getInstance(this).getCity();
        final CityAdapter cityAdapter = new CityAdapter(cityList);
        cityWeather.setAdapter(cityAdapter);
    }
    public void displayList(){

        if (CityRepository.getInstance(this).getCity().isEmpty() ){
            cityWeather.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            information.setVisibility(View.VISIBLE);}
        else{
            cityWeather.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            information.setVisibility(View.GONE);
        }

    }
}