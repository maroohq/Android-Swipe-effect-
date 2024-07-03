package com.example.tabbed

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object api {

    val BASE_URL = "https://dataservice.accuweather.com"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IWeather::class.java) //interface in the previous slide
}
