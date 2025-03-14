package com.github.callmephil1.rickandmortywiki.di

import com.github.callmephil1.rickandmortywiki.data.service.RickAndMortyService
import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        val gson = GsonBuilder()
            .setDateFormat("YYYY-MM-dd'T'HH:mm:ss")
            .create()
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


    single<RickAndMortyService> {
        val retrofit = get<Retrofit>()

        retrofit.create(RickAndMortyService::class.java)
    }
}