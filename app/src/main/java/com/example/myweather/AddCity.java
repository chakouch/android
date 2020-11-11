package com.example.myweather;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddCity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_city);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //We add the "menu_order" to the current activity
        getMenuInflater().inflate(R.menu.menu_add_city, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        //We handle the click on a menu item
        if (item.getItemId() == R.id.profile)
        {
            Intent profile = new Intent(this, AddCity.class);
            startActivity(profile);
        }


        return super.onOptionsItemSelected(item);
    }


}
