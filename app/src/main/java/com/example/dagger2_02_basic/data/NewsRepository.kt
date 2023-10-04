@file:Suppress("unused")

package com.example.dagger2_02_basic.data

import javax.inject.Inject

//Репозиторий обертка для получения данных
//реализация + интерфейс
interface NewsRepository {

    suspend fun getNews(newsId: String): News
}

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService,
    private val analytics: Analytics,
) : NewsRepository {

    override suspend fun getNews(newsId: String): News {
        analytics.trackNewsRequest(newsId)
        return newsService.news(newsId)
    }
}