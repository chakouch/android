package com.example.myweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.myweather.adapter.CityAdapter;
import com.example.myweather.repository.CityRepository;
import com.example.myweather.service.CityServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity   {

    private RecyclerView cityWeather;
    private FloatingActionButton addWeather;
    private TextView information;
    private City city;
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
        cityWeather.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        cityServices = new CityServices();
        if (cityServices.getCity() == null)
            cityWeather.setVisibility(View.GONE);
        else
            cityWeather.setVisibility(View.VISIBLE);

        addWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (cityServices.getCity() != null)
        initList();
    }

    private void initList()
    {
        final List<City> cityList = currentOrderState == OrderState.NotOrder ? CityRepository.getInstance().getCity() : CityRepository.getInstance().sortUsersByName();
        final CityAdapter cityAdapter = new CityAdapter(cityList);
        cityWeather.setAdapter(cityAdapter);
    }
}