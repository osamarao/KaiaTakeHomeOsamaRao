package com.example.kaiacasestudy.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Exercise(
    val id: Int,
    val name: String,
    @SerialName("cover_image_url")
    val coverImageUrl: String,
)