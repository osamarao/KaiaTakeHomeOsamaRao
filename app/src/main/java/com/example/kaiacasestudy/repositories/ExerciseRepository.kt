package com.example.kaiacasestudy.repositories

import com.example.kaiacasestudy.models.Exercise
import com.example.kaiacasestudy.data.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    val exercises: Flow<NetworkResult<List<ExerciseDataLayer>>>
}

data class ExerciseDataLayer(
    val id: Int,
    val name: String,
    val coverImageUrl: String,
)

fun Exercise.toExerciseDataLayer(): ExerciseDataLayer = ExerciseDataLayer(id, name, coverImageUrl)