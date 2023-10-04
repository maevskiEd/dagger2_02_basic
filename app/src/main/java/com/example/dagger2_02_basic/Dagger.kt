package com.example.dagger2_02_basic

import dagger.Component
import dagger.Module
import dagger.Provides
import com.example.dagger2_02_basic.data.Analytics
import com.example.dagger2_02_basic.data.NewsRepository
import com.example.dagger2_02_basic.data.NewsRepositoryImpl
import com.example.dagger2_02_basic.data.NewsService
import dagger.Binds
import retrofit2.Retrofit
import retrofit2.create


//можно NetworkModule добавить в компонент
//@Component(modules = [AppModule::class, NetworkModule::class])
@Component(modules = [AppModule::class])
interface AppComponent

//а можно NetworkModule добавить в верхний модуль

//layer4
//инжект в конструктор позволяет легко переносить классы и легко делать модуляризацию

//layer4
//в AppModule мы в функциях вызываем конструкторы, передавая туда две зависимости из графа
//даггер позволяет предоставлять зависимости прямо в поля, что используется для Activity,
//когда у нас нет возможности инжектить в конструктор
// а так как в аналитику и репозиторий мы можем инжектить в конструктор, то мы убираем отсюда функции
//и прописываем inject в конструктора -> смотри классы
@Module(includes = [NetworkModule::class, AppBindModule::class])
class AppModule
/*{
*//*    @Provides
    fun provideNewsRepositoryImpl(
        newsService: NewsService,
        analytics: Analytics
    ): NewsRepositoryImpl {
        return NewsRepositoryImpl(newsService, analytics)
    }*//*

*//*    @Provides
    fun provideAnalytics(): Analytics {
        return Analytics()
    }*//*
}*/

//c retrofit мы не можем инжектить в конструктор, потому что он внешний и здесь используется
//система builder
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

//В идеале надо использовать abstract, но можно использовать интерфейс
@Module
//abstract class AppBindModule {
interface AppBindModule {
    @Binds
//    abstract fun bindNewsRepositoryImpl_to_NewsRepository(
    fun bindNewsRepositoryImpl_to_NewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository
}
