package com.example.masterlyapp.presentation

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.masterlyapp.presentation.navigation.AppNavHost
import com.example.masterlyapp.presentation.navigation.ErrorScreen
import com.example.masterlyapp.presentation.navigation.LoadingScreen
import com.example.masterlyapp.presentation.theme.MasterlyAppTheme
import com.example.masterlyapp.presentation.viewmodel.CoursesViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.masterlyapp.R
import com.example.masterlyapp.presentation.navigation.Screens
import androidx.core.text.isDigitsOnly
import com.example.masterlyapp.domain.model.Course


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val coursesViewModel = hiltViewModel<CoursesViewModel>()
            var showDialog by remember {
                mutableStateOf(false)
            }

            if (showDialog) {
                ShowAlertDialog(
                    modifier = Modifier,
                    dismissDialog = {
                        showDialog = false
                    },
                    { course ->
                        coursesViewModel.addCourse(course)
                        showDialog = false
                    }
                )
            }
            MasterlyAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                showDialog = !showDialog
                            },
                        )
                        {
                            Icon(
                                painter = painterResource(R.drawable.outline_add_24),
                                contentDescription = ""
                            )
                        }
                    }) { innerPadding ->
                    val courseUiState by coursesViewModel.courseState.collectAsState()
                    when (courseUiState.submissionState) {
                        CoursesViewModel.SubmissionState.Success -> {
                            AppNavHost(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding),
                                courseUiState = courseUiState
                            )
                        }

                        CoursesViewModel.SubmissionState.Error -> {
                            ErrorScreen(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)
                            )
                        }

                        CoursesViewModel.SubmissionState.Loading -> {
                            LoadingScreen(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)
                            )
                        }
                    }

                }
            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowAlertDialog(
    modifier: Modifier = Modifier, dismissDialog: () -> Unit,
    addUser: (Course) -> Unit
) {
    var courseName by remember {
        mutableStateOf("")
    }
    var courseDescription by remember {
        mutableStateOf("")
    }
    var isError by remember { mutableStateOf(false) }

    BasicAlertDialog(
        modifier = modifier
            .height(300.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            ),
        onDismissRequest = dismissDialog,
        properties = DialogProperties(),
        content = {
            Column(modifier = Modifier.fillMaxSize()) {
                OutlinedTextField(
                    modifier = Modifier.padding(start = 10.dp, top = 20.dp),
                    value = courseName,
                    onValueChange = {
                        courseName = it
                        isError = courseName.isBlank() || courseName.length < 3
                    },
                    label = {
                        Text(text = "Enter course name")

                    },
                    isError = isError
                )
                OutlinedTextField(
                    modifier = Modifier.padding(start = 10.dp, top = 20.dp),
                    value = courseDescription,
                    onValueChange = {
                        courseDescription = it
                        isError = courseDescription.isBlank() || courseDescription.length < 3
                    },
                    label = {
                        Text(text = "Enter course description")
                    },
                    isError = isError
                )
                if (isError) {
                    Text(
                        text = "Course name must be at least 3 characters",
                        color = Color.Red,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            if (!TextUtils.isEmpty(courseName)
                                || !courseName.isDigitsOnly() || courseName.length > 3
                            ) {
                                addUser(
                                    Course(
                                        title = courseName,
                                        description = courseDescription,
                                        progress = 0.0f,
                                        timeOnline = "0/0"
                                    )
                                )
                            }
                        }
                    ) {
                        Text(text = "Add course")
                    }
                }

            }
        })
}


@Composable
@Preview(showBackground = true)
fun ShowAlertDialogPreview(modifier: Modifier = Modifier) {
    ShowAlertDialog(
        modifier, {},
        {})
}