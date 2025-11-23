package com.example.masterlyapp.domain.repository

import com.example.masterlyapp.domain.model.Course


interface CourseRepository {
    suspend fun getCourses(): List<Course>
    suspend fun insertCourse(course: Course)

    suspend fun deleteCourse(course: Course)

    suspend fun deleteAllCourses()

}