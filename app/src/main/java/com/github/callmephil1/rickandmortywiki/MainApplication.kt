package com.github.callmephil1.rickandmortywiki

import android.app.Application
import com.github.callmephil1.rickandmortywiki.di.networkModule
import com.github.callmephil1.rickandmortywiki.di.repositoryModule
import com.github.callmephil1.rickandmortywiki.di.uiModule
import com.github.callmephil1.rickandmortywiki.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)

            modules(
                networkModule,
                repositoryModule,
                uiModule,
                viewModelModule
            )
        }
    }
}