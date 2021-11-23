package com.smarteryo.computerone.domain.implementation

import com.smarteryo.computerone.domain.repository.AppRepository
import com.store.data.service.SessionStoreService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    private val sessionStoreService: SessionStoreService
) : AppRepository {
    override fun setVibration(enabled: Boolean) {
        sessionStoreService.checkVibration = enabled
    }

    override fun getVibration(): Boolean {
        return sessionStoreService.checkVibration
    }

    override fun setSound(enabled: Boolean) {
        sessionStoreService.checkSound = enabled
    }

    override fun getSound(): Boolean {
        return sessionStoreService.checkSound
    }
}