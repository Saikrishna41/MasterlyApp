package com.example.masterlyapp.di

import com.example.masterlyapp.domain.repository.CourseRepository
import com.example.masterlyapp.domain.usecases.AddCourseUseCase
import com.example.masterlyapp.domain.usecases.GetCoursesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetCoursesUseCase(repository: CourseRepository) : GetCoursesUseCase {
        return GetCoursesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddCoursesUseCase(repository: CourseRepository) : AddCourseUseCase
    {
        return AddCourseUseCase(repository)
    }
}