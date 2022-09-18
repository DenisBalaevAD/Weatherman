package com.example.mobilepractica.Common

import com.example.mobilepractica.ClassConstant
import com.example.mobilepractica.Interface.WeatherAPIInterface
import com.example.mobilepractica.Retrafit.RetrofitClient

object Common {
    val retrofitServices:WeatherAPIInterface
        get() = RetrofitClient().getClient(ClassConstant.BASE_URL).create(WeatherAPIInterface::class.java)
}