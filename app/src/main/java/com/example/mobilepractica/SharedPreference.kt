package com.example.mobilepractica

import android.app.Activity
import android.content.Context

class SharedPreference(context: Context) {

    private val PREFS_NAME = "Weather"
    val shared=context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)

    fun save(key:String,str:String){
        shared.edit().putString(key,str).apply()
    }

    fun saveInt(count:Int){
        shared.edit().putInt("start",count).apply().toString()
    }

    fun open(key:String):String{
        return shared.getString(key,"").toString()
    }

    fun openInt():Int{
        return shared.getInt("start",0).toString().toInt()
    }
}