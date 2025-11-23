package com.example.masterlyapp.domain.usecases

import com.example.masterlyapp.domain.model.Course
import com.example.masterlyapp.domain.repository.CourseRepository
import javax.inject.Inject

class DeleteCourseUseCase @Inject constructor(private val courseRepository: CourseRepository) {

    suspend fun deleteCourse(course : Course) {
        courseRepository.deleteCourse(course)
    }


    suspend fun deleteAll() {
        courseRepository.deleteAllCourses()
    }

}