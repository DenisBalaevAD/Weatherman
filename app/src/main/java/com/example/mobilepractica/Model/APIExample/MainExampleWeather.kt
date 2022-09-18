package com.example.mobilepractica.Model.APIExample

import com.example.mobilepractica.Model.APIMain.MainWeather
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MainExampleWeather {
    @SerializedName("main")
    @Expose
    val mainWeather:MainWeather.Main?=null

    @SerializedName("weather")
    @Expose
    val weather:List<MainWeather.Weather>?=null

    @SerializedName("clouds")
    @Expose
    val clouds:MainWeather.Clouds?=null

    @SerializedName("wind")
    @Expose
    val wind:MainWeather.Wind?=null
}