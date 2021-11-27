package com.example.kaiacasestudy.network

import com.example.kaiacasestudy.models.Exercise
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface ExerciseDataProvider {
    fun exercises(): Flow<NetworkResult<List<Exercise>>>
}

class ExerciseNetworkDataProvider @Inject constructor(
    private val exerciseService: ExerciseService,
    private val dispatchersModule: KaiaDispatchers,
) : ExerciseDataProvider {

    override fun exercises(): Flow<NetworkResult<List<Exercise>>> = flow {
        emit(NetworkResult.Loading)

        val exerciseResponse = exerciseService.exercises()

        if (exerciseResponse.isSuccessful && exerciseResponse.body() != null) {
            exerciseResponse.body()?.let {
                emit(NetworkResult.Success(it))
            } ?: emit(NetworkResult.Error(Exception("An Error Occurred")))
        } else {
            emit(NetworkResult.Error(Exception("An Error Occurred")))
        }

    }.flowOn(dispatchersModule.mainDispatcher())
}
