package com.smarteryo.computerone.domain.repository

interface AppRepository {
    fun setVibration(enabled: Boolean)
    fun getVibration(): Boolean
    fun setSound(enabled: Boolean)
    fun getSound(): Boolean
}