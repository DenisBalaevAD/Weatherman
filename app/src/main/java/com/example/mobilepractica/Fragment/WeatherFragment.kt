@file:Suppress("DEPRECATION")

package com.example.mobilepractica.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mobilepractica.*
import com.example.mobilepractica.Common.Common
import com.example.mobilepractica.Interface.WeatherAPIInterface
import com.example.mobilepractica.Model.APIExample.MainExampleWeather
import kotlinx.android.synthetic.main.fragment_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("DEPRECATION", "PLUGIN_WARNING")
class WeatherFragment : Fragment() {

    lateinit var api: WeatherAPIInterface

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataAcquisition()
    }

    fun dataAcquisition(){
        relativeLayout.visibility=View.GONE
        val shared= context?.let { SharedPreference(it).open("Elects") }
        if (context?.let { SharedPreference(it).shared.contains("Elects") }!! && shared!="") {
            title.text = shared
            Retrofit()
        }
        else
        {
            context?.let { Dialog().dialogInformation(it,"Первый вход","Выбирите город. Для этого нажмите на значек лупы, и там нажмите на город ,который вам нужен.") }
            progressBar.visibility=View.GONE
            SharedPreference(requireContext()).saveInt(1)
        }
    }

    fun animation(){

        LinerCardOne.startAnimation(AnimationUtils.loadAnimation(context,R.anim.translate_card))
        LinerCardTwo.startAnimation(AnimationUtils.loadAnimation(context,R.anim.translate_card))
        advice.startAnimation(AnimationUtils.loadAnimation(context,R.anim.alpha))
        adviceText.startAnimation(AnimationUtils.loadAnimation(context,R.anim.alpha))
        temperature.startAnimation(AnimationUtils.loadAnimation(context,R.anim.alpha))
        icon_temperature.startAnimation(AnimationUtils.loadAnimation(context,R.anim.alpha))
        title.startAnimation(AnimationUtils.loadAnimation(context,R.anim.translate_title))
        description.startAnimation(AnimationUtils.loadAnimation(context,R.anim.translate_description))

    }

    fun Retrofit(){

        api= Common.retrofitServices

        val call=api.getWeather(title.text.toString(), "metric", "ru", BuildConfig.OPEN_WEATHER_MAP_API_KEY)

        call.enqueue(object : Callback<MainExampleWeather> {

            override fun onFailure(call: Call<MainExampleWeather>, t: Throwable) {
                Toast.makeText(context,"JQ",Toast.LENGTH_LONG).show()
                Log.d("TAG",t.message.toString())
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<MainExampleWeather>, response: Response<MainExampleWeather>) {
                if (response.isSuccessful) {
                    oneDay(response.body()!!)
                }
                else{
                    Toast.makeText(context,response.code().toString(),Toast.LENGTH_LONG).show()
                }
            }
        })

    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    fun oneDay(myData:MainExampleWeather){
        val mainEx=myData.mainWeather
        val heat=mainEx?.temp?.toInt()
        temperature.text = heat.toString()
        description.text=myData.weather?.get(0)?.description.toString().capitalize()

        clouds.text=myData.clouds?.all?.toInt().toString()+" %"
        pressure.text=(mainEx?.pressure!! /1.33307087).toInt().toString()+" мм рт.ст"
        humidity.text=mainEx.humidity?.toInt().toString()+" %"
        wind.text= myData.wind?.speed?.toInt().toString()+"м/с"

        if ( heat!! > 15){
            advice.text="Идти купаться, или загорать."
        }
        else{
            advice.text="Одеться теплее."
        }

        animation()
        relativeLayout.visibility=View.VISIBLE
        progressBar.visibility=View.GONE
    }
}