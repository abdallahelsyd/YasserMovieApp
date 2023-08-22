package com.example.yassermovieapp.presentation.movieList.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yassermovieapp.BuildConfig
import com.example.yassermovieapp.R
import com.example.yassermovieapp.data.remote.MoviesResponse
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun MovieItem(
    moviesItem: MoviesResponse.Result,
    onItemClick:(MoviesResponse.Result)->Unit={}
){
    Card (
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .background(Color.White)
            .clickable(onClick = { onItemClick(moviesItem) }),

        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Surface(
                modifier = Modifier
                    .height(100.dp)
                    .width(70.dp),
                color = MaterialTheme.colorScheme.surface.copy(
                    alpha = 0.2f)
            ) {
                val image = rememberCoilPainter(
                    request = BuildConfig.IMAGE_BASE_URL+moviesItem.poster_path,
                    fadeIn = true)
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .width(70.dp)
                        ,
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp,
                    )
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 5.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(

                        text = moviesItem.title,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 17.sp),
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.widthIn(0.dp,250.dp)
                    )
                    Row(horizontalArrangement = Arrangement.Center) {
                        Image(painterResource(
                            id =  R.drawable.ic_yellow_star),
                            contentDescription ="star image" )
                        Text(
                            text = moviesItem.vote_average.toString(),
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Gray,
                            style = typography.bodyMedium,

                            modifier = Modifier.padding(0.dp,4.dp,0.dp,0.dp)
                        )
                    }
                    
                }

                CompositionLocalProvider(
                    //LocalContentAlpha provides ContentAlpha.medium
                ) {
                    Text(
                        text = moviesItem.release_date.removeRange(4,moviesItem.release_date.length),
                        style = typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(end = 25.dp)
                    )
                    Text(
                        text = moviesItem.overview,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        style = typography.bodyMedium,
                        maxLines = 2
                    )
                }

            }
        }
    }

}