package com.henry.marvelmahle.di

import android.content.Context
import com.henry.marvelmahle.BuildConfig.API_URL
import com.henry.marvelmahle.BuildConfig
import com.henry.marvelmahle.data.network.ApiHelper
import com.henry.marvelmahle.data.network.ApiHelperImpl
import com.henry.marvelmahle.data.network.ApiService
import com.henry.marvelmahle.utils.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

// This class has the definitions of the networks providers
val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), API_URL) }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }

    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}

object AppModuleKey {
    const val API_KEY = "apikey"
    const val TIMESTAMP = "ts"
    const val HASH_KEY = "hash"
}

val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()

private fun hash(): String {
    val input = "$timeStamp${BuildConfig.PRIVATE_API_KEY_VALUE}${BuildConfig.PUBLIC_API_KEY_VALUE}"
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

fun provideOkHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor(logging)
    httpClient.addInterceptor { chain ->
        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(AppModuleKey.API_KEY, BuildConfig.PUBLIC_API_KEY_VALUE)
            .addQueryParameter(AppModuleKey.TIMESTAMP, timeStamp)
            .addQueryParameter(AppModuleKey.HASH_KEY, hash())
            .build()

        chain.proceed(original.newBuilder().url(url).build())
    }
    return httpClient.build()
}

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    apiUrl: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(apiUrl)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)
