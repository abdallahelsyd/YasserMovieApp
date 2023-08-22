package com.example.yassermovieapp.presentation.movieList

import androidx.paging.PagingData
import com.example.yassermovieapp.data.remote.MoviesResponse
import kotlinx.coroutines.flow.Flow

data class MoviesListViewState(
    val isLoading:Boolean=false,
    val movies:Flow<PagingData<MoviesResponse.Result>>?=null,
    val error:String=""
)
