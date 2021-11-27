package com.example.kaiacasestudy.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kaiacasestudy.network.NetworkResult
import com.example.kaiacasestudy.network.mapIfSuccess
import com.example.kaiacasestudy.usecase.ExerciseListFragmentUseCase
import com.example.kaiacasestudy.usecase.ExerciseUseCaseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(useCase: ExerciseListFragmentUseCase): ViewModel() {

    private val _exerciseList : MutableStateFlow<NetworkResult<List<ExerciseApplicationModel>>> = MutableStateFlow(NetworkResult.Loading)
    val exerciseList = _exerciseList.asStateFlow()

    init {
        useCase.exercises().onEach {
            _exerciseList.value = it.mapIfSuccess { list ->
                list.map { exerciseUseCaseModel ->
                    exerciseUseCaseModel.toExerciseApplicationModel()
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class ExerciseApplicationModel(
    val id: Int,
    val name: String,
    val coverImageUrl: String,
    val favorite: Boolean,
)

fun  ExerciseUseCaseModel.toExerciseApplicationModel() : ExerciseApplicationModel = ExerciseApplicationModel(id, name, coverImageUrl, favorite)