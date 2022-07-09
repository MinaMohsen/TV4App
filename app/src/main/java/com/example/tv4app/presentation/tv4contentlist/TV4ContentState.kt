package com.example.tv4app.presentation.tv4contentlist

import com.example.tv4app.data.model.TV4Content

data class TV4ContentState(
    val tv4Content: TV4Content? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)