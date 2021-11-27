package com.example.kaiacasestudy.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kaiacasestudy.data.NetworkResult
import com.example.kaiacasestudy.usecase.ExerciseListUseCase
import com.example.kaiacasestudy.usecase.ExerciseUseCaseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ExerciseDetailViewModel @Inject constructor(private val useCase: ExerciseListUseCase) : ViewModel() {

    fun exercise(id: Int): Flow<NetworkResult<ExerciseUseCaseModel>> = useCase.exercise(id)

    fun addFavorite(id: Int) {
        useCase.exercises().onEach { exercisesResult ->
            if (exercisesResult is NetworkResult.Success) {
                val favorites = exercisesResult.data.filter { it.favorite }.map { it.id }.toMutableList()
                if (!favorites.contains(id)) {
                    favorites.add(id)
                }
                useCase.updateFavorites(favorites)
            }
        }.launchIn(viewModelScope)

    }

    fun removeFavorite(id: Int) {
        useCase.exercises().onEach { exercisesResult ->
            if (exercisesResult is NetworkResult.Success) {
                val favorites = exercisesResult.data.filter { it.favorite }.map { it.id }.toMutableList()
                favorites.removeAt(favorites.indexOf(id))
                useCase.updateFavorites(favorites)
            }
        }.launchIn(viewModelScope)

    }
}