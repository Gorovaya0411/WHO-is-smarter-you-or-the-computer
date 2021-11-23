package com.store.sessionstore

import android.content.SharedPreferences
import javax.inject.Inject

class SessionStore @Inject constructor(private val sharedPreferences: SharedPreferences) {

    var uuId: String
        get() = sharedPreferences.getString(KEY_UU_ID, "") ?: ""
        set(value) {
            sharedPreferences.apply {
                edit().putString(KEY_UU_ID, value).apply()
            }
        }

    var dePin: Int
        get() = sharedPreferences.getInt(KEY_DE_PIN, 0)
        set(value) {
            sharedPreferences.apply {
                edit().putInt(KEY_DE_PIN, value).apply()
            }
        }

    var rePin: Int
        get() = sharedPreferences.getInt(KEY_RE_PIN, 0)
        set(value) {
            sharedPreferences.apply {
                edit().putInt(KEY_RE_PIN, value).apply()
            }
        }

    var remoteUri: String
        get() {
            return sharedPreferences.getString(URI, "") ?: ""
        }
        set(value) {
            sharedPreferences.apply {
                edit().putString(URI, value).apply()
            }
        }

    var firstLaunch: Boolean
        get() = sharedPreferences.getBoolean(KEY_FIRST_LAUNCH, true)
        set(value) {
            sharedPreferences.apply {
                edit().putBoolean(KEY_FIRST_LAUNCH, value).apply()
            }
        }

    var bestScore: Int
        get() = sharedPreferences.getInt(KEY_BEST_SCORE, 0)
        set(value) {
            sharedPreferences.apply {
                edit().putInt(KEY_BEST_SCORE, value).apply()
            }
        }

    var equation: String
        get() {
            return sharedPreferences.getString(KEY_EQUATION, "") ?: ""
        }
        set(value) {
            sharedPreferences.apply {
                edit().putString(KEY_EQUATION, value).apply()
            }
        }

    var score: Int
        get() = sharedPreferences.getInt(KEY_SCORE, 0)
        set(value) {
            sharedPreferences.apply {
                edit().putInt(KEY_SCORE, value).apply()
            }
        }

    var timerTime: Int
        get() = sharedPreferences.getInt(KEY_REMAINING_TIMER_TIME, 0)
        set(value) {
            sharedPreferences.apply {
                edit().putInt(KEY_REMAINING_TIMER_TIME, value).apply()
            }
        }

    var checkSound: Boolean
        get() = sharedPreferences.getBoolean(KEY_CHECK_SOUND, false)
        set(value) {
            sharedPreferences.apply {
                edit().putBoolean(KEY_CHECK_SOUND, value).apply()
            }
        }

    var checkVibration: Boolean
        get() = sharedPreferences.getBoolean(KEY_CHECK_VIBRATION, false)
        set(value) {
            sharedPreferences.apply {
                edit().putBoolean(KEY_CHECK_VIBRATION, value).apply()
            }
        }

    var secondRemaining: Long
        get() = sharedPreferences.getLong(KEY_SECOND_REMAINING, 0L)
        set(value) {
            sharedPreferences.edit().putLong(KEY_SECOND_REMAINING, value).apply()
        }

    companion object {
        private const val URI = "URI"
        private const val KEY_UU_ID = "uuid"
        const val KEY_DE_PIN = "key de pin"
        private const val KEY_SECOND_REMAINING = "second_remaining"
        const val KEY_RE_PIN = "key re pin"
        const val KEY_BEST_SCORE = "key_best_score"
        const val KEY_SCORE = "key_score"
        const val KEY_EQUATION = "key_equation"
        const val KEY_REMAINING_TIMER_TIME = "key_remaining_timer_time"
        const val KEY_CHECK_SOUND = "key_check_sound"
        const val KEY_CHECK_VIBRATION = "key_check_vibration"
        private const val KEY_FIRST_LAUNCH = "key first launch"
    }
}