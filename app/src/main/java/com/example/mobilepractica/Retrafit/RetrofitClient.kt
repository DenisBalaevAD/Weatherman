package com.example.mobilepractica.Retrafit

import com.example.mobilepractica.BuildConfig
import com.example.mobilepractica.ClassConstant
import com.example.mobilepractica.Interface.WeatherAPIInterface
import com.example.mobilepractica.Model.APIExample.MainExampleWeather
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private var retrofit:  Retrofit?=null

    fun getClient(baseUrl:String):Retrofit{

        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit!!

    }
}