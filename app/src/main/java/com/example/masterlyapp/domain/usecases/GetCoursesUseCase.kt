package com.example.masterlyapp.domain.usecases

import com.example.masterlyapp.domain.model.Course
import com.example.masterlyapp.domain.repository.CourseRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(private val repository: CourseRepository) {

    operator fun invoke(): Flow<List<Course>> = flow {
        delay(5000)
        emit(repository.getCourses())
    }
}