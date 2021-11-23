package com.smarteryo.computerone.ui.secondary_menu

import androidx.lifecycle.ViewModel
import com.smarteryo.computerone.domain.usecase.AppUseCase
import com.smarteryo.computerone.ui.option.SettingsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecondaryMenuViewModel @Inject constructor(private val appUseCase: AppUseCase) : ViewModel() {

    fun setTimerTime(checkVibration: Int) {
        appUseCase.timerTime = checkVibration
    }

    fun getVibration(): Boolean {
        return appUseCase.getSettingsEnabled(SettingsModel.VIBRATION)
    }

    fun getSound(): Boolean {
        return appUseCase.getSettingsEnabled(SettingsModel.SOUND)
    }

    fun getTimerTime(): Int {
        return appUseCase.timerTime
    }

    fun setCheckExample(checkExample: String) {
        appUseCase.equation = checkExample
    }

    fun setScore(score: Int) {
        appUseCase.score = score
    }

    fun setSecondRemaining(secondRemaining: Long) {
        appUseCase.secondRemaining = secondRemaining
    }
}