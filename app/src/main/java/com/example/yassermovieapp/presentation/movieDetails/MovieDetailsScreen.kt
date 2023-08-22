package com.example.yassermovieapp.presentation.movieDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yassermovieapp.BuildConfig
import com.google.accompanist.coil.rememberCoilPainter

@Preview
@Composable
fun MovieDetailScreen (
    movieState: State<MoviesDetailsViewState> = mutableStateOf(MoviesDetailsViewState())
) {

    val state=movieState.value
    Column(
        Modifier.background(Color.White).fillMaxSize().padding(10.dp),
        horizontalAlignment = CenterHorizontally,

    ) {
        state.movie?.let { movie ->
                Row(
                    Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                )
                {
                    val image = rememberCoilPainter(
                        request = BuildConfig.IMAGE_BASE_URL + movie.poster_path,
                        fadeIn = true
                    )
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.FillHeight
                    )

                }
            Text(
                text = movie.title,
                modifier = Modifier.fillMaxWidth().padding(0.dp,30.dp).align(CenterHorizontally),
                fontSize = 30.sp,
                color = Color.Black
            )
            Text(
                text = movie.release_date,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Gray
            )
            Text(
                text = movie.overview,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Gray
            )


        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)

            )
        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}