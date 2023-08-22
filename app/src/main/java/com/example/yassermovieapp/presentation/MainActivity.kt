package com.example.yassermovieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.yassermovieapp.presentation.movieDetails.MovieDetailScreen
import com.example.yassermovieapp.presentation.movieList.MoviesListScreen
import com.example.yassermovieapp.presentation.ui.theme.YasserMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MoviesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YasserMovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MoviesListScreen.route
                    ) {
                        composable(
                            route = Screen.MoviesListScreen.route
                        ) {
                            MoviesListScreen(navController = navController, viewModel = viewModel)
                        }
                        composable(
                            route = Screen.MovieDetailScreen.route + "/{movieId}"
                        ) {
                            MovieDetailScreen(viewModel.detailState)
                        }
                    }

                }
            }
        }
    }
}
