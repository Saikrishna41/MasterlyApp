package com.example.masterlyapp.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.masterlyapp.domain.model.Course

@Composable
fun DetailScreen(modifier: Modifier = Modifier, course: Course) {
    Box(modifier = modifier) {
        Text(text = course.title)
    }
}


@Composable
@Preview
fun DetailScreenPreview(modifier: Modifier = Modifier) {
}