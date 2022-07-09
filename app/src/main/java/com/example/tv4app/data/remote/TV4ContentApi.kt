package com.example.tv4app.data.remote

import com.example.tv4app.data.model.TV4Content
import retrofit2.http.GET

interface TV4ContentApi {

    @GET("assets?client=android-code-test")
    suspend fun getContent(): TV4Content
}