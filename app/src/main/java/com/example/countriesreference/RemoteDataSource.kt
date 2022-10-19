package com.example.countriesreference

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


class RemoteDataSource {
    interface RestCountriesService {
        @GET("/name/{name}")
        fun getCountryByName(@Path("name") cityName: String): List<Country>
    }

    var retrofit = Retrofit.Builder()
        .baseUrl("https://restcountries.com/v3.1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var restCountriesService = retrofit.create(RestCountriesService::class.java)
}