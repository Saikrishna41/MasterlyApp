package com.example.masterlyapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.masterlyapp.data.local.CourseEntity
import com.example.masterlyapp.data.local.dao.CourseDao


@Database(entities = [CourseEntity::class], version = 1, exportSchema = false)
abstract class  CourseDatabase : RoomDatabase() {

    abstract fun getCourseDao(): CourseDao
}