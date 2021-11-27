package com.example.kaiacasestudy.usecase

import com.example.kaiacasestudy.network.NetworkResult
import com.example.kaiacasestudy.network.mapIfSuccess
import com.example.kaiacasestudy.repositories.ExerciseDataLayer
import com.example.kaiacasestudy.repositories.ExerciseRepository
import com.example.kaiacasestudy.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

interface ExerciseListFragmentUseCase {
    fun exercises(): Flow<NetworkResult<List<ExerciseUseCaseModel>>>
}

class ExerciseListFragmentUseCaseImpl @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
    private val favoritesRepository: FavoritesRepository,
) : ExerciseListFragmentUseCase {
    override fun exercises(): Flow<NetworkResult<List<ExerciseUseCaseModel>>> {
        return exerciseRepository.exercises.combine(favoritesRepository.favorites())
        { dataLayerExercise, favorites ->
            dataLayerExercise.mapIfSuccess { list ->
                list.map { dataLayerExercise ->
                    dataLayerExercise.toExerciseUseCase(favorite = favorites.contains(dataLayerExercise.id))
                }
            }
        }
    }
}

data class ExerciseUseCaseModel(
    val id: Int,
    val name: String,
    val coverImageUrl: String,
    val favorite: Boolean,
)

fun ExerciseDataLayer.toExerciseUseCase(favorite: Boolean = false): ExerciseUseCaseModel =
    ExerciseUseCaseModel(id, name, coverImageUrl, favorite)