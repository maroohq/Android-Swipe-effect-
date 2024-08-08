package com.example.tabbed



import com.example.tabbed.ForeCast.ForeCast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


const val apikey: String = "Your api key here"

interface IWeather {

    @GET("/forecasts/v1/daily/5day/305605?apikey=${apikey}")
    suspend fun getFiveDayTemperature() : Response<ForeCast>

}

