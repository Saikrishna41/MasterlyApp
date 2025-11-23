package com.example.masterlyapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.masterlyapp.presentation.viewmodel.CoursesViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    courseUiState : CoursesViewModel.CourseUIState,
    coursesViewModel: CoursesViewModel = hiltViewModel<CoursesViewModel>()
) {
    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(
                modifier = modifier, courseUiState.courseList,
                navController = navController,
                onDeleteItem =  { course ->
                    coursesViewModel.deleteCourse(course)
                }
            )
        }
        composable(
            route = Screens.DetailScreen.route,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("id")

            DetailScreen(modifier = modifier, courseUiState.courseList.get(courseId!! - 1))
        }

        composable(
            route = Screens.LoadingScreen.route
        ) {
            LoadingScreen(modifier = modifier)
        }

        composable(
            route = Screens.ErrorScreen.route
        ) {
            ErrorScreen(modifier = modifier)
        }
    }
}