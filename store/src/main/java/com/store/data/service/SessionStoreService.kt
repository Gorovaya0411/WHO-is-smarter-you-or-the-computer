package com.store.data.service

import com.store.sessionstore.SessionStore
import javax.inject.Inject

interface SessionStoreService {
    var remoteUri: String
    var uuId: String
    var firstLaunch: Boolean
    var rePin: Int
    var dePin: Int
    var bestScore: Int
    var checkSound: Boolean
    var checkVibration: Boolean
    var secondRemaining: Long
    var equation: String
    var score: Int
    var timerTime: Int
}

class SessionStoreServiceImpl @Inject constructor(
    val sessionStore: SessionStore
) : SessionStoreService {

    override var remoteUri: String
        get() = sessionStore.remoteUri
        set(value) {
            sessionStore.remoteUri = value
        }

    override var uuId: String
        get() = sessionStore.uuId
        set(value) {
            sessionStore.uuId = value
        }

    override var equation: String
        get() = sessionStore.equation
        set(value) {
            sessionStore.equation = value
        }

    override var score: Int
        get() = sessionStore.score
        set(value) {
            sessionStore.score = value
        }

    override var timerTime: Int
        get() = sessionStore.timerTime
        set(value) {
            sessionStore.timerTime = value
        }

    override var firstLaunch: Boolean
        get() = sessionStore.firstLaunch
        set(value) {
            sessionStore.firstLaunch = value
        }

    override var checkSound: Boolean
        get() = sessionStore.checkSound
        set(value) {
            sessionStore.checkSound = value
        }

    override var checkVibration: Boolean
        get() = sessionStore.checkVibration
        set(value) {
            sessionStore.checkVibration = value
        }

    override var secondRemaining: Long
        get() = sessionStore.secondRemaining
        set(value) {
            sessionStore.secondRemaining = value
        }

    override var rePin: Int
        get() = sessionStore.rePin
        set(value) {
            sessionStore.rePin = value
        }

    override var dePin: Int
        get() = sessionStore.dePin
        set(value) {
            sessionStore.dePin = value
        }

    override var bestScore: Int
        get() = sessionStore.bestScore
        set(value) {
            sessionStore.bestScore = value
        }
}