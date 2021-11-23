package com.smarteryo.computerone.ui.main_two

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smarteryo.computerone.R
import com.smarteryo.computerone.domain.usecase.AppUseCase
import com.smarteryo.computerone.ui.option.SettingsModel
import com.smarteryo.computerone.utill.CountDownTimer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainTwoViewModel @Inject constructor(
    private val appUseCase: AppUseCase,
    val context: Application
) : ViewModel() {

    private var resultOfEquation = 0
    private var magnificationScore = 0
    private var isTimerStarted: Boolean = false
    private var secondsRemaining = 0L
    private var remainingTimerTime = 0L
    private var timer: CountDownTimer? = null
    private var oneMinutes = 60L
    private val twoMinutes = 120L
    private val threeMinutes = 180L
    private val equationTypes = Equation.values()

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> = _score

    private val _timeOver = MutableLiveData<Int>()
    val timeOver: LiveData<Int> = _timeOver

    private val _timeTimer = MutableLiveData<String>()
    val timeTimer: LiveData<String> = _timeTimer

    private val _equationString = MutableLiveData<String>()
    val equationString: LiveData<String> = _equationString

    private val _calculationResult = MutableLiveData(false)
    val calculationResult: LiveData<Boolean> = _calculationResult

    private val _watcher = MutableLiveData<String>()
    val watcher: LiveData<String> = _watcher

    init {
        getGeneratedEquation()
        _timeOver.postValue(5)
    }

    fun onUsernameTextChanged(str: CharSequence) {
        _watcher.postValue(str.toString())
    }

    fun getGeneratedEquation() {
        val currentEquation = getEquationFromCache()
        if (currentEquation == "") {
            _equationString.postValue(generateEquation(equationTypes[(0..2).random()]))
        } else {
            _equationString.postValue(currentEquation)
        }
    }

    private fun generateEquation(equationType: Equation): String {
        when (equationType) {
            Equation.ADDITION -> {
                val valueA = (1..99).random()
                val valueB = (1..valueA).random()
                resultOfEquation = valueA + valueB
                setEquation(context.getString(R.string.main2_equation_addition, valueA, valueB))

                return context.getString(R.string.main2_equation_addition, valueA, valueB)
            }

            Equation.SUBTRACTION -> {
                val valueA = (1..99).random()
                val valueB = (1 until valueA).random()
                resultOfEquation = valueA - valueB
                setEquation(
                    context.getString(
                        R.string.main2_equation_subtraction,
                        valueA,
                        valueB
                    )
                )
                setEquation(context.getString(R.string.main2_equation_subtraction, valueA, valueB))
                return context.getString(R.string.main2_equation_subtraction, valueA, valueB)
            }

            Equation.MULTIPLICATION -> {
                val valueA = (1..99).random()
                val valueB = (1..valueA).random()
                resultOfEquation = valueA * valueB
                setEquation(
                    context.getString(
                        R.string.main2_equation_multiplication,
                        valueA,
                        valueB
                    )
                )
                setEquation(
                    context.getString(
                        R.string.main2_equation_multiplication,
                        valueA,
                        valueB
                    )
                )
                return context.getString(R.string.main2_equation_multiplication, valueA, valueB)
            }
        }
    }

    fun compareResults(userResult: Int) {
        _calculationResult.postValue(userResult == resultOfEquation)
    }

    fun onAnswer(isCorrect: Boolean) {
        if (isCorrect) {
            magnificationScore += 1
            _score.postValue(magnificationScore)

            if (getBestScore() < magnificationScore) {
                setBestScore(magnificationScore)
            }
            _equationString.postValue(generateEquation(equationTypes[(0..2).random()]))
        } else {
            if (getSecondRemaining() != 0L) {
                timerStop()
                isTimerStarted = false

                if (remainingTimerTime <= 5) {
                    remainingTimerTime = 1
                } else {
                    remainingTimerTime -= 5
                }
                setSecondRemaining(remainingTimerTime)
                initTimer(
                    remainingTimerTime
                )
            }
        }
    }

    private fun initTimer(minutesTimer: Long) {
        timer = object : CountDownTimer(minutesTimer * 1000, 1000) {
            override fun onTimerTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountdownUI()
            }

            override fun onTimerFinish() {
                when (getTimerTime()) {
                    1 -> timerFinished()
                    2 -> timerFinished()
                    3 -> timerFinished()
                }
            }
        }.also { timer ->
            if (!isTimerStarted) timer.start()
        }
        isTimerStarted = true
    }

    private fun updateCountdownUI() {
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsInMinuteUntilFinished.toString()

        val minutes = if (minutesUntilFinished == 0L) "0" else (secondsRemaining / 60).toString()
        val seconds = if (secondsStr.length == 2) secondsStr else "0$secondsStr"
        remainingTimerTime = secondsRemaining
        setSecondRemaining(secondsRemaining)

        _timeTimer.postValue(context.getString(R.string.timer_count_with_minutes, minutes, seconds))
    }

    private fun timerFinished() {
        _timeOver.postValue(0)
        _timeTimer.postValue(context.getString(R.string.finished_time))
        _equationString.postValue("")
        _score.postValue(0)
        isTimerStarted = false

        viewModelScope.launch {
            delay(1000)
            _timeOver.postValue(5)
            _equationString.postValue(generateEquation(equationTypes[(0..2).random()]))
            choiceTime()
        }
    }

    fun choiceTimeTimer() {
        if (getSecondRemaining() == 0L) {
            choiceTime()
        } else {
            initTimer(
                getSecondRemaining()
            )
        }
    }

    fun choiceTime() {
        when (getTimerTime()) {
            1 -> initTimer(
                oneMinutes
            )
            2 -> initTimer(
                twoMinutes
            )
            3 -> initTimer(
                threeMinutes
            )
        }
    }

    fun timerStop() {
        timer?.cancel()
        isTimerStarted = false
    }

    fun getSound(): Boolean {
        return appUseCase.getSettingsEnabled(SettingsModel.SOUND)
    }

    private fun getBestScore(): Int {
        return appUseCase.bestScore
    }

    private fun setBestScore(checkBestScore: Int) {
        appUseCase.bestScore = checkBestScore
    }

    private fun getEquationFromCache(): String {
        return appUseCase.equation
    }

    fun setEquation(checkEquation: String) {
        appUseCase.equation = checkEquation
    }

    fun setSecondRemaining(secondRemaining: Long) {
        appUseCase.secondRemaining = secondRemaining
    }

    private fun getSecondRemaining(): Long {
        return appUseCase.secondRemaining
    }

    fun getTimerTime(): Int {
        return appUseCase.timerTime
    }

    fun setScore(checkScore: Int) {
        appUseCase.score = checkScore
    }

    fun getVibration(): Boolean {
        return appUseCase.getSettingsEnabled(SettingsModel.VIBRATION)
    }
}