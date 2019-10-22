package com.albertmiro.troopertrips.di

import com.albertmiro.data.di.networkModule
import com.albertmiro.data.di.repositoryModule
import com.albertmiro.domain.di.useCaseModule
import org.koin.core.context.loadKoinModules

object Modules {
    fun init() {
        loadKoinModules(
            listOf(
                viewModelModule,
                useCaseModule,
                repositoryModule,
                networkModule
            )
        )
    }
}