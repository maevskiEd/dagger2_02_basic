package dev.androidbroadcast.dagger

import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dev.androidbroadcast.dagger.data.Analytics
import dev.androidbroadcast.dagger.data.NewsRepository
import dev.androidbroadcast.dagger.data.NewsRepositoryImpl
import dev.androidbroadcast.dagger.data.NewsService
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named
import javax.inject.Qualifier

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
