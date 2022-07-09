package com.example.tv4app.di

import com.example.tv4app.data.remote.TV4ContentApi
import com.example.tv4app.data.repository.TV4ContentRepositoryImpl
import com.example.tv4app.domain.repository.TV4ContentRepository
import com.example.tv4app.util.Constants.API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTV4ContentRepository(
        api: TV4ContentApi
    ): TV4ContentRepository = TV4ContentRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideTV4ContentApi(): TV4ContentApi {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

}