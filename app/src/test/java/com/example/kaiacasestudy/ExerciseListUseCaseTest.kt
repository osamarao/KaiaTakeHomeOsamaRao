package com.example.kaiacasestudy

import com.example.kaiacasestudy.data.NetworkResult
import com.example.kaiacasestudy.repositories.ExerciseDataLayer
import com.example.kaiacasestudy.repositories.ExerciseRepository
import com.example.kaiacasestudy.repositories.FavoritesRepository
import com.example.kaiacasestudy.usecase.ExerciseListUseCase
import com.example.kaiacasestudy.usecase.ExerciseListUseCaseImpl
import com.example.kaiacasestudy.usecase.ExerciseUseCaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test


class ExerciseListUseCaseTest {

    private val exerciseRepository = object : ExerciseRepository {
        override val exercises: Flow<NetworkResult<List<ExerciseDataLayer>>>
            get() = flowOf(NetworkResult.Success(
                listOf(
                    ExerciseDataLayer(1, "exercise 1", "coverimageurl 1"),
                    ExerciseDataLayer(2, "exercise 2", "coverimageurl 2"),
                )
            ))
    }

    private val favoritesRepository = object : FavoritesRepository {
        override fun favorites(): Flow<NetworkResult<List<Int>>> {
            return flowOf(NetworkResult.Success(
                listOf(
                    2
                )
            ))
        }

        override suspend fun updateFavorites(idList: List<Int>) {

        }

    }

    private val subject: ExerciseListUseCase = ExerciseListUseCaseImpl(exerciseRepository, favoritesRepository)

    @Test
    fun `favorites match in exercise list`() = runBlocking {
        val exercises = subject.exercises().first() as NetworkResult.Success

        assertThat("first exercise", exercises.data[0] == ExerciseUseCaseModel(1, "exercise 1", "coverimageurl 1", false))
        assertThat("second exercise", exercises.data[1] == ExerciseUseCaseModel(2, "exercise 2", "coverimageurl 2", true))
    }
}