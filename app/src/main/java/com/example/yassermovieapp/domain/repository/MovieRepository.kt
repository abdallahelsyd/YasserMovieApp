package com.example.yassermovieapp.domain.repository

import com.example.yassermovieapp.data.remote.MoviesResponse
import com.example.yassermovieapp.domain.models.MovieResponse

/**
 * Created by Abdallah Shehata on 8/22/2023.
 */
interface MovieRepository {
    suspend fun getMovies(): MoviesResponse
    suspend fun getMovieById(movieId:String): MovieResponse
}