package com.example.yassermovieapp.domain.useCases.getMovies

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.yassermovieapp.common.Resource
import com.example.yassermovieapp.data.remote.MoviesAPI
import com.example.yassermovieapp.data.remote.MoviesResponse
import com.example.yassermovieapp.di.ContextProvider

import com.example.yassermovieapp.domain.repository.MovieRepository
import com.example.yassermovieapp.domain.repository.MoviesPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Abdallah Shehata on 8/22/2023.
 */
@Singleton
class GetMoviesUseCase @Inject constructor(
    private val contextProvider: ContextProvider,
    private val api: MoviesAPI
){
    operator fun invoke(): Flow<Resource<Pager<Int, MoviesResponse.Result>>> = flow {
        try {
            emit(Resource.Loading())
            //val movies=repository.getMovies()
            val pager = Pager(
                PagingConfig(pageSize = 10)) {
                MoviesPagingSource(api)
            }
            emit(Resource.Success(pager))
        }catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage?:"An unexpected error occured "))
        }catch (e:IOException){
            emit(Resource.Error("Couldn't reach server. check your internet "))
        }
    }.flowOn(contextProvider.IO)
}