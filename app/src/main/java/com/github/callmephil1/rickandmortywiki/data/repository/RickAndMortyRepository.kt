package com.github.callmephil1.rickandmortywiki.data.repository

import com.github.callmephil1.rickandmortywiki.data.ServiceError
import com.github.callmephil1.rickandmortywiki.data.model.SearchEntry
import com.github.callmephil1.rickandmortywiki.data.model.SearchResponse
import com.github.callmephil1.rickandmortywiki.data.service.RickAndMortyService

interface RickAndMortyRepository {
    suspend fun fetchInfo(id: Int): Result<SearchEntry>
    suspend fun search(searchText: String): Result<SearchResponse>
}

class RickAndMortyRepositoryImpl(
    private val rickAndMortyService: RickAndMortyService
) : RickAndMortyRepository {

    override suspend fun fetchInfo(id: Int): Result<SearchEntry> {
        val response = rickAndMortyService.fetchInfo(id)

        return when {
            response.isSuccessful && response.body() != null -> { Result.success(response.body()!!) }
            response.body() == null -> { Result.failure(NullPointerException("Api returned an null body")) }
            else -> Result.failure(ServiceError(response.code(), response.message()))
        }
    }

    override suspend fun search(searchText: String): Result<SearchResponse> {
        val response = rickAndMortyService.search(searchText)

        return when {
            response.isSuccessful && response.body() != null -> { Result.success(response.body()!!) }
            response.body() == null -> { Result.failure(NullPointerException("Api returned an null body")) }
            else -> Result.failure(ServiceError(response.code(), response.message()))
        }
    }
}