package com.albertmiro.data.di

import com.albertmiro.data.BuildConfig
import com.albertmiro.data.api.APIService
import com.albertmiro.data.mapper.TripsMapper
import com.albertmiro.data.repository.APIDataSource
import com.albertmiro.data.repository.TripsRepositoryImpl
import com.albertmiro.domain.repository.TripsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private val httpClient = OkHttpClient.Builder()
    .connectTimeout(10, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .addInterceptor(loggingInterceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .client(httpClient)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private val apiService = retrofit.create(APIService::class.java)
private val mapper = TripsMapper()

val networkModule: Module = module {
    single { apiService }
}

val repositoryModule: Module = module {
    single { mapper }
    single { APIDataSource(service = get()) }
    single { TripsRepositoryImpl(dataSource = get(), mapper = get()) as TripsRepository }
}