package com.example.wheatherapp.presentation.activities.cities.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wheatherapp.R
import com.example.wheatherapp.databinding.ActivityCitiesBinding
import com.example.wheatherapp.presentation.activities.cities.model.City
import com.example.wheatherapp.presentation.activities.weather.view.ARG_CITY_ID
import com.example.wheatherapp.presentation.activities.weather.view.WeatherActivity
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class CitiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCitiesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gson = GsonBuilder().create()
        val listOfCityObject = object : TypeToken<List<City>>() {}.type
        val cities = gson.fromJson<List<City>>(
            resources.openRawResource(R.raw.cities).reader(),
            listOfCityObject
        )

        binding.citiesList.adapter = CitiesListAdapter(cities) { cityId ->
            val intent = Intent(applicationContext, WeatherActivity::class.java)
            intent.putExtra(ARG_CITY_ID, cityId)
            startActivity(intent)
        }
    }
}