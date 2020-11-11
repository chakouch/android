package com.example.myweather.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweather.R;
import com.example.myweather.beans.CityWeather;

import java.util.List;

public final class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private final List<CityWeather> cityList;

    public CityAdapter(List<CityWeather> cityList) {
        this.cityList = cityList;
    }

    public final class CityViewHolder extends RecyclerView.ViewHolder {

        private final TextView displayCity;
        private final ImageView displayStatus;


        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            displayCity = itemView.findViewById(R.id.city);
            displayStatus = itemView.findViewById(R.id.status);
        }

        public void updateViewHolder(CityWeather city) {
            displayCity.setText(city.cityName);
            itemView.setOnClickListener(new View.OnClickListener()   {
                @Override
                public void onClick(View v)     {

                }});
        }
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_city, parent,false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        holder.updateViewHolder(cityList.get(position));
    }

    @Override
   public int getItemCount() {
        return cityList.size();
    }
}