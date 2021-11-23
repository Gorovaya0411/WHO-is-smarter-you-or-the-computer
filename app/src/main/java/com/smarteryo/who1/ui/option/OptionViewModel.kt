package com.smarteryo.computerone.ui.option

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smarteryo.computerone.domain.usecase.AppUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OptionViewModel @Inject constructor(private val appUseCase: AppUseCase) : ViewModel() {

    private val _settingsVibrationEnabled = MutableLiveData<Boolean>()
    val settingsVibrationEnabled: LiveData<Boolean> = _settingsVibrationEnabled

    private val _settingsSoundEnabled = MutableLiveData<Boolean>()
    val settingsSoundEnabled: LiveData<Boolean> = _settingsSoundEnabled

    init {
        _settingsVibrationEnabled.postValue(getSettings(SettingsModel.VIBRATION))
        _settingsSoundEnabled.postValue(getSettings(SettingsModel.SOUND))
    }

    fun setVibrationOnClick() {
        with(appUseCase) {
            saveSettings(SettingsModel.VIBRATION.apply {
                enabled = !getSettingsEnabled(SettingsModel.VIBRATION)
            })
        }
        _settingsVibrationEnabled.postValue(getSettings(SettingsModel.VIBRATION))
    }

    fun setSoundOnClick() {
        with(appUseCase) {
            saveSettings(SettingsModel.SOUND.apply {
                enabled = !getSettingsEnabled(SettingsModel.SOUND)
            })
        }
        _settingsSoundEnabled.postValue(getSettings(SettingsModel.SOUND))
    }

    fun getVibration(): Boolean {
        return appUseCase.getSettingsEnabled(SettingsModel.VIBRATION)
    }

    fun getSound(): Boolean {
        return appUseCase.getSettingsEnabled(SettingsModel.SOUND)
    }

    private fun getSettings(settingsModel: SettingsModel): Boolean {
        return appUseCase.getSettingsEnabled(settingsModel)
    }
}