package com.myapplication.presentation.movie_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.myapplication.R
import com.myapplication.data.remote.dto.configuration.ConfigurationDto
import com.myapplication.domain.model.Movie

@Composable
fun MovieListItem(
    configurationDto: ConfigurationDto?,
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp
            )
            .fillMaxWidth()
            .clickable { onItemClick(movie) },
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            if (configurationDto != null) {
                val painter =
                    rememberImagePainter(data = "${configurationDto.images?.secure_base_url}${configurationDto.images?.poster_sizes?.last()}${movie.poster_path}",
                        builder = {
                            placeholder(R.drawable.poster)
                        })

                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )
            }
            Column(
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h4,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = movie.release_date,
                    style = MaterialTheme.typography.h6,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
                Row {
                    Image(
                        modifier = Modifier
                            .size(12.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(id = R.drawable.ic_baseline_star_24),
                        contentDescription = null,
                    )

                    Text(
                        text = movie.vote_average,
                        style = MaterialTheme.typography.h6,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
