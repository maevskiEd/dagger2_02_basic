package com.example.dagger2_02_basic

import dagger.Component
import dagger.Module
import dagger.Provides
import com.example.dagger2_02_basic.data.Analytics
import com.example.dagger2_02_basic.data.NewsRepositoryImpl
import com.example.dagger2_02_basic.data.NewsService
import retrofit2.Retrofit
import retrofit2.create

@Component(modules = [AppModule::class])
interface AppComponent

@Module
class AppModule {

    @Provides
    fun provideNewsRepositoryImpl(
        newsService: NewsService,
        analytics: Analytics
    ): NewsRepositoryImpl {
        return NewsRepositoryImpl(newsService, analytics)
    }

    @Provides
    fun provideProductionNewsService(): NewsService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://androidbrodcast.dev")
            .build()
        return retrofit.create()
    }

    @Provides
    fun provideAnalytics(): Analytics {
        return Analytics()
    }
}
