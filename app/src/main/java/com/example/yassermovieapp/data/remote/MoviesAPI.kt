package com.example.yassermovieapp.data.remote

import com.example.yassermovieapp.domain.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Abdallah Shehata on 8/22/2023.
 */
interface MoviesAPI {

    @GET("3/discover/movie")
    suspend fun getMovies(
        @Query("perPage") perPage:Int,
        @Query("page") page:Int,
        @Query("api_key") key:String
    ):MoviesResponse


    @GET("3/movie/{movieId}")
    suspend fun getMovieById(
        @Path("movieId") movieId:String,
        @Query("api_key") key:String
    ): MovieResponse
}