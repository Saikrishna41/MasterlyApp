package com.example.masterlyapp.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Course(
    val id: Int = 0,
    val title: String,
    val description: String,
    val progress: Float = 0f,
    val timeOnline: String = "2000/ 10000"
)