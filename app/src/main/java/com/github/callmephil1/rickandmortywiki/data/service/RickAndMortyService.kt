package com.github.callmephil1.rickandmortywiki.data.service

import com.github.callmephil1.rickandmortywiki.data.model.SearchEntry
import com.github.callmephil1.rickandmortywiki.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {
    @GET("api/character/{id}")
    suspend fun fetchInfo(
        @Path("id") id: Int
    ): Response<SearchEntry>

    @GET("api/character")
    suspend fun search(
        @Query("name") name: String,
        @Query("page") page: Int? = null
    ): Response<SearchResponse>
}