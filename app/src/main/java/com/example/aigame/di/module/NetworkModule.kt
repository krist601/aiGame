package com.example.aigame.di.module

import com.example.aigame.data.services.RetrofitMS
import com.example.aigame.domain.repositories.QuestionRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.example.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun provideQuestionApiService(retrofit: Retrofit): RetrofitMS =
        retrofit.create(RetrofitMS::class.java)

    @Provides
    fun provideQuestionRepository(questionApiService: RetrofitMS): QuestionRepository =
        QuestionRepository(questionApiService)
}