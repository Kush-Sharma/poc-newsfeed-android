package com.example.newsfeedapp.di

import android.app.Application
import androidx.room.Room
import com.example.newsfeedapp.data.local.NewsDao
import com.example.newsfeedapp.data.local.NewsDatabase
import com.example.newsfeedapp.data.local.NewsTypeConvertor
import com.example.newsfeedapp.data.manager.LocalUserManagerImpl
import com.example.newsfeedapp.data.remote.NewsApi
import com.example.newsfeedapp.data.repository.NewsRepositoryImpl
import com.example.newsfeedapp.domain.manager.LocalUserManager
import com.example.newsfeedapp.domain.repository.NewsRepository
import com.example.newsfeedapp.domain.usecases.appentry.AppEntryUseCases
import com.example.newsfeedapp.domain.usecases.appentry.FetchAppEntry
import com.example.newsfeedapp.domain.usecases.appentry.SaveAppEntry
import com.example.newsfeedapp.domain.usecases.news.GetNews
import com.example.newsfeedapp.domain.usecases.news.NewsUseCases
import com.example.newsfeedapp.domain.usecases.news.SearchNews
import com.example.newsfeedapp.util.Constants.BASE_URL
import com.example.newsfeedapp.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        saveAppEntry = SaveAppEntry(localUserManager),
        fetchAppEntry = FetchAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi = newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository = newsRepository),
            searchNews = SearchNews(newsRepository = newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

}
