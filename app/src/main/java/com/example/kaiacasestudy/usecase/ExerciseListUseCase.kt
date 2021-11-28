package com.example.kaiacasestudy.usecase

import com.example.kaiacasestudy.data.NetworkResult
import com.example.kaiacasestudy.data.mapIfSuccess
import com.example.kaiacasestudy.repositories.ExerciseDataLayer
import com.example.kaiacasestudy.repositories.ExerciseRepository
import com.example.kaiacasestudy.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

interface ExerciseListUseCase {
    fun exercises(): Flow<NetworkResult<List<ExerciseUseCaseModel>>>
    fun exercise(id: Int): Flow<NetworkResult<ExerciseUseCaseModel>>
    suspend fun updateFavorites(idList: List<Int>)
}

class ExerciseListUseCaseImpl @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
    private val favoritesRepository: FavoritesRepository,
) : ExerciseListUseCase {
    override fun exercises(): Flow<NetworkResult<List<ExerciseUseCaseModel>>> =
        exerciseRepository.exercises.combine(favoritesRepository.favorites())
        { dataLayerExercise, favorites ->
            val favoritesList = if (favorites is NetworkResult.Success) {
                favorites.data
            } else {
                emptyList()
            }

            dataLayerExercise.mapIfSuccess { list ->
                list.map { dataLayerExercise ->
                    dataLayerExercise.toExerciseUseCase(favorite = favoritesList.contains(dataLayerExercise.id))
                }
            }
        }

    override fun exercise(id: Int): Flow<NetworkResult<ExerciseUseCaseModel>> =
        exerciseRepository.exercises.combine(favoritesRepository.favorites())
        { dataLayerExercise, favorites ->
            val favoritesList = if (favorites is NetworkResult.Success) {
                favorites.data
            } else {
                emptyList()
            }

            dataLayerExercise.mapIfSuccess { list ->
                list.filter { it.id == id }.map { dataLayerExercise ->
                    dataLayerExercise.toExerciseUseCase(favorite = favoritesList.contains(dataLayerExercise.id))
                }.first()
            }
        }

    override suspend fun updateFavorites(idList: List<Int>) {
        favoritesRepository.updateFavorites(idList)
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