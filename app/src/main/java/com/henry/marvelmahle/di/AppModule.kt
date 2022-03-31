package com.henry.marvelmahle.di

import android.content.Context
import com.henry.marvelmahle.BuildConfig.API_URL
import com.henry.marvelmahle.utils.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// This class has the definitions of the networks providers
val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), API_URL) }
    //single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }

   /*single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }*/
}

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    apiUrl: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(apiUrl)
        .client(okHttpClient)
        .build()

/*private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)*/
