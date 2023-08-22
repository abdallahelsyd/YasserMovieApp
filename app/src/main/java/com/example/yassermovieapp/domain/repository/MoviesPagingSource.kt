package com.example.yassermovieapp.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.yassermovieapp.BuildConfig
import com.example.yassermovieapp.data.remote.MoviesAPI
import com.example.yassermovieapp.data.remote.MoviesResponse
import retrofit2.HttpException
import java.io.IOException
/**
 * Created by Abdallah Shehata on 8/22/2023.
 */
class MoviesPagingSource(private val api: MoviesAPI): PagingSource<Int, MoviesResponse.Result>() {

    override fun getRefreshKey(state: PagingState<Int, MoviesResponse.Result>): Int?
    {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>):LoadResult<Int, MoviesResponse.Result> {
        return try {
            val nextPage = params.key ?: 1
            val moviesList = RetrofitClient.apiService.getMovies(10,nextPage,BuildConfig.SECRET_KEY)
            LoadResult.Page(
                data = moviesList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (moviesList.results.isEmpty()) null else moviesList.page?.plus(1)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}