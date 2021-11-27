package com.example.kaiacasestudy.data

import com.example.kaiacasestudy.models.Exercise
import retrofit2.Response
import retrofit2.http.GET

interface ExerciseService {

    @GET("027787de-c76e-11eb-ae0a-39a1b8479ec2")
    suspend fun exercises() : Response<List<Exercise>>
}
