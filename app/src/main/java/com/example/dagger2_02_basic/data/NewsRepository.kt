@file:Suppress("unused")

package dev.androidbroadcast.dagger.data

import javax.inject.Inject

//Репозиторий обертка для получения данных
//реализация + интерфейс
interface NewsRepository {

    suspend fun getNews(newsId: String): News
}

class NewsRepositoryImpl (
    private val newsService: NewsService,
    private val analytics: Analytics,
) : NewsRepository {

    override suspend fun getNews(newsId: String): News {
        analytics.trackNewsRequest(newsId)
        return newsService.news(newsId)
    }
}