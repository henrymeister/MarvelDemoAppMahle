package com.henry.marvelmahle.di

import android.content.Context
import com.henry.marvelmahle.BuildConfig.API_URL
import com.henry.marvelmahle.BuildConfig
import com.henry.marvelmahle.data.network.character.CharacterApiHelper
import com.henry.marvelmahle.data.network.character.CharacterApiHelperImpl
import com.henry.marvelmahle.data.network.character.CharacterApiService
import com.henry.marvelmahle.data.network.comics.ComicsApiHelper
import com.henry.marvelmahle.data.network.comics.ComicsApiHelperImpl
import com.henry.marvelmahle.data.network.comics.ComicsApiService
import com.henry.marvelmahle.data.network.series.SeriesApiHelper
import com.henry.marvelmahle.data.network.series.SeriesApiHelperImpl
import com.henry.marvelmahle.data.network.series.SeriesApiService
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
    single { provideCharactersApiService(get()) }
    single { provideSeriesApiService(get()) }
    single { provideComicsApiService(get()) }
    single { provideNetworkHelper(androidContext()) }

    single<CharacterApiHelper> {
        return@single CharacterApiHelperImpl(get())
    }
    single<SeriesApiHelper> {
        return@single SeriesApiHelperImpl(get())
    }
    single<ComicsApiHelper> {
        return@single ComicsApiHelperImpl(get())
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

private fun provideCharactersApiService(retrofit: Retrofit): CharacterApiService =
    retrofit.create(CharacterApiService::class.java)

private fun provideSeriesApiService(retrofit: Retrofit): SeriesApiService =
    retrofit.create(SeriesApiService::class.java)

private fun provideComicsApiService(retrofit: Retrofit): ComicsApiService =
    retrofit.create(ComicsApiService::class.java)
