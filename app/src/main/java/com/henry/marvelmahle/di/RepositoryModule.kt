package com.henry.marvelmahle.di

import com.henry.marvelmahle.data.repository.AppRepository
import org.koin.dsl.module

// This class has the declaration of the repositories
val repoModule = module {
    single { AppRepository(get()) }
}