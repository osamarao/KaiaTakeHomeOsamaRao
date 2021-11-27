package com.example.kaiacasestudy.usecase

import com.example.kaiacasestudy.network.NetworkResult
import com.example.kaiacasestudy.network.mapIfSuccess
import com.example.kaiacasestudy.repositories.ExerciseDataLayer
import com.example.kaiacasestudy.repositories.ExerciseRepository
import com.example.kaiacasestudy.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface MainActivityUseCase {
    fun exercises(): Flow<NetworkResult<List<ExerciseUseCase>>>
}

class MainActivityUseCaseImpl @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
    private val favoritesRepository: FavoritesRepository,
) : MainActivityUseCase {
    override fun exercises(): Flow<NetworkResult<List<ExerciseUseCase>>> =
        exerciseRepository.exercises.map { dataLayerExercise ->
            dataLayerExercise.mapIfSuccess { list ->
                list.map { dataLayerExercise ->
                    dataLayerExercise.toExerciseUseCase()
                }
            }
        }
}

data class ExerciseUseCase(
    val id: Int,
    val name: String,
    val coverImageUrl: String,
    val favorite: Boolean,
)

fun ExerciseDataLayer.toExerciseUseCase(favorite: Boolean = false): ExerciseUseCase =
    ExerciseUseCase(id, name, coverImageUrl, favorite)