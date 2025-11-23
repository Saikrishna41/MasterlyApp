package com.example.masterlyapp.data.repository

import androidx.annotation.WorkerThread
import com.example.masterlyapp.data.local.dao.CourseDao
import com.example.masterlyapp.data.local.mapper.toDomain
import com.example.masterlyapp.data.local.mapper.toEntity
import com.example.masterlyapp.domain.model.Course
import com.example.masterlyapp.domain.repository.CourseRepository
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val courseDao: CourseDao
) : CourseRepository {
    @WorkerThread
    override suspend fun getCourses(): List<Course> {
        return courseDao.getAllCourses().map { entities ->
            entities.toDomain()
        }
    }

    override suspend fun insertCourse(course: Course) {
        courseDao.insertCourse(course.toEntity())
    }

    override suspend fun deleteCourse(course: Course) {
            courseDao.deleteCourse(course.toEntity())
    }

    override suspend fun deleteAllCourses() {
        courseDao.deleteAllCourses()
    }
}
