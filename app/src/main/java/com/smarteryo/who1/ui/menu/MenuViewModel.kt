package com.smarteryo.computerone.ui.menu

import androidx.lifecycle.ViewModel
import com.smarteryo.computerone.domain.usecase.AppUseCase
import com.smarteryo.computerone.ui.option.SettingsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val appUseCase: AppUseCase) : ViewModel() {

    fun getBestScore(): Int {
        return appUseCase.bestScore
    }

    fun setTimerTime(timerTime: Int) {
        appUseCase.timerTime = timerTime
    }

    fun getVibration(): Boolean {
        return appUseCase.getSettingsEnabled(SettingsModel.VIBRATION)
    }

    fun getSound(): Boolean {
        return appUseCase.getSettingsEnabled(SettingsModel.SOUND)
    }
}