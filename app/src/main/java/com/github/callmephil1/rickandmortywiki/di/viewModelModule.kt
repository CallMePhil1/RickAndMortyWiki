package com.github.callmephil1.rickandmortywiki.di

import com.github.callmephil1.rickandmortywiki.ui.compose.details.DetailsViewModel
import com.github.callmephil1.rickandmortywiki.ui.compose.search.SearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::DetailsViewModel)
    viewModelOf(::SearchViewModel)
}