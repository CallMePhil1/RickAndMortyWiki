package com.github.callmephil1.rickandmortywiki.ui.compose.search

import com.github.callmephil1.rickandmortywiki.data.model.SearchEntry

data class SearchUiState(
    val entries: List<SearchEntry> = listOf()
)