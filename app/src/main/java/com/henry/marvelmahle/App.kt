package com.henry.marvelmahle

import android.app.Application
import com.henry.marvelmahle.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    // region LIFECYCLE ----------------------------------------------------------------------------

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }

    // endregion
}