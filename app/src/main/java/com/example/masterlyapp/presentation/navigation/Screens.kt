package com.example.masterlyapp.presentation.navigation

sealed class Screens(val route : String) {
    data object HomeScreen : Screens("homeScreen")
    data object LoadingScreen : Screens("loadingScreen")
    data object ErrorScreen : Screens("ErrorScreen")
    data object DetailScreen : Screens("detailScreen/{id}") {
        fun passId(id : Int) : String {
            return "detailScreen/$id"
        }
    }
}