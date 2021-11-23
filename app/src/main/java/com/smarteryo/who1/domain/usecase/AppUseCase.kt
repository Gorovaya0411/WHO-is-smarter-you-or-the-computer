package com.smarteryo.computerone.domain.usecase

import com.smarteryo.computerone.domain.repository.AppRepository
import com.smarteryo.computerone.ui.option.SettingsModel
import com.store.data.service.SessionStoreService
import javax.inject.Inject

interface AppUseCase {
    var bestScore: Int
    var checkVibration: Boolean
    var checkSound: Boolean
    var secondRemaining: Long
    var equation: String
    var score: Int
    var timerTime: Int
    fun saveSettings(settingsModel: SettingsModel)
    fun getSettingsEnabled(settingsModel: SettingsModel): Boolean
}

class AppUseCaseImpl @Inject constructor(
    private val sessionStoreService: SessionStoreService,
    private val appRepository: AppRepository
) : AppUseCase {

    override fun saveSettings(settingsModel: SettingsModel) {
        when (settingsModel) {
            SettingsModel.VIBRATION -> {
                appRepository.setVibration(settingsModel.enabled)
            }
            SettingsModel.SOUND -> {
                appRepository.setSound(settingsModel.enabled)
            }
        }
    }

    override fun getSettingsEnabled(settingsModel: SettingsModel): Boolean {
        return when(settingsModel) {
            SettingsModel.VIBRATION -> {
                appRepository.getVibration()
            }
            SettingsModel.SOUND -> {
                appRepository.getSound()
            }
        }
    }

    override var bestScore: Int
        get() = sessionStoreService.bestScore
        set(value) {
            sessionStoreService.bestScore = value
        }

    override var score: Int
        get() = sessionStoreService.score
        set(value) {
            sessionStoreService.score = value
        }

    override var timerTime: Int
        get() = sessionStoreService.timerTime
        set(value) {
            sessionStoreService.timerTime = value
        }

    override var equation: String
        get() = sessionStoreService.equation
        set(value) {
            sessionStoreService.equation = value
        }

    override var checkVibration: Boolean
        get() = sessionStoreService.checkVibration
        set(value) {
            sessionStoreService.checkVibration = value
        }

    override var checkSound: Boolean
        get() = sessionStoreService.checkSound
        set(value) {
            sessionStoreService.checkSound = value
        }

    override var secondRemaining: Long
        get() = sessionStoreService.secondRemaining
        set(value) {
            sessionStoreService.secondRemaining = value
        }
}