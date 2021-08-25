package com.example.registerapp.connection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideOkhttpClient() = OkHttpClient.Builder()
        .addNetworkInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .followRedirects(true)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Api = Retrofit.Builder()
        .baseUrl("http://94.127.67.113:8099")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()
        .create(Api::class.java)
}
