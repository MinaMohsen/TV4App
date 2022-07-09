package com.example.tv4app.domain.repository

import com.example.tv4app.data.model.TV4Content
import com.example.tv4app.domain.util.Resource

interface TV4ContentRepository {
    suspend fun getContent(): Resource<TV4Content>
}