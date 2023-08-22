package com.example.yassermovieapp.presentation.movieDetails

import com.example.yassermovieapp.domain.models.MovieResponse

data class MoviesDetailsViewState(
    val isLoading:Boolean=false,
    val movie: MovieResponse? =null,
    val error:String=""
)
