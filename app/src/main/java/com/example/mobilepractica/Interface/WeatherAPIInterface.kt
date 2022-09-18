package com.example.mobilepractica.Interface

import com.example.mobilepractica.Model.APIExample.MainExampleWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIInterface{

    @GET("weather")
    fun getWeather(
        @Query("q") city: String,
        @Query("units") units:String,
        @Query("lang") lang:String,
        @Query("appid") api_key: String
    ): Call<MainExampleWeather>
}


