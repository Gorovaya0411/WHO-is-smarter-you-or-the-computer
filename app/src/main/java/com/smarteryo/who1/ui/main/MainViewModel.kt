package com.smarteryo.computerone.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smarteryo.computerone.ui.base.BaseViewModel
import com.smarteryo.computerone.domain.usecase.AppUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appUseCase: AppUseCase
) : BaseViewModel() {

    private val _uri = MutableStateFlow("")
    val uri: StateFlow<String> = _uri.asStateFlow()


    fun getCheckVibration(): Boolean {
        return appUseCase.checkVibration
    }

    fun getCheckSound(): Boolean {
        return appUseCase.checkSound
    }
}