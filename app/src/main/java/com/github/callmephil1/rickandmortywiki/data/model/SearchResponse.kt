package com.github.callmephil1.rickandmortywiki.data.model

import java.time.LocalDateTime
import java.util.Date

data class SearchEntry(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: SearchOrigin,
    val location: SearchLocation,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: Date
)

data class SearchInfo(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
)

data class SearchLocation(
    val name: String,
    val url: String
)

data class SearchOrigin(
    val name: String,
    val url: String
)

data class SearchResponse(
    val info: SearchInfo,
    val results: List<SearchEntry>
)