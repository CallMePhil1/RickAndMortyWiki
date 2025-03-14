package com.github.callmephil1.rickandmortywiki.ui.compose.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.callmephil1.rickandmortywiki.data.repository.RickAndMortyRepository
import com.github.callmephil1.rickandmortywiki.ui.loader.LoaderManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val loaderManager: LoaderManager,
    private val rickAndMortyRepository: RickAndMortyRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    fun search(searchText: String) = viewModelScope.launch {

        loaderManager.showContentLoader(true, 0.7f)

        val result = rickAndMortyRepository.search(searchText)

        loaderManager.showContentLoader(false)

        when {
            result.isSuccess -> {
                _uiState.update {
                    it.copy(entries = result.getOrThrow().results)
                }
            }
        }
    }
}