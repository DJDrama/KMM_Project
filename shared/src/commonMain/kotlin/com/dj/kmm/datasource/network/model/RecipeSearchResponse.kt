package com.dj.kmm.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeSearchResponse(
    @SerialName("count")
    var count: Int,
    @SerialName("next")
    var next: String,
    @SerialName("results")
    var results: List<RecipeDto>
)
