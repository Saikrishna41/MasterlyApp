package com.example.masterlyapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.masterlyapp.domain.model.Course
import com.example.masterlyapp.domain.usecases.AddCourseUseCase
import com.example.masterlyapp.domain.usecases.DeleteCourseUseCase
import com.example.masterlyapp.domain.usecases.GetCoursesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val addCourseUseCase: AddCourseUseCase,
    private val deleteCourseUseCase: DeleteCourseUseCase,
) : ViewModel() {
    private val _coursesState = MutableStateFlow<CourseUIState>(
        value = CourseUIState.default()
    )
    val courseState = _coursesState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = CourseUIState.default(),
    )

    init {
        observeSkills()
    }

    fun deleteCourse(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCourseUseCase.deleteCourse(course)
        }
    }

    fun deleteAll(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCourseUseCase.deleteAll()
        }
    }

    fun addCourse(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            _coursesState.update {
                it.copy(
                    submissionState = SubmissionState.Loading
                )
            }
            addCourseUseCase.invoke(
               course
            )

        }
        observeSkills()
    }

    private fun observeSkills() {
        viewModelScope.launch(Dispatchers.IO) {
            getCoursesUseCase.invoke().collect { courses ->
                try {
                    _coursesState.update { courseUIState ->
                        courseUIState.copy(
                            submissionState = SubmissionState.Success,
                            courseList = courses
                        )
                    }

                } catch (e: IllegalStateException) {
                    e.printStackTrace()
                    _coursesState.update { courseUIState ->
                        courseUIState.copy(
                            submissionState = SubmissionState.Error
                        )
                    }
                }

            }
        }
    }



    data class CourseUIState(
        val courseList: List<Course>,
        val submissionState: SubmissionState
    ) {
        companion object {
            fun default() = CourseUIState(
                courseList = emptyList(),
                submissionState = SubmissionState.Loading
            )
        }

    }

    sealed interface SubmissionState {
        object Loading : SubmissionState
        object Error : SubmissionState
        object Success : SubmissionState
    }
}