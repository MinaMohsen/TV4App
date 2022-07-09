package com.example.tv4app.data.repository

import com.example.tv4app.data.remote.TV4ContentApi
import com.example.tv4app.data.model.TV4Content
import com.example.tv4app.domain.repository.TV4ContentRepository
import com.example.tv4app.domain.util.Resource
import javax.inject.Inject

class TV4ContentRepositoryImpl @Inject constructor(
    private val api: TV4ContentApi
) : TV4ContentRepository {
    override suspend fun getContent(): Resource<TV4Content> {
        return try {
            Resource.Success(api.getContent())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = e.message ?: e.cause?.message)
        }
    }
}