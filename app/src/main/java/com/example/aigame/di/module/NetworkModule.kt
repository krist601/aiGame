package com.example.aigame.di.module

import android.content.Context
import com.example.aigame.data.data_sources.local.QuestionLocalDataSource
import com.example.aigame.data.data_sources.network.QuestionNetworkDataSource
import com.example.aigame.data.services.RetrofitMS
import com.example.aigame.data.util.SharedPreferencesHelper
import com.example.aigame.domain.repositories.QuestionRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    val okHttpClient = OkHttpClient.Builder()
        //.addInterceptor(CustomInterceptor())
        .build()
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl("https://d1gpyzib29.execute-api.us-east-2.amazonaws.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()

    @Provides
    fun provideQuestionApiService(retrofit: Retrofit): RetrofitMS =
        retrofit.create(RetrofitMS::class.java)

    @Provides
    fun getSharedPreferencesHelper(@ApplicationContext context: Context): SharedPreferencesHelper =
        SharedPreferencesHelper(context)


    @Provides
    fun questionLocalDataSource(sharedPreferencesHelper: SharedPreferencesHelper): QuestionLocalDataSource =
        QuestionLocalDataSource(sharedPreferencesHelper)

    @Provides
    fun questionNetworkDataSource(questionApiService: RetrofitMS): QuestionNetworkDataSource =
        QuestionNetworkDataSource(questionApiService)

    @Provides
    fun provideQuestionRepository(questionLocalDataSource: QuestionLocalDataSource, questionNetworkDataSource: QuestionNetworkDataSource): QuestionRepository =
        QuestionRepository(questionLocalDataSource, questionNetworkDataSource)
}