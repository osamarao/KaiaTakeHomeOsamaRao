package com.example.kaiacasestudy.repositories

import com.example.kaiacasestudy.models.Exercise
import com.example.kaiacasestudy.network.ExerciseDataProvider
import com.example.kaiacasestudy.network.KaiaDispatchers
import com.example.kaiacasestudy.network.NetworkResult
import com.example.kaiacasestudy.network.mapIfSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

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