package com.example.yassermovieapp.domain.useCases.getMovie


import com.example.yassermovieapp.common.Resource
import com.example.yassermovieapp.di.ContextProvider
import com.example.yassermovieapp.domain.models.MovieResponse
import com.example.yassermovieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
/**
 * Created by Abdallah Shehata on 8/22/2023.
 */
class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val contextProvider: ContextProvider
){
    operator fun invoke(movieId:String): Flow<Resource<MovieResponse>> = flow {
        try {
            emit(Resource.Loading())
            val movie=repository.getMovieById(movieId)
            emit(Resource.Success(movie))
        }catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage?:"An unexpected error occured "))
        }catch (e:IOException){
            emit(Resource.Error("Couldn't reach server. check your internet "))
        }
    }.flowOn(contextProvider.IO)
}