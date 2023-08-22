package com.example.yassermovieapp.di

import com.example.yassermovieapp.BuildConfig
import com.example.yassermovieapp.data.remote.MoviesAPI
import com.example.yassermovieapp.data.repository.MovieRepositoryImp
import com.example.yassermovieapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Abdallah Shehata on 8/22/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideMovieAPI(): MoviesAPI {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api:MoviesAPI): MovieRepository {
        return MovieRepositoryImp(api)
    }

}