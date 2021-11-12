package com.myapplication.presentation.movie_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AdvertiseItem() {
    Text(
        text = "Advertise",
        style = MaterialTheme.typography.h3,
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp)
    )
}