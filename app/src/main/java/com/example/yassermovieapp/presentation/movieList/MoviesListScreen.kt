package com.example.yassermovieapp.presentation.movieList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.yassermovieapp.data.remote.MoviesResponse
import com.example.yassermovieapp.presentation.MoviesViewModel
import com.example.yassermovieapp.presentation.Screen
import com.example.yassermovieapp.presentation.movieList.components.MovieItem


@Composable
fun MoviesListScreen(
    navController: NavController,
    viewModel: MoviesViewModel,
){
    val state=viewModel.state.value
    val listState = rememberLazyListState()

    val moviesListItems: LazyPagingItems<MoviesResponse.Result> = viewModel.movie.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(state = listState) {
            items(moviesListItems.itemCount) { item ->

                moviesListItems[item]?.let { result ->
                    MovieItem(result, onItemClick = {
                        viewModel.setCurrentId(it.id.toString())
                        navController.navigate(Screen.MovieDetailScreen.route + "/${it.id}")
                    })
                }
            }
            moviesListItems.apply {
                when {
                    loadState.refresh is LoadState.Loading ->
                        item { Text("Loading", color = Color.Blue, fontSize = 30.sp) }
                    loadState.append is LoadState.Loading ->
                        item { Text("Loading", color = Color.Blue, fontSize = 30.sp) }

                    loadState.append is LoadState.Error ->
                        item { Text("Error", color = Color.Red, fontSize = 30.sp) }
                    loadState.refresh is LoadState.Error -> item {
                        Text(
                            "Error",
                            color = Color.Red,
                            fontSize = 30.sp
                        )
                    }
                }
            }
        }
        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

    }
}