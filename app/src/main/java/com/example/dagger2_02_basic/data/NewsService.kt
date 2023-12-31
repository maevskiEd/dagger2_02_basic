@file:Suppress("unused")

package com.example.dagger2_02_basic.data

import retrofit2.http.GET
import retrofit2.http.Path

//реализация с помощью Retrofit
interface NewsService {

    @GET("news")
    suspend fun news(): List<News>

    @GET("news/{id}")
    suspend fun news(@Path("id") id: String): News
}

data class News(
    val id: String,
    val title: String,
    val body: String,
)