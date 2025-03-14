package com.github.callmephil1.rickandmortywiki.ui.loader

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoaderUiState(
    val loaderAlpha: Float = 1f,
    val showContentLoader: Boolean = false,
    val showFullscreenLoader: Boolean = false,
)

interface LoaderManager {
    val uiState: StateFlow<LoaderUiState>
    fun showContentLoader(show: Boolean, alpha: Float = 1f)
    fun showFullscreenLoader(show: Boolean, alpha: Float = 1f)
}

class LoaderManagerImpl : LoaderManager {
    private val _uiState = MutableStateFlow(LoaderUiState())
    override val uiState = _uiState.asStateFlow()

    override fun showContentLoader(show: Boolean, alpha: Float) {
        _uiState.update { it.copy(showContentLoader = show) }
    }

    override fun showFullscreenLoader(show: Boolean, alpha: Float) {
        _uiState.update { it.copy(showFullscreenLoader = show, loaderAlpha = alpha) }
    }
}