package com.smarteryo.computerone.ui.main_two

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.smarteryo.computerone.R
import com.smarteryo.computerone.databinding.FragmentMainTwoBinding
import com.smarteryo.computerone.ui.base.BaseFragment
import com.smarteryo.computerone.ui.main.MainActivity
import com.smarteryo.computerone.ui.pause.PauseDialogFragment.Companion.CONTINUE_KEY
import com.smarteryo.computerone.ui.pause.PauseDialogFragment.Companion.NEW_GAME_KEY
import com.smarteryo.computerone.utill.CountDownTimer
import com.smarteryo.computerone.utill.getNavigationResult
import com.smarteryo.computerone.utill.observe
import com.smarteryo.computerone.utill.setTextWatcher
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainTwoFragment : BaseFragment<FragmentMainTwoBinding>(R.layout.fragment_main_two),
    MainTwoActionsListener, View.OnKeyListener {

    private val contextActivity: MainActivity by lazy(LazyThreadSafetyMode.NONE) {
        (activity as MainActivity)
    }

    private val mainTwoViewModel: MainTwoViewModel by viewModels()
    private var timer: CountDownTimer? = null
    private var enteredResponse = ""

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contextActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        with(binding) {
            mainTwoActionsListener = this@MainTwoFragment
            mainTwoViewModelBinding = mainTwoViewModel
            mainTwoOnKeyListenerBinding = this@MainTwoFragment
        }

        mainTwoViewModel.choiceTimeTimer()
        initView()
        checkPlayerResult()
        getCallbackFromDialog()
    }

    private fun initView() {
        with(mainTwoViewModel) {
            observe(equationString) { equation ->
                binding.equation = equation
            }

            observe(calculationResult) { resultIsCorrect ->
                onAnswer(resultIsCorrect)
            }

            observe(score) { score ->
                binding.score = score.toString()
            }

            observe(timeTimer) { timer ->
                binding.timer = timer
            }

            observe(watcher) { entered ->
                enteredResponse = entered
            }

            observe(timeOver) { timerOver ->
                binding.timerOver = timerOver
            }
        }
    }

    private fun checkPlayerResult() {
        binding.mainTwoAnswerPlayerEt.setTextWatcher { entered ->
            enteredResponse = entered
        }
    }

    private fun getCallbackFromDialog() {
        getNavigationResult(CONTINUE_KEY) {
            timer?.start()
        }

        getNavigationResult(NEW_GAME_KEY) {
            findNavController().navigate(R.id.action_pauseDialogFragment_to_menuFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainTwoViewModel.timerStop()
    }

    private fun timerPause() {
        timer?.pause()
    }

    override fun mainTwoPause() {
        findNavController().navigate(
            R.id.action_mainTwoFragment_to_pauseDialogFragment
        )
        timerPause()
    }

    override fun mainTwoRefresh() {
        binding.mainTwoAnswerPlayerEt.setText("")
        with(mainTwoViewModel) {
            setScore(0)
            timerStop()
            setEquation("")
            setSecondRemaining(0L)
            choiceTime()
            getGeneratedEquation()
        }
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

            if (enteredResponse != "") {
                mainTwoViewModel.compareResults(enteredResponse.toInt())
            }
            binding.mainTwoAnswerPlayerEt.setText("")

            return true
        }
        return false
    }
}