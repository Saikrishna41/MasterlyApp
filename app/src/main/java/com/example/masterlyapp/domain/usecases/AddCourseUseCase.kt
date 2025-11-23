package com.example.masterlyapp.domain.usecases

import com.example.masterlyapp.domain.model.Course
import com.example.masterlyapp.domain.repository.CourseRepository
import javax.inject.Inject

class AddCourseUseCase @Inject constructor(private val courseRepository: CourseRepository) {

    suspend operator fun invoke(course: Course) = courseRepository.insertCourse(course)
}