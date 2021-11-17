package com.myapplication.di

import android.content.Context
import com.google.gson.Gson
import com.myapplication.common.Constants
import com.myapplication.common.Resource
import com.myapplication.data.MovieApi
import com.myapplication.data.remote.dto.configuration.ConfigurationDto
import com.myapplication.data.remote.dto.movieList.MovieResponseDto
import com.myapplication.data.remote.dto.movieList.toMovieList
import com.myapplication.data.repository.local.ConfigurationRepositoryImpl
import com.myapplication.domain.model.MovieDetail
import com.myapplication.domain.model.MovieList
import com.myapplication.domain.repository.local.ConfigurationRepository
import com.myapplication.domain.repository.remote.MovieRepository
import com.myapplication.presentation.movie_list.testResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
@Module
object FakeModule {

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
    fun provideMovieRepository(api: MovieApi): MovieRepository = object : MovieRepository {
        override fun getListMovies(path: String, page: Int): Flow<Resource<MovieList>> = flow {
            try {
                val response =
                    Gson().fromJson(testResponse, MovieResponseDto::class.java).toMovieList()
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(
                    Resource.Error<MovieList>(
                        e.localizedMessage ?: "An unexpected error occurred"
                    )
                )
            }
        }

        override fun getMovieDetail(movie_id: String): Flow<Resource<MovieDetail>> {
            return flowOf()
        }

        override fun getConfiguration(): Flow<Resource<ConfigurationDto>> {
            return flowOf()
        }

    }

    @Provides
    @Singleton
    fun provideConfigurationRepository(@ApplicationContext appContext: Context): ConfigurationRepository =
        ConfigurationRepositoryImpl(appContext)
}