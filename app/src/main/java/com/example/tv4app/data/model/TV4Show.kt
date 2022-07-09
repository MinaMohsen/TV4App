package com.example.tv4app.data.model

import com.squareup.moshi.Json

data class TV4Show(
    @field:Json(name = "broadcast_date_time")
    val showBroadCastTime: String? ,
    @field:Json(name = "category_title_nids")
    val showCategory: List<String>? = emptyList(),
    val description: String? ,
    val title: String?,
    val image: String?,
    val type: String?
)
