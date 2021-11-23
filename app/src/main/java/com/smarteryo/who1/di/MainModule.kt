package com.smarteryo.computerone.di

import com.smarteryo.computerone.domain.implementation.AppRepositoryImpl
import com.smarteryo.computerone.domain.repository.AppRepository
import com.smarteryo.computerone.domain.usecase.AppUseCase
import com.smarteryo.computerone.domain.usecase.AppUseCaseImpl
import com.store.data.service.SessionStoreService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideAppRepository(
        sessionStoreService: SessionStoreService
    ): AppRepository = AppRepositoryImpl(sessionStoreService)

    @Provides
    fun provideAppUseCase(
        appRepository: AppRepository,
        sessionStoreService: SessionStoreService
    ): AppUseCase = AppUseCaseImpl(sessionStoreService, appRepository)
}