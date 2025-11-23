package com.example.masterlyapp.di

import android.content.Context
import androidx.room.Room
import com.example.masterlyapp.data.local.dao.CourseDao
import com.example.masterlyapp.data.local.db.CourseDatabase
import com.example.masterlyapp.data.repository.CourseRepositoryImpl
import com.example.masterlyapp.domain.repository.CourseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
                context,
                CourseDatabase::class.java,
                "CourseDB"
            ).fallbackToDestructiveMigration(false).build()


    @Provides
    @Singleton
    fun provideDao(db : CourseDatabase) = db.getCourseDao()

    @Provides
    @Singleton
    fun provideRepository(curseDao: CourseDao) : CourseRepository = CourseRepositoryImpl(curseDao)


}