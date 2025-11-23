package com.example.masterlyapp.data.local.mapper

import com.example.masterlyapp.data.local.CourseEntity
import com.example.masterlyapp.domain.model.Course


fun CourseEntity.toDomain() : Course = Course(
    id = id,
    title = title,
    description = description,
    progress = progress,
    timeOnline = timeOnline
)


fun Course.toEntity() : CourseEntity = CourseEntity(
    id = id,
    title = title,
    description = description,
    progress = progress,
    timeOnline = timeOnline
)