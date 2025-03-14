package com.github.callmephil1.rickandmortywiki.di

import com.github.callmephil1.rickandmortywiki.data.repository.RickAndMortyRepository
import com.github.callmephil1.rickandmortywiki.data.repository.RickAndMortyRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<RickAndMortyRepository> {
        RickAndMortyRepositoryImpl(get())
    }
}