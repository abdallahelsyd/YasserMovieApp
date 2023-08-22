package com.example.yassermovieapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.yassermovieapp.common.Resource
import com.example.yassermovieapp.data.remote.MoviesAPI
import com.example.yassermovieapp.domain.repository.MoviesPagingSource
import com.example.yassermovieapp.domain.useCases.getMovie.GetMovieUseCase
import com.example.yassermovieapp.domain.useCases.getMovies.GetMoviesUseCase
import com.example.yassermovieapp.presentation.movieDetails.MoviesDetailsViewState
import com.example.yassermovieapp.presentation.movieList.MoviesListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getMovieUseCase: GetMovieUseCase,
    api: MoviesAPI
):ViewModel() {

    private val _state= mutableStateOf(MoviesListViewState())
    val state: State<MoviesListViewState> =_state

    init {
        getMovies()
    }
    val movie= Pager(
        PagingConfig(pageSize = 10)
    ) {
        MoviesPagingSource(api)
    }.flow.cachedIn(viewModelScope)

    private fun getMovies(){
        getMoviesUseCase().onEach {
            when(it){
                is Resource.Error -> {
                    _state.value= MoviesListViewState(error = it.message?:"An unexpected error occored")

                }
                is Resource.Loading -> {
                    _state.value= MoviesListViewState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value= MoviesListViewState(movies = it.data!!.flow.cachedIn(viewModelScope))

                }
            }
        }.launchIn(viewModelScope)
    }


    private val _detailState= mutableStateOf(MoviesDetailsViewState())
    val detailState: State<MoviesDetailsViewState> =_detailState
    private var currentId=""
    fun setCurrentId(id:String){
        currentId=id
        getMovie(id)
    }
    private fun getMovie(movieId:String){
        getMovieUseCase(movieId).onEach {
            when(it){
                is Resource.Error -> {
                    _detailState.value= MoviesDetailsViewState(error = it.message?:"An unexpected error occored")

                }
                is Resource.Loading -> {
                    _detailState.value= MoviesDetailsViewState(isLoading = true)
                }
                is Resource.Success -> {
                    _detailState.value= MoviesDetailsViewState(movie = it.data)

                }
            }
        }.launchIn(viewModelScope)
    }
}