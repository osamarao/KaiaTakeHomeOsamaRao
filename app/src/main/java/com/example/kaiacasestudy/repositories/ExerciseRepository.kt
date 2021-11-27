package com.example.kaiacasestudy.repositories

import com.example.kaiacasestudy.data.ExerciseDataProvider
import com.example.kaiacasestudy.data.KaiaDispatchers
import com.example.kaiacasestudy.data.NetworkResult
import com.example.kaiacasestudy.data.mapIfSuccess
import com.example.kaiacasestudy.models.Exercise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

interface ExerciseRepository {
    val exercises: Flow<NetworkResult<List<ExerciseDataLayer>>>
}


class ExerciseRepositoryImpl @Inject constructor(
    exerciseDataProvider: ExerciseDataProvider,
    dispatchersModule: KaiaDispatchers,
) : ExerciseRepository {

    init {
        exerciseDataProvider.exercises()
            .onEach {
                _exercises.value = it
            }
            .launchIn(CoroutineScope(dispatchersModule.mainDispatcher()))
    }

    private val _exercises: MutableStateFlow<NetworkResult<List<Exercise>>> = MutableStateFlow(NetworkResult.Loading)
    override val exercises: Flow<NetworkResult<List<ExerciseDataLayer>>> =
        _exercises.map { networkResultListExercises ->
            networkResultListExercises.mapIfSuccess { exerciseList ->
                exerciseList.map { exercise ->
                    exercise.toExerciseDataLayer()
                }
            }
        }
}


data class ExerciseDataLayer(
    val id: Int,
    val name: String,
    val coverImageUrl: String,
)

fun Exercise.toExerciseDataLayer(): ExerciseDataLayer = ExerciseDataLayer(id, name, coverImageUrl)