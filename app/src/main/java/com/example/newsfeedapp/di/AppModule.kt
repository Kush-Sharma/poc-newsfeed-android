package com.example.newsfeedapp.di

import android.app.Application
import com.example.newsfeedapp.data.manager.LocalUserManagerImpl
import com.example.newsfeedapp.domain.manager.LocalUserManager
import com.example.newsfeedapp.domain.usecases.AppEntryUseCases
import com.example.newsfeedapp.domain.usecases.FetchAppEntry
import com.example.newsfeedapp.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}