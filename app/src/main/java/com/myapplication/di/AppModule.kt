package com.myapplication.di

import android.content.Context
import com.myapplication.common.Constants
import com.myapplication.data.MovieApi
import com.myapplication.data.repository.local.ConfigurationRepositoryImpl
import com.myapplication.data.repository.remote.MovieRepositoryImpl
import com.myapplication.domain.repository.local.ConfigurationRepository
import com.myapplication.domain.repository.remote.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        val client = OkHttpClient.Builder().addInterceptor(logging)

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .baseUrl(Constants.BASE_URL).build().create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieApi): MovieRepository = MovieRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideConfigurationRepository(@ApplicationContext appContext: Context): ConfigurationRepository =
        ConfigurationRepositoryImpl(appContext)
}