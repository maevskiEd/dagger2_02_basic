package com.example.dagger2_02_basic

import dagger.Component
import dagger.Module
import dagger.Provides
import com.example.dagger2_02_basic.data.Analytics
import com.example.dagger2_02_basic.data.NewsRepository
import com.example.dagger2_02_basic.data.NewsRepositoryImpl
import com.example.dagger2_02_basic.data.NewsService
import retrofit2.Retrofit
import retrofit2.create


//можно NetworkModule добавить в компонент
//@Component(modules = [AppModule::class, NetworkModule::class])
@Component(modules = [AppModule::class])
interface AppComponent

//а можно NetworkModule добавить в верхний модуль
@Module(includes = [NetworkModule::class])
class AppModule {

    //Вместо этого провайд можно использовать bind, которая указывает что можно делать в случаях,
    //когда запрашивается один тип, а надо вернуть другой. Для этого надо сделать класс,
    //в котором можно декларировать абстрактные методы
    //abstraсt class AppModule {

/*    @Binds
    adstract fun bindNewsRepositoryImpl_to_NewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository*/

    @Provides
    fun provideNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository {
        return  newsRepositoryImpl
    }

    @Provides
    fun provideNewsRepositoryImpl(
        newsService: NewsService,
        analytics: Analytics
    ): NewsRepositoryImpl {
        return NewsRepositoryImpl(newsService, analytics)
    }


    @Provides
    fun provideAnalytics(): Analytics {
        return Analytics()
    }
}

@Module
class NetworkModule {
    @Provides
    fun provideProductionNewsService(): NewsService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://androidbrodcast.dev")
            .build()
        return retrofit.create()
    }
}
