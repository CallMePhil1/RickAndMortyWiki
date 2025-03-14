package com.github.callmephil1.rickandmortywiki.di

import com.github.callmephil1.rickandmortywiki.ui.loader.LoaderManager
import com.github.callmephil1.rickandmortywiki.ui.loader.LoaderManagerImpl
import com.github.callmephil1.rickandmortywiki.ui.navigation.NavigationHelper
import com.github.callmephil1.rickandmortywiki.ui.navigation.NavigationHelperImpl
import org.koin.dsl.module

val uiModule = module {
    single<LoaderManager> {
        LoaderManagerImpl()
    }
    single<NavigationHelper> {
        NavigationHelperImpl()
    }
}