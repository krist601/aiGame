package com.kdg.uncertain.di.module

import android.content.Context
import com.kdg.uncertain.data.data_sources.local.QuestionLocalDataSource
import com.kdg.uncertain.data.data_sources.network.QuestionNetworkDataSource
import com.kdg.uncertain.data.services.CustomInterceptor
import com.kdg.uncertain.data.services.RetrofitMS
import com.kdg.uncertain.data.util.SharedPreferencesHelper
import com.kdg.uncertain.domain.repositories.QuestionRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(CustomInterceptor())
        .build()

    private fun provideUnsafeOkHttpClient(): OkHttpClient {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { _: String?, _: SSLSession? -> true }

            return builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit {
        val okHttpClient = provideUnsafeOkHttpClient()
        return Retrofit.Builder()
            .baseUrl("https://d1gpyzib29.execute-api.us-east-2.amazonaws.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

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