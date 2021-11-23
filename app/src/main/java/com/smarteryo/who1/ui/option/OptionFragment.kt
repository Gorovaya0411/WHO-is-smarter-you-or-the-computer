package com.smarteryo.computerone.ui.option

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.smarteryo.computerone.R
import com.smarteryo.computerone.databinding.FragmentOptionBinding
import com.smarteryo.computerone.ui.base.BaseFragment
import com.smarteryo.computerone.ui.main.MainActivity
import com.smarteryo.computerone.utill.bindEnabledImage
import com.smarteryo.computerone.utill.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OptionFragment : BaseFragment<FragmentOptionBinding>(R.layout.fragment_option),
    OptionActionsListener {

    private val contextActivity: MainActivity by lazy(LazyThreadSafetyMode.NONE) {
        (activity as MainActivity)
    }
    private val optionViwModel: OptionViewModel by viewModels()

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contextActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding.optionViewModel = optionViwModel
        binding.optionActionsListener = this

        initView()
    }

    private fun initView() {

        observe(optionViwModel.settingsVibrationEnabled) {
            binding.optionSwitchVibrationImgBtn.bindEnabledImage(it)
        }

        observe(optionViwModel.settingsSoundEnabled) {
            binding.optionSwitchSoundImgBtn.bindEnabledImage(it)
        }
    }

    override fun optionBack() {
        contextActivity.onBackPressed()
    }

    override fun optionPP() {
        val browserIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://clck.ru/Vzb9W"))
        ContextCompat.startActivity(contextActivity, browserIntent, null)
    }
}
