package com.example.final_assignment.models

// This is the model for showing course details on the detail screen
data class CourseDetails(
    val courseCode: String,
    val courseName: String,
    val instructor: String,
    val credits: Int,
    val description: String
)
