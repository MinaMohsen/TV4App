package com.example.tv4app.data.model

import com.squareup.moshi.Json

data class TV4Content(
    @field:Json(name = "data")
    val data: List<TV4Show>?
)
