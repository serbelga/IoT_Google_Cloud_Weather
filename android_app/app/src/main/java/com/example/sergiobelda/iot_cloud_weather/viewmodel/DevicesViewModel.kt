package com.example.sergiobelda.iot_cloud_weather.viewmodel

import androidx.lifecycle.ViewModel
import com.example.sergiobelda.iot_cloud_weather.repository.FirestoreWeatherRepository
import com.example.sergiobelda.iot_cloud_weather.repository.WeatherRepository

class DevicesViewModel : ViewModel() {
    private val repository: WeatherRepository = FirestoreWeatherRepository()

    fun getDevices() = repository.getDevices()
}