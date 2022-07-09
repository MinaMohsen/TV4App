package com.example.tv4app.presentation.tv4contentlist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tv4app.R
import com.example.tv4app.data.model.TV4Show
import com.example.tv4app.domain.repository.TV4ContentRepository
import com.example.tv4app.domain.util.Resource
import com.example.tv4app.presentation.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TV4ContentViewModel @Inject constructor(
    private val repository: TV4ContentRepository
) : ViewModel() {

    val tv4ShowsList = mutableStateOf<List<TV4Show>>(emptyList())
    val isLoading = mutableStateOf(false)
    val loadError = mutableStateOf<UiText>(UiText.DynamicString(""))

    init {
        loadTV4Content()
    }

    fun loadTV4Content() {
        isLoading.value = true
        viewModelScope.launch {
            when (val result = repository.getContent()) {
                is Resource.Success -> {
                    isLoading.value = false
                    loadError.value = UiText.DynamicString("")
                    tv4ShowsList.value =
                        result.data?.data?.shuffled() ?: emptyList()
                }
                is Resource.Error -> {
                    isLoading.value = false
                    result.message?.let {
                        loadError.value = UiText.DynamicString(it)
                    } ?: run {
                        loadError.value =
                            UiText.StringResource(resId = R.string.unknown_error)
                    }
                }
            }
        }
    }
}