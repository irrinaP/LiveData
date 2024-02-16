package com.bignerdranch.android.livedata.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.livedata.RetrofitService
import com.bignerdranch.android.livedata.WeatherData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class WeatherViewModel : ViewModel() {
    val weatherData = MutableLiveData<WeatherData>()

    fun retrofitAPI(city : String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val weatherRetrofit = RetrofitService.create().getWeatherForecast(city, "e95a93c258eb63a193e07e680c12daab", "metric", "ru")
                withContext(Dispatchers.Main) {
                    weatherData.postValue(weatherRetrofit)
                }
            } catch (ex : Exception) { }
        }
    }
}