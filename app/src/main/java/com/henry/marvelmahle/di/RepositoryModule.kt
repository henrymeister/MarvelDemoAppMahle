package com.henry.marvelmahle.di

import com.henry.marvelmahle.data.repository.CharactersRepository
import com.henry.marvelmahle.data.repository.ComicsRepository
import com.henry.marvelmahle.data.repository.SeriesRepository
import org.koin.dsl.module

// This class has the declaration of the repositories
val repoModule = module {
    single { CharactersRepository(get()) }
    single { ComicsRepository(get()) }
    single { SeriesRepository(get()) }
}