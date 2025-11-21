package com.example.masterlyapp

import android.widget.ProgressBar
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.widget.ContentLoadingProgressBar

data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val timeOnline: String = "200 / 10000",
    val progressPercent: Float = 0.7f,
)

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val courses = listOf(
        Course(1, "Guitar", "Learn the basics of Guitar", timeOnline = "7000/10000"),
        Course(
            2,
            "JavaScript",
            "Dive deeper into JavaScript",
            timeOnline = "8000/10000",
            progressPercent = 0.8f
        ),
        Course(
            3,
            "Digital Painting",
            "Create stunning Digital Painting",
            timeOnline = "6500/10000",
            progressPercent = 0.65f
        ),
        Course(
            4,
            "Spanish",
            "Learn to speak Spanish fluently",
            timeOnline = "9000/10000",
            progressPercent = 0.9f
        ),
    )
    CourseList(modifier, courses)
}

@Composable
private fun CourseList(
    modifier: Modifier,
    courses: List<Course>
) {
    LazyColumn(modifier = modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp),
       ) {
        items(courses.size) { index ->
            CourseCard(courses, index)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
            )
        }
    }
}

@Composable
private fun CourseCard(
    courses: List<Course>,
    index: Int
) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .height(100.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = courses.get(index).title, color = Color.White)

                Icon(
                    painterResource(R.drawable.outline_arrow_forward_24),
                    contentDescription = "time icon",
                    tint = colorResource(R.color.purple_500)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Icon(
                    painterResource(R.drawable.outline_timer_24),
                    contentDescription = "time icon",
                )
                Text(
                    text = courses.get(index).timeOnline,
                    color = Color.White,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Text(text = "Progress", modifier = Modifier.padding(top = 10.dp))
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp),
                progress = { courses.get(index).progressPercent },
                color = ProgressIndicatorDefaults.linearColor,
                trackColor = ProgressIndicatorDefaults.linearTrackColor,
                strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun MainScreenPreview(modifier: Modifier = Modifier) {
    MainScreen(modifier = modifier)
}