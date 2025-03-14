package com.github.callmephil1.rickandmortywiki.ui.compose.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.callmephil1.rickandmortywiki.data.repository.RickAndMortyRepository
import com.github.callmephil1.rickandmortywiki.ui.loader.LoaderManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val loaderManager: LoaderManager,
    private val rickAndMortyRepository: RickAndMortyRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun fetchInfo(id: Int) = viewModelScope.launch {
        loaderManager.showContentLoader(true)

        val result = rickAndMortyRepository.fetchInfo(id)

        loaderManager.showContentLoader(false)

        when {
            result.isSuccess -> {
                _uiState.update {
                    it.copy(searchEntry = result.getOrThrow())
                }
            }
        }
    }
}