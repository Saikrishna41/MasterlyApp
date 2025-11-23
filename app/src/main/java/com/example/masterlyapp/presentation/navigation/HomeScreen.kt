package com.example.masterlyapp.presentation.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.masterlyapp.R
import com.example.masterlyapp.domain.model.Course

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, courses: List<Course>,
    navController: NavController,
    onDeleteItem: (Course) -> Unit
) {

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        stickyHeader {
            Text(text = "Masterly class")
        }
        items(
            count = courses.size,
            key = { index ->
                courses[index].id
            }) { index ->
            val course = courses[index]
            val dismissState = rememberSwipeToDismissBoxState(
                confirmValueChange = {
                    if (it == SwipeToDismissBoxValue.StartToEnd || it == SwipeToDismissBoxValue.EndToStart) {
                        onDeleteItem(course)
                        true
                    } else false
                }
            )
            SwipeToDismissBox(
                state = dismissState,
                backgroundContent = {},
                onDismiss = { onDeleteItem(course) },
                content = {
                    CourseCard(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Screens.DetailScreen.passId(course.id))
                            }
                            .fillMaxSize()
                            .padding(top = 10.dp), course
                    )
                },
            )


        }
    }
}

@Composable
fun CourseCard(modifier: Modifier = Modifier, course: Course) {
    var progress by
    remember { mutableFloatStateOf(0.1f) }
    progress = course.progress
    Card(
        modifier = modifier
            .height(200.dp)
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(start = 20.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = course.title, color = Color.White)
            Row {
                Icon(
                    painter = painterResource(R.drawable.outline_av_timer_24),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.padding(end = 5.dp))
                Text(text = course.timeOnline, color = Color.White)
            }

            Column {
                Text(text = "Progress", color = Color.White)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 5.dp)
                        .height(5.dp),
                    color = ProgressIndicatorDefaults.linearColor,
                    trackColor = ProgressIndicatorDefaults.linearTrackColor,
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                    drawStopIndicator = {},
                    gapSize = 0.dp
                )
            }

        }

    }
}

@Composable
@Preview
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    HomeScreen(
        modifier = modifier,
        courses = listOf(
            Course(
                id = 1,
                title = "Guitar",
                description = "Learn Guitar basics",
                progress = 0.7f,
                timeOnline = "7000/10000"

            ),
            Course(
                id = 2,
                title = "JavaScript",
                description = "Build Javascript applications",
                progress = 0.8f,
                timeOnline = "8000/10000"

            ),
            Course(
                id = 3,
                title = "Digital Planning",
                description = "Master Digital Planning",
                progress = 0.9f,
                timeOnline = "9000/10000"

            ),
            Course(
                id = 4,
                title = "Spanish",
                description = "Master Digital Planning",
                progress = 0.6f,
                timeOnline = "6000/10000"
            )
        ),
        navController = rememberNavController(),
        onDeleteItem = {}
    )
}
