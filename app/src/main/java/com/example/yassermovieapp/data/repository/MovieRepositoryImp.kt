package com.example.yassermovieapp.data.repository

import com.example.yassermovieapp.BuildConfig
import com.example.yassermovieapp.data.remote.MoviesAPI
import com.example.yassermovieapp.data.remote.MoviesResponse
import com.example.yassermovieapp.domain.models.MovieResponse
import com.example.yassermovieapp.domain.repository.MovieRepository
import javax.inject.Inject
/**
 * Created by Abdallah Shehata on 8/22/2023.
 */
class MovieRepositoryImp @Inject constructor(
    private val moviesApi: MoviesAPI
): MovieRepository {
    override suspend fun getMovies(): MoviesResponse {
        return moviesApi.getMovies(10,1,BuildConfig.SECRET_KEY)
    }

    override suspend fun getMovieById(movieId:String): MovieResponse {
        return moviesApi.getMovieById(movieId,BuildConfig.SECRET_KEY)
    }

}